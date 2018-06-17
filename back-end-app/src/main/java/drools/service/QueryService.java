package drools.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;


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
	
	public Disease getMostProbable(Examination exam, KieSession ks) {
		
		System.out.println(exam);
		
		ks.getAgenda().getAgendaGroup("diagnose").setFocus();
		
		Chart ch = chartService.findByExamId(exam.getId());
		if(ch == null) {
			return null;
		}
		
		System.out.println(ch);
		
		FactHandle f1 = ks.insert(ch);
		FactHandle f2 =ks.insert(exam);
		
		List<Disease> diseases = new ArrayList<>();
		ks.setGlobal("diseaseCont", diseases);

		ks.fireAllRules();
		ks.delete(f1);
		ks.delete(f2);

		if(diseases.size() != 0)
			return diseaseService.findByStartName(diseases.get(0).getName()).get(0);
		
		System.out.println("Vel je 0 :(");
		return null;
	}
	
	public List<Disease> getAllPossibleDiseases(List<Symptom> symptoms, KieSession ks){
		ks.getAgenda().getAgendaGroup("symptom-bc").setFocus();
		
		List<FactHandle> sympHandles = new ArrayList<>();
		for(Symptom s: symptoms) {
			sympHandles.add(ks.insert(symptomService.findById(s.getId())));
		}
		
		List<FactHandle> linkHandles = new ArrayList<>();
		for(Link l: linkService.getAllLinks()) {
			linkHandles.add(ks.insert(l));
		}

		ks.fireAllRules();
		
		for(FactHandle ff: sympHandles){
			ks.delete(ff);
		}

		for(FactHandle ff: linkHandles){
			ks.delete(ff);
		}

		Collection<?> diss = ks.getObjects(new ClassObjectFilter(Disease.class));
		List<Disease> diseases = new ArrayList<>();

		for(Object o: diss){
			diseases.add((Disease) o);
			ks.delete(ks.getFactHandle(o));
		}

		return diseases;
	}
	
	public List<Symptom> getDiseaseSymptoms(Disease disease, KieSession ks){
		ks.getAgenda().getAgendaGroup("disease-bc").setFocus();
		
		FactHandle df =ks.insert(diseaseService.findById(disease.getId()));
		System.out.println("Simptomi za bolest: " + disease.getId());

		List<FactHandle> linkHandles = new ArrayList<>();
		for(Link l: linkService.getAllLinks()) {
			linkHandles.add(ks.insert(l));
		}

		System.out.println("Broj linkova: "+linkHandles.size());
		List<Symptom> symptoms = new ArrayList<>();
		ks.setGlobal("sympCont", symptoms);

		ks.fireAllRules();
		ks.delete(df);

		for(FactHandle ff: linkHandles){
			ks.delete(ff);
		}
		
		return symptoms;
	}
	
}
