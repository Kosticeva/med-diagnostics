package drools.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.SampleApp;
import drools.model.Allergy;
import drools.model.Chart;
import drools.model.Drug;
import drools.model.Patient;

@Service
public class ReportsService {

	@Autowired
	ChartService chartService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DrugService drugService;
	
	public List<Patient> findChronics(){
		
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("reports-chronics").setFocus();
		
		List<Chart> charts = chartService.findAll();
		
		ks.insert(charts);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Patient.class));
		
		List<Patient> chronics = new ArrayList<Patient>();
		for(Object w: coll.toArray()) {
			chronics.add((Patient)w);
		}
		
		return chronics;
	}
	
	public List<Patient> findAddicts(){
		
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("reports-addicts").setFocus();
		
		List<Chart> charts = chartService.findAll();
		
		ks.insert(charts);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Patient.class));
		
		List<Patient> addicts = new ArrayList<Patient>();
		for(Object w: coll.toArray()) {
			addicts.add((Patient)w);
		}
		
		return addicts;
	}
	
	public List<Patient> findWeaks(){
		
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("reports-weaks").setFocus();
		
		List<Chart> charts = chartService.findAll();
		
		ks.insert(charts);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Patient.class));
		
		List<Patient> weaks = new ArrayList<Patient>();
		for(Object w: coll.toArray()) {
			weaks.add((Patient)w);
		}
		
		return weaks;
	}
	
	public List<Allergy> checkAllergyWarnings(Integer id, Drug drug){
		KieSession ks = SampleApp.kieContainer().newKieSession();
		ks.getAgenda().getAgendaGroup("allergy-warning").setFocus();
		
		Patient p = patientService.findById(id);
		drug = drugService.findById(drug.getId());
		ks.insert(drug);
		ks.insert(p);
		
		ks.fireAllRules();
		
		Collection<?> coll = ks.getObjects(new ClassObjectFilter(Allergy.class));
		
		List<Allergy> allergies = new ArrayList<Allergy>();
		for(Object w: coll.toArray()) {
			allergies.add((Allergy)w);
		}
		
		return allergies;
	}
	
}
