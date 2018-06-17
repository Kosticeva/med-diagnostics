package drools.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import drools.model.Chart;
import drools.model.Examination;
import drools.model.Patient;
import drools.repository.ChartRepository;
import drools.resource.AuthenticationResource;

@Service
public class ChartService {
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ExaminationService examinationService;
	
	@Transactional
	public Chart findById(int id) {
		Optional<Chart> c = chartRepository.findById(id);
		if(c.isPresent()) {
			return c.get();
		}
		
		return null;
	}
	
	@Transactional
	public List<Chart> findByPatientName(String name){
		List<Patient> patients = patientService.findByName(name);
		List<Chart> charts = findAll();

		List<Chart> retVal = new ArrayList<Chart>();
		for(Patient p: patients){
			for(Chart c: charts){
				if(c.getPatient().equals(p)){
					retVal.add(c);
				}
			}
		}

		return retVal;
	}

	@Transactional
	public List<Chart> findAll(){
		return chartRepository.findAll();
	}

	@Transactional
	public Chart findByExamId(Integer examId){
		List<Chart> allCharts = findAll();

		for(Chart c: allCharts){
			for(Examination e: c.getExaminations()){
				if(e.getId().equals(examId)){
					return c;
				}
			}
		}

		return null;
	}
	
	@Transactional
	public Chart createNewChart(Chart chart) {
		if(chart.getPatient() == null) {
			System.out.println("Nema pacijenta");
			return null;
		}
		
		Patient PP = patientService.findById(chart.getPatient().getId());
		
		if(PP == null) {
			System.out.println("Pacijent nepostojec");
			return null;
		}

		chart.setPatient(PP);
		
		if(chart.getExaminations().size() > 0) {
			System.out.println("Postoje pregledi u praznom kartonu");
			return null;
		}
		
		return chartRepository.save(chart);
	}
	
	@Transactional
	public Chart updateChart(Chart chart) {
		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null){
			f = ks.getFactHandle(findById(chart.getId()));
		}

		if(chart.getPatient() == null) {
			System.out.println("Nema pacijenta");
			return null;
		}
		
		Patient PP = patientService.findById(chart.getPatient().getId());
		
		if(PP == null) {
			System.out.println("Pacijent nepostojec");
			return null;
		}
		
		if(chartRepository.getOne(chart.getId()).getPatient() != null && !chartRepository.getOne(chart.getId()).getPatient().equals(chart.getPatient())) {
			System.out.println("Promenjen pacijent");
			return null;
		}
		
		chart.setPatient(PP);
		
		if(chart.getExaminations() == null) {
			System.out.println("Nema pregleda");
			return null;
		}
		
		List<Examination> newExams = new ArrayList<Examination>();

		for(Examination ee: chart.getExaminations()) {
			Examination EE = examinationService.findById(ee.getId());
			
			if(EE == null) {
				System.out.println("Nepostojeci pregled");
				return null;
			}
				
			newExams.add(EE);
		}
		
		chart.setExaminations(newExams);
		
		if(f != null){
			ks.update(f, chart);
		}

		return chartRepository.save(chart);
	}
	
	@Transactional
	public void deleteChart(int id) throws SQLException{
		chartRepository.deleteById(id);
	}
}
