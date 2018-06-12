package drools.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Chart;
import drools.model.Examination;
import drools.model.Patient;
import drools.repository.ChartRepository;

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
		return chartRepository.getOne(id);
	}
	
	@Transactional
	public List<Chart> findAll(){
		return chartRepository.findAll();
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
		
		if(chart.getPatient() == null) {
			System.out.println("Nema pacijenta");
			return null;
		}
		
		Patient PP = patientService.findById(chart.getPatient().getId());
		
		if(PP == null) {
			System.out.println("Pacijent nepostojec");
			return null;
		}
		
		if(!chartRepository.getOne(chart.getId()).getPatient().equals(chart.getPatient())) {
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
		
		return chartRepository.save(chart);
	}
	
	@Transactional
	public void deleteChart(int id) throws SQLException{
		chartRepository.deleteById(id);
	}
}
