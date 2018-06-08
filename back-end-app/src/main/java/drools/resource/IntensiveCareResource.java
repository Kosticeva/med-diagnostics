package drools.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Allergen;
import drools.model.Chart;
import drools.model.Disease;
import drools.model.Doctor;
import drools.model.Drug;
import drools.model.Examination;
import drools.model.Ingredient;
import drools.model.IntensiveCareReport;
import drools.model.Patient;
import drools.model.Prescription;
import drools.model.Symptom;
import drools.model.enums.DoctorType;
import drools.model.enums.DrugType;
import drools.model.reports.DialysisEvent;
import drools.model.reports.HeartBeatEvent;
import drools.model.reports.OxygenEvent;
import drools.service.IntensiveCareService;

@RestController
@Scope(value="application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntensiveCareResource {

	@Autowired
	IntensiveCareService intensiveCareService;
	
	@RequestMapping(value="/api/intensive-care/start", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public void startDaemon() {
		
		Chart c1 = new Chart(new ArrayList<Examination>(), null, 1);
		Chart c2 = new Chart(new ArrayList<Examination>(), null, 2);
		
		Patient hasDiabetes = new Patient("Milka", "Debelic", 123, new ArrayList<Allergen>());
		Patient hasKidneyDisease = new Patient("Rastko", "Drzic", 321, new ArrayList<Allergen>());
		
		Doctor d = new Doctor("Doca", "Dr Docic", 44444, "drDoca", "drDoca44444", DoctorType.ADMINISTRATOR);
	
		Symptom ss = new Symptom("Neki sim", 0);
		
		Examination diabExam = new Examination(10, new Date(), d, new ArrayList<Symptom>(), null, null);	
		Examination kidnExam = new Examination(20, new Date(), d, new ArrayList<Symptom>(), null, null);
		
		Disease diab = new Disease(88, "Dijabetes", new ArrayList<Symptom>());
		Disease kidn = new Disease(98, "Hronicna bubrezna bolest", new ArrayList<Symptom>());
		
		diab.getSymptoms().add(ss);
		kidn.getSymptoms().add(ss);
		
		Prescription pDiab = new Prescription(880, "Sto vise", null);
		Prescription pKidn = new Prescription(980, "Sto vise", null);
		
		Drug dDiab = new Drug(8800, "Insulin", new ArrayList<Ingredient>(), DrugType.OTHER);
		Drug dKidn = new Drug(9800, "Andol", new ArrayList<Ingredient>(), DrugType.OTHER);
		
		pDiab.setDrug(dDiab);
		pKidn.setDrug(dKidn);
		
		diabExam.setDisease(diab);
		kidnExam.setDisease(kidn);
		diabExam.setPrescription(pDiab);
		kidnExam.setPrescription(pKidn);
		
		c1.setPatient(hasDiabetes);
		c2.setPatient(hasKidneyDisease);
		
		c1.getExaminations().add(diabExam);
		c2.getExaminations().add(kidnExam);
		
		System.out.println(c1);
		System.out.println(c2);
		
		KieServices kss = KieServices.Factory.get();
        KieFileSystem kfs = kss.newKieFileSystem();
        kfs.write(ResourceFactory.newClassPathResource("intensiveCare/ic-rules.drl"));
        KieBuilder kbuilder = kss.newKieBuilder(kfs);
        kbuilder.buildAll();
        if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalArgumentException("Coudln't build knowledge module" + kbuilder.getResults());
        }
        KieContainer kContainer = kss.newKieContainer(kbuilder.getKieModule().getReleaseId());
        KieBaseConfiguration kbconf = kss.newKieBaseConfiguration();
        kbconf.setOption(EventProcessingOption.STREAM);
        KieBase kbase = kContainer.newKieBase(kbconf);
        KieSessionConfiguration ksconf2 = kss.newKieSessionConfiguration();
        ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession ks = kbase.newKieSession(ksconf2, null);
		ks.getAgenda().getAgendaGroup("alerts").setFocus();
		
		IntensiveCareReport icDiab = new IntensiveCareReport(true, true, true, c1);
		IntensiveCareReport icKidn = new IntensiveCareReport(true, true, true, c2);
		
		ks.insert(icDiab);
		ks.insert(icKidn);
		
		SessionPseudoClock clock = ks.getSessionClock();
		
		//Oxygen problem patient 2
		double startOxLevel = 100;
		
		System.out.println("===================================\nZapocet monitoring 1\n");
		for(int i=0; i<30; i++) {
			OxygenEvent oe = new OxygenEvent(c2, startOxLevel - i*2);
			ks.insert(oe);
			clock.advanceTime(30, TimeUnit.SECONDS);
			ks.fireAllRules();
			System.out.println("Drop ("+i*30+"s)");
			OxygenEvent oe1 = new OxygenEvent(c2, startOxLevel - i*2);
			ks.insert(oe1);
			clock.advanceTime(30, TimeUnit.SECONDS);
			System.out.println("Drop ("+i*30+"s)");
			ks.fireAllRules();
		}
		
		System.out.println("===================================\nZavrsen monitoring 1\n");
		System.out.println("Stanje pacijenta: "+icDiab.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icDiab.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icDiab.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icDiab.isUrinOk()+"\n");
		System.out.println("\t------------------------------------------------------\n");
		System.out.println("Stanje pacijenta: "+icKidn.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icKidn.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icKidn.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icKidn.isUrinOk()+"\n");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("===================================\nZapocet monitoring 2\n");
		for(int i=0; i<30; i++) {
			HeartBeatEvent oe = new HeartBeatEvent(c1);
			ks.insert(oe);
			clock.advanceTime(100, TimeUnit.MILLISECONDS);
			ks.fireAllRules();
			System.out.println("Beat ("+i*0.1+"s)");
		}
		
		System.out.println("===================================\nZavrsen monitoring 2\n");
		System.out.println("Stanje pacijenta: "+icDiab.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icDiab.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icDiab.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icDiab.isUrinOk()+"\n");
		System.out.println("\t------------------------------------------------------\n");
		System.out.println("Stanje pacijenta: "+icKidn.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icKidn.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icKidn.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icKidn.isUrinOk()+"\n");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		System.out.println("===================================\nZapocet monitoring 3\n");
		for(int i=0; i<30; i++) {
			DialysisEvent oe = new DialysisEvent(c2, 1);
			ks.insert(oe);
			clock.advanceTime(30, TimeUnit.MINUTES);
			ks.fireAllRules();
			System.out.println("Drop ("+i*30+"min)");
		}
		
		for(int i=0; i<30; i++) {
			HeartBeatEvent oe = new HeartBeatEvent(c2);
			ks.insert(oe);
			clock.advanceTime(500, TimeUnit.MILLISECONDS);
			ks.fireAllRules();
			HeartBeatEvent oe1 = new HeartBeatEvent(c2);
			ks.insert(oe1);
			clock.advanceTime(500, TimeUnit.MILLISECONDS);
			ks.fireAllRules();
			System.out.println("Beat ("+i*0.5+"s)");
		}
		
		System.out.println("===================================\nZavrsen monitoring 3\n");
		System.out.println("Stanje pacijenta: "+icDiab.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icDiab.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icDiab.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icDiab.isUrinOk()+"\n");
		System.out.println("\t------------------------------------------------------\n");
		System.out.println("Stanje pacijenta: "+icKidn.getChart().getPatient());
		System.out.println("\t*** Nivo kiseonika: "+icKidn.isOxygenOk()+"\n");
		System.out.println("\t*** Nivo otkucaja: "+icKidn.isHeartOk()+"\n");
		System.out.println("\t*** Nivo urina: "+icKidn.isUrinOk()+"\n");
	}
	
	
	@Bean
    public static KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("back-end","back-end-rules", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		return kContainer;
    }
	
	@RequestMapping(value = "/api/intensive-care", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Collection<Chart> getPatientsInIC(){
		return intensiveCareService.getPatientsInIC().values();
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public Chart placePatientInIC(@PathParam("id") Integer id) {
		return intensiveCareService.addPatientToIC(id);
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public Chart removePatientFromIC(@PathParam("id") Integer id) {
		return intensiveCareService.addPatientToIC(id);
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Chart checkPatientInIC(@PathParam("id") Integer id) {
		return intensiveCareService.checkIfInIC(id);
	}
}
