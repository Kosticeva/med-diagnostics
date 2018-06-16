package drools.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Allergy;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class IntensiveCareServiceTest {

	@Test
	@Transactional
	public void startDaemon() {
		 		
 		Chart c1 = new Chart(new ArrayList<Examination>(), null, 1);
 		Chart c2 = new Chart(new ArrayList<Examination>(), null, 2);
 		
 		Patient hasDiabetes = new Patient("Milka", "Debelic", 123, new ArrayList<Allergy>());
 		Patient hasKidneyDisease = new Patient("Rastko", "Drzic", 321, new ArrayList<Allergy>());
 		
 		Doctor d = new Doctor("Doca", "Dr Docic", 44444, "drDoca", "drDoca44444", DoctorType.ADMINISTRATOR);
 		
 		Examination diabExam = new Examination(10, new Date(), d, new ArrayList<Symptom>(), null, null);	
 		Examination kidnExam = new Examination(20, new Date(), d, new ArrayList<Symptom>(), null, null);
 		
 		Disease diab = new Disease(88, "Dijabetes");
 		Disease kidn = new Disease(98, "Hronicna bubrezna bolest");
 		
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
 		for(int i=0; i<30; i++ ) {
 			OxygenEvent oe = new OxygenEvent(c2, startOxLevel - i*2);
 			ks.insert(oe);
 			clock.advanceTime(30, TimeUnit.SECONDS);
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 			System.out.println("Drop (" +  i*30 + 30 +  "s)");
 			OxygenEvent oe1 = new OxygenEvent(c2, startOxLevel - i*2);
 			ks.insert(oe1);
 			clock.advanceTime(30, TimeUnit.SECONDS);
 			System.out.println("Drop (" +  i*30 + 60+  "s)");
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 		}
 		
 		System.out.println("===================================\nZavrsen monitoring 1\n");
 		System.out.println("Stanje pacijenta: " +  icDiab.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: " +  icDiab.isOxygenOk() +  "\n");
 		System.out.println("\t*** Nivo otkucaja: " +  icDiab.isHeartOk() +  "\n");
 		System.out.println("\t*** Nivo urina: "  + icDiab.isUrinOk() +  "\n");
 		System.out.println("\t------------------------------------------------------\n");
 		System.out.println("Stanje pacijenta: "  + icKidn.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: "  + icKidn.isOxygenOk() +  "\n");
 		System.out.println("\t*** Nivo otkucaja: "  + icKidn.isHeartOk() +  "\n");
 		System.out.println("\t*** Nivo urina: "  + icKidn.isUrinOk()  + "\n");
 		
 		
 		assertEquals(icKidn.isOxygenOk(), false);
 		////////////////////////////////////////////////////////////////////////////////////////////////////////
 		
 		System.out.println("===================================\nZapocet monitoring 2\n");
 		for(int i=0; i<30; i++ ) {
 			HeartBeatEvent oe = new HeartBeatEvent(c1);
 			ks.insert(oe);
 			clock.advanceTime(100, TimeUnit.MILLISECONDS);
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 			System.out.println("Beat (" +  i*0.1 +  "s)");
 		}
 		
 		System.out.println("===================================\nZavrsen monitoring 2\n");
 		System.out.println("Stanje pacijenta: " +  icDiab.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: " +  icDiab.isOxygenOk() +  "\n");
 		System.out.println("\t*** Nivo otkucaja: " +  icDiab.isHeartOk() +  "\n");
 		System.out.println("\t*** Nivo urina: " +  icDiab.isUrinOk() +  "\n");
 		System.out.println("\t------------------------------------------------------\n");
 		System.out.println("Stanje pacijenta: " +  icKidn.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: " +  icKidn.isOxygenOk() +  "\n");
 		System.out.println("\t*** Nivo otkucaja: " +  icKidn.isHeartOk()  + "\n");
 		System.out.println("\t*** Nivo urina: " +  icKidn.isUrinOk() +  "\n");
 		
 		assertEquals(icDiab.isHeartOk(), false);
 		////////////////////////////////////////////////////////////////////////////////////////////////////////
 				
 		System.out.println("===================================\nZapocet monitoring 3\n");
 		for(int i=0; i<30; i++ ) {
 			DialysisEvent oe = new DialysisEvent(c2, 1);
 			ks.insert(oe);
 			clock.advanceTime(30, TimeUnit.MINUTES);
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 			System.out.println("Drop (" +  i*30  + "min)");
 		}
 		
 		for(int i=0; i<30; i++ ) {
 			HeartBeatEvent oe = new HeartBeatEvent(c2);
 			ks.insert(oe);
 			clock.advanceTime(500, TimeUnit.MILLISECONDS);
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 			System.out.println("Beat ("+i+0.5+"s)");
 			HeartBeatEvent oe1 = new HeartBeatEvent(c2);
 			ks.insert(oe1);
 			clock.advanceTime(500, TimeUnit.MILLISECONDS);
 			ks.getAgenda().getAgendaGroup("alerts").setFocus();
 			ks.fireAllRules();
 			System.out.println("Beat ("+i+1+"s)");
 		}
 		
 		System.out.println("===================================\nZavrsen monitoring 3\n");
 		System.out.println("Stanje pacijenta: " +  icDiab.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: " +  icDiab.isOxygenOk() +  "\n");
 		System.out.println("\t*** Nivo otkucaja: " +  icDiab.isHeartOk() +  "\n");
 		System.out.println("\t*** Nivo urina: " +  icDiab.isUrinOk()  + "\n");
 		System.out.println("\t------------------------------------------------------\n");
 		System.out.println("Stanje pacijenta: " +  icKidn.getChart().getPatient());
 		System.out.println("\t*** Nivo kiseonika: " +  icKidn.isOxygenOk()  + "\n");
 		System.out.println("\t*** Nivo otkucaja: " +  icKidn.isHeartOk()  + "\n");
 		System.out.println("\t*** Nivo urina: " +  icKidn.isUrinOk()  + "\n");
 		
 		assertEquals(icKidn.isUrinOk(), false);
 	}
	
}
