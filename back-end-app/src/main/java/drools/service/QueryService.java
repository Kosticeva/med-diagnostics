package drools.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.SampleApp;
import drools.model.Chart;
import drools.model.Disease;
import drools.model.Examination;
import drools.model.Link;
import drools.model.Symptom;

@Service
public class QueryService {

	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	ChartService chartService;
	
	@Autowired
	LinkService linkService;
	
	public Disease getMostProbable(Examination exam) {
		
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		Chart ch = chartService.findByExamId(exam.getId());
		if(ch == null) {
			return null;
		}
		
		ks.insert(ch);
		ks.insert(exam);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Disease.class));
		
		List<Disease> diseases = new ArrayList<Disease>();
		for(Object w: coll.toArray()) {
			diseases.add((Disease)w);
		}
		
		if(diseases.size() != 0)
			return diseases.get(0);
		return null;
	}
	
	public List<Disease> getAllPossibleDiseases(List<Symptom> symptoms){
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("symptom-bc").setFocus();
		
		for(Symptom s: symptoms) {
			ks.insert(symptomService.findById(s.getId()));
		}
		
		for(Link l: linkService.getAllLinks()) {
			ks.insert(l);
		}
		
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
		ks.getAgenda().getAgendaGroup("disease-bc").setFocus();
		
		ks.insert(diseaseService.findById(disease.getId()));
		
		for(Link l: linkService.getAllLinks()) {
			ks.insert(l);
		}
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Symptom.class));
		
		List<Symptom> symptoms = new ArrayList<Symptom>();
		for(Object w: coll.toArray()) {
			 symptoms.add((Symptom)w);
		}
		
		return symptoms;
	}
	
}
