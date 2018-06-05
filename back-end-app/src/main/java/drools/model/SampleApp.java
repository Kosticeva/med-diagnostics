package drools.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
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

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
        log.info(sb.toString());
        
        doShit();
	}
	
	@Bean
    public static KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("back-end","back-end-rules", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		return kContainer;
    }
	
	public static void doShit() {
		KieSession ks = kieContainer().newKieSession();
		
		Chart prehlada = new Chart();
		Patient p = new Patient("Jelena", "Kostic", 12, new ArrayList<Allergen>());
		prehlada.setPatient(p);
		
		Doctor dd = new Doctor("Kiki", "Riki", "aa124", "aa124", "xxx", DoctorType.REGULAR);
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
		
		ks.insert(prehlada);
		
		System.out.println(ks.fireAllRules());
		
		if(m.getDisease() != null) {
			System.out.println(m.getDisease().getName());
		}
		
	}
	/*
	 * KieServices ks = KieServices.Factory.get();
	 * KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
	 * KieScanner kScanner = ks.newKieScanner(kContainer);
	 * kScanner.start(10_000);
	 * KieSession kSession = kContainer.newKieSession();
	 */
}
