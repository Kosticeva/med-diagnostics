package drools.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import drools.model.enums.DoctorType;
import drools.model.enums.DrugType;
import drools.repository.ChartRepository;
import drools.repository.DiseaseRepository;
import drools.repository.DoctorRepository;
import drools.repository.DrugRepository;
import drools.repository.ExaminationRepository;
import drools.repository.IngredientRepository;
import drools.repository.PatientRepository;
import drools.repository.PrescriptionRepository;
import drools.repository.SymptomRepository;


@SpringBootApplication
public class SampleApp {
	
	private static Logger log = LoggerFactory.getLogger(SampleApp.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SampleApp.class, args);
        
        doStuff();
		//doStuff1();
		//doStuff2();
		//doStuff3();
	}
	
	public static void doStuff3() {
		KieSession ks = kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("reports").setFocus();
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		prehlada.setExaminations(new ArrayList<Examination>());
		
		Doctor dd = new Doctor("Kiki", "Riki", 1234, "aa124", "xxx", DoctorType.REGULAR);
		
		Disease d = new Disease(23, "Dijabetes", null);
		
		try {
			for(int i=0; i<6; i++) {
				Examination e = new Examination();
				e.setSymptoms(new ArrayList<Symptom>());
				e.setDoctor(dd);
				e.setDisease(d);
				e.setId(i*56);
				e.setDate(new Date());
				prehlada.getExaminations().add(e);
				Thread.sleep(i*1000);
			}
		}catch(Exception e) {
			
		}
		
		Examination eee = new Examination(23, new Date(), null, null, null, null);
		ks.setGlobal("noww", eee);
		
		for(int i=0; i<prehlada.getExaminations().size(); i++) {
			System.out.println(eee.getDate().getTime()-prehlada.getExaminations().get(i).getDate().getTime());
		}
		
		FactHandle f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		
		
	}
	
	
	@Bean
    public static KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("back-end","back-end-rules", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		return kContainer;
    }
	
	public static void doStuff() {
		KieSession ks = kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		/*Date dz = new Date();
		ks.setGlobal("currDate", dz);*/
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		
		Doctor dd = new Doctor("Kiki", "Riki", 1234, "aa124", "xxx", DoctorType.REGULAR);
		//prehlada
		
		Examination m = new Examination();
		m.setDate(new Date());
		m.setId(243);
		m.setDoctor(dd);
	
		List<Symptom> sysms = new ArrayList<Symptom>();
		Symptom s1 = new Symptom("Glavobolja", 1);
		Symptom s2 = new Symptom("Kijanje", 2);
		Symptom s3 = new Symptom("Kasalj", 3);
		Symptom s4 = new Symptom("Bol u grlu", 4);
		Symptom s5 = new Symptom("Dijareja", 5);
		Symptom s66 = new Symptom("Temperatura veca od 38", 77);
		Symptom s67 = new Symptom("Curenje iz nosa", 654);
		Symptom s77 = new Symptom("Drhvatica", 614);
		
		sysms.add(s1);
		sysms.add(s2);
		sysms.add(s3);
		sysms.add(s4);
		sysms.add(s66);
		sysms.add(s67);
		sysms.add(s77);
		
		m.setSymptoms(sysms);
		
		prehlada.setExaminations(new ArrayList<Examination>());
		prehlada.getExaminations().add(m);
		
		FactHandle f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		System.out.println(prehlada);
		
		//upala krajnika
		Examination mm = new Examination();
		mm.setDate(new Date());
		mm.setId(2433);
		mm.setDoctor(dd);
	
		List<Symptom> sysms1 = new ArrayList<Symptom>();
		Symptom s21 = new Symptom("Bol koji se siri do usiju", 6);
		Symptom s41 = new Symptom("Temperatura od 40 do 41", 7);
		Symptom s51 = new Symptom("Drhtavica", 8);
		Symptom s61 = new Symptom("Gubitak apetita", 9);
		Symptom s71 = new Symptom("Umor", 10);
		Symptom s81 = new Symptom("Zuti sekret iz nosa", 11);
		
		sysms1.add(s1);
		sysms1.add(s21);
		sysms1.add(s3);
		sysms1.add(s41);
		sysms1.add(s51);
		sysms1.add(s61);
		sysms1.add(s71);
		sysms1.add(s81);
		
		mm.setSymptoms(sysms1);
		
		prehlada.getExaminations().add(mm);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		//sinusna infekcija
		Examination MM = new Examination();
		MM.setDate(new Date());
		MM.setId(24333);
		MM.setDoctor(dd);
	
		List<Symptom> sysms2 = new ArrayList<Symptom>();
		
		Symptom s111 = new Symptom("Oticanje oko ociju", 12);
		
		sysms2.add(s111);
		sysms2.add(s1);
		sysms2.add(s81);
		sysms2.add(s4);
		sysms2.add(s3);
		
		MM.setSymptoms(sysms2);
		
		prehlada.getExaminations().add(MM);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		
		ks.getAgenda().getAgendaGroup("allergy-warning").setFocus();
		
		Ingredient ii = new Ingredient(2, "Metil-ultra-sulfat");
		
		Drug d = new Drug(321, "Penicilin", new ArrayList<Ingredient>(), DrugType.ANTIBIOTIC);
		d.getIngredients().add(ii);
		
		p.getAllergens().add(ii);
		
		Prescription pp = new Prescription(312, "2 puta dnevno po 2", d);
		MM.setPrescription(pp);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		
	}
	
	public static void doStuff1() {
		KieSession ks = kieContainer().newKieSession();
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		prehlada.setExaminations(new ArrayList<Examination>());
		
		Doctor dd = new Doctor("Kiki", "Riki", 1234, "aa124", "xxx", DoctorType.REGULAR);
		
		Symptom s = new Symptom("Povisen krvni pritisak", 123);
		
		try {
			Examination e1 = new Examination(12, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e2 = new Examination(22, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e3 = new Examination(32, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e4 = new Examination(42, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e5 = new Examination(52, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e6 = new Examination(62, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e7 = new Examination(72, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e8 = new Examination(82, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e9 = new Examination(92, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e0 = new Examination(02, new Date(), dd, new ArrayList<Symptom>(), null, null);
			
			e1.getSymptoms().add(s);
			e2.getSymptoms().add(s);
			e3.getSymptoms().add(s);
			e4.getSymptoms().add(s);
			e5.getSymptoms().add(s);
			e6.getSymptoms().add(s);
			e7.getSymptoms().add(s);
			e8.getSymptoms().add(s);
			e9.getSymptoms().add(s);
			e0.getSymptoms().add(s);
			
			prehlada.getExaminations().add(e1);
			prehlada.getExaminations().add(e2);
			prehlada.getExaminations().add(e3);
			prehlada.getExaminations().add(e4);
			prehlada.getExaminations().add(e5);
			prehlada.getExaminations().add(e6);
			prehlada.getExaminations().add(e7);
			prehlada.getExaminations().add(e8);
			prehlada.getExaminations().add(e9);
			prehlada.getExaminations().add(e0);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FactHandle f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		
		Examination mmm = new Examination(231, new Date(), dd, new ArrayList<Symptom>(), null, null);
		
		Symptom s1 = new Symptom("Cesto uriniranje", 123);
		Symptom s2 = new Symptom("Gubitak telesne tezine", 333);
		Symptom s3 = new Symptom("Mucnina i povracanje", 223);
		Symptom s4 = new Symptom("Zamor", 423);
		
		mmm.getSymptoms().add(s1);
		mmm.getSymptoms().add(s2);
		mmm.getSymptoms().add(s3);
		mmm.getSymptoms().add(s4);
		
		prehlada.getExaminations().add(mmm);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
	}
	
	public static void doStuff2() {
		KieSession ks = kieContainer().newKieSession();
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		prehlada.setExaminations(new ArrayList<Examination>());
		
		Doctor dd = new Doctor("Kiki", "Riki", 1234, "aa124", "xxx", DoctorType.REGULAR);
	
		//za dijabetes
		Examination mmm = new Examination(231, new Date(), dd, new ArrayList<Symptom>(), null, null);
		
		Symptom s1 = new Symptom("Cesto uriniranje", 123);
		Symptom s2 = new Symptom("Gubitak telesne tezine", 333);
		Symptom s3 = new Symptom("Mucnina i povracanje", 223);
		Symptom s4 = new Symptom("Zamor", 423);
		
		mmm.getSymptoms().add(s1);
		mmm.getSymptoms().add(s2);
		mmm.getSymptoms().add(s3);
		mmm.getSymptoms().add(s4);
		
		prehlada.getExaminations().add(mmm);
		
		FactHandle f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		Ingredient ii = new Ingredient(2, "Metil-ultra-sulfat");
		
		Drug d = new Drug(321, "Penicilin", new ArrayList<Ingredient>(), DrugType.ANTIBIOTIC);
		d.getIngredients().add(ii);
		
		Prescription pp = new Prescription(312, "2 puta dnevno po 2", d);
		mmm.setPrescription(pp);
		
		Examination m = new Examination(222, new Date(), dd, new ArrayList<Symptom>(), null, null);
		
		Symptom s5 = new Symptom("Nocturia", 124);
		Symptom s6 = new Symptom("Otoci nogu i zglobova", 125);
		Symptom s7 = new Symptom("Gusenje", 241);
		Symptom s8 = new Symptom("Dijareja", 31);
		
		m.getSymptoms().add(s7);
		m.getSymptoms().add(s5);
		//m.getSymptoms().add(s6);
		
		prehlada.getExaminations().add(m);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
		
		Examination mM = new Examination(223, new Date(), dd, new ArrayList<Symptom>(), null, null);
		
		mM.getSymptoms().add(s4);
		mM.getSymptoms().add(s8);
		///mM.getSymptoms().add(s7);
		
		prehlada.getExaminations().add(mM);
		
		f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
		System.out.println(prehlada);
	}
}
