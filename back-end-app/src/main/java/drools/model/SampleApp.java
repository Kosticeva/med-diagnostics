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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import drools.model.enums.DoctorType;


@SpringBootApplication
public class SampleApp {
	
	private static Logger log = LoggerFactory.getLogger(SampleApp.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SampleApp.class, args);
        
        //doStuff();
		//doStuff1();
		doStuff2();
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
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		
		Doctor dd = new Doctor("Kiki", "Riki", "aa124", "aa124", "xxx", DoctorType.REGULAR);
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
		
		sysms.add(s1);
		sysms.add(s2);
		sysms.add(s3);
		sysms.add(s4);
		sysms.add(s5);
		
		m.setSymptoms(sysms);
		
		prehlada.setExaminations(new ArrayList<Examination>());
		prehlada.getExaminations().add(m);
		
		FactHandle f = ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		ks.delete(f);
		
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
		
	}
	
	public static void doStuff1() {
		KieSession ks = kieContainer().newKieSession();
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		prehlada.setExaminations(new ArrayList<Examination>());
		
		Doctor dd = new Doctor("Kiki", "Riki", "aa124", "aa124", "xxx", DoctorType.REGULAR);
		
		Symptom s = new Symptom("Povisen krvni pritisak", 123);
		
		try {
			Examination e1 = new Examination(12, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e2 = new Examination(22, new Date(), dd, new ArrayList<Symptom>(), null, null);
			Thread.sleep(5000);
			Examination e3 = new Examination(32, new Date(), dd, new ArrayList<Symptom>(), null, null);
			/*Thread.sleep(5000);
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
			*/
			e1.getSymptoms().add(s);
			e2.getSymptoms().add(s);
			e3.getSymptoms().add(s);
			/*e4.getSymptoms().add(s);
			e5.getSymptoms().add(s);
			e6.getSymptoms().add(s);
			e7.getSymptoms().add(s);
			e8.getSymptoms().add(s);
			e9.getSymptoms().add(s);
			e0.getSymptoms().add(s);*/
			
			prehlada.getExaminations().add(e1);
			prehlada.getExaminations().add(e2);
			prehlada.getExaminations().add(e3);
			/*prehlada.getExaminations().add(e4);
			prehlada.getExaminations().add(e5);
			prehlada.getExaminations().add(e6);
			prehlada.getExaminations().add(e7);
			prehlada.getExaminations().add(e8);
			prehlada.getExaminations().add(e9);
			prehlada.getExaminations().add(e0);*/
		
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
		
		Doctor dd = new Doctor("Kiki", "Riki", "aa124", "aa124", "xxx", DoctorType.REGULAR);
	}
}
