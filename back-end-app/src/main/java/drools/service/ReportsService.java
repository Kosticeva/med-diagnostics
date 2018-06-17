package drools.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.kie.api.runtime.rule.FactHandle;

import drools.model.Allergy;
import drools.model.Chart;
import drools.model.Drug;
import drools.model.Patient;
import drools.model.reports.PatientReport;

@Service
public class ReportsService {

	@Autowired
	ChartService chartService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	DrugService drugService;
	
	public List<Patient> findChronics(KieSession ks){
		
		ks.getAgenda().getAgendaGroup("reports-chronics").setFocus();
		
		List<Chart> charts = chartService.findAll();
		List<FactHandle> chartsF = new ArrayList<>();

		for(Chart c: charts) {
			chartsF.add(ks.insert(c));
		}

		List<Patient> chronics = new ArrayList<>();
		ks.setGlobal("chronics", chronics);
		
		ks.fireAllRules();

		ks.setGlobal("chronics", null);
		
		for(FactHandle ff: chartsF){
			ks.delete(ff);
		}
		
		return chronics;
	}
	
	public List<Patient> findAddicts(KieSession ks){
		
		ks.getAgenda().getAgendaGroup("reports-addicts").setFocus();
		
		List<Chart> charts = chartService.findAll();
		List<FactHandle> chartsF = new ArrayList<>();

		for(Chart c: charts) {
			chartsF.add(ks.insert(c));
			System.out.println(c);
		}

		List<Patient> addicts = new ArrayList<>();
		ks.setGlobal("addicts", addicts);

		ks.fireAllRules();
		
		ks.setGlobal("addicts", null);
		for(FactHandle ff: chartsF){
			ks.delete(ff);
		}

		System.out.println("XXX");
		for(Patient addict: addicts){
			System.out.println(addict);
		}
		System.out.println("XXX");
		
		return addicts;
	}
	
	public List<Patient> findWeaks(KieSession ks){
		
		ks.getAgenda().getAgendaGroup("reports-weaks").setFocus();
		
		List<Chart> charts = chartService.findAll();
		List<FactHandle> chartsF = new ArrayList<>();

		for(Chart c: charts) {
			chartsF.add(ks.insert(c));
		}
		
		List<Patient> weaks = new ArrayList<>();
		ks.setGlobal("weaks", weaks);
		
		ks.fireAllRules();
		
		ks.setGlobal("weaks", null);
		for(FactHandle ff: chartsF){
			ks.delete(ff);
		}
		
		return weaks;
	}
	
	public List<Allergy> checkAllergyWarnings(Integer id, Drug drug, KieSession ks){
		ks.getAgenda().getAgendaGroup("allergy-warning").setFocus();
		
		Patient p = patientService.findById(id);
		drug = drugService.findById(drug.getId());
		FactHandle fd = ks.insert(drug);
		FactHandle fp = ks.insert(p);

		List<Allergy> allergies = new ArrayList<>();
		ks.setGlobal("allergyCont", allergies);

		ks.fireAllRules();

		ks.setGlobal("allergyCont", null);

		ks.delete(fd);
		ks.delete(fp);
		
		return allergies;
	}
	
}
