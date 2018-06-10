package drools.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import drools.SampleApp;
import drools.model.Chart;
import drools.model.Disease;
import drools.model.Symptom;

@Service
public class QueryService {

	public Disease getMostProbable(Chart chart) {
		
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		ks.insert(chart);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Disease.class));
		
		List<Disease> diseases = new ArrayList<Disease>();
		for(Object w: coll.toArray()) {
			diseases.add((Disease)w);
		}
		
		return diseases.get(0);
	}
	
	public List<Disease> getAllPossibleDiseases(List<Symptom> symptoms){
		KieSession ks = SampleApp.kieContainer().newKieSession();
		//ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		ks.insert(symptoms);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Disease.class));
		
		List<Disease> diseases = new ArrayList<Disease>();
		for(Object w: coll.toArray()) {
			diseases.add((Disease)w);
		}
		
		return diseases;
	}
	
	public List<Symptom> getDiseaseSymptoms(Disease disease){
		KieSession ks = SampleApp.kieContainer().newKieSession();
		//ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		ks.insert(disease);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Symptom.class));
		
		List<Symptom> symptoms = new ArrayList<Symptom>();
		for(Object w: coll.toArray()) {
			 symptoms.add((Symptom)w);
		}
		
		return symptoms;
	}
	 
}
