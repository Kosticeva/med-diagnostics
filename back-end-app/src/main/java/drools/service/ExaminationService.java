package drools.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Disease;
import drools.model.Doctor;
import drools.model.Examination;
import drools.model.Prescription;
import drools.model.Symptom;
import drools.repository.ExaminationRepository;

@Service
public class ExaminationService {
	
	@Autowired
	ExaminationRepository examinationRepository;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@Autowired
	SymptomService symptomService;
	
	@Transactional
	public Examination findById(int id) {
		return examinationRepository.getOne(id);
	}
	
	@Transactional
	public List<Examination> findAll(){
		return examinationRepository.findAll();
	}
	
	@Transactional
	public Examination createNewExamination(Examination examination) {
		
		if(examination.getDate() != null) {
			System.out.println("Zadan datum za pregled");
			return null;
		}
		
		if(examination.getDoctor() == null) {
			System.out.println("Nema doktor");
			return null;
		}
		
		Doctor DD = doctorService.findById(examination.getDoctor().getLicenceId());
		
		if(DD == null) {
			System.out.println("Nepostojeci doktor");
			return null;
		}
		
		examination.setDoctor(DD);
		
		if(examination.getDisease() == null) {
			System.out.println("Nema bolest");
			return null;
		}
		
		Disease DDD = diseaseService.findById(examination.getDisease().getId());
		
		if(DDD == null) {
			System.out.println("Nepostojeca bolest");
			return null;
		}
		
		examination.setDisease(DDD);
		
		if(examination.getPrescription() == null) {
			System.out.println("Nema recept");
			return null;
		}
		
		Prescription PP = prescriptionService.findById(examination.getPrescription().getId());
		
		if(PP == null) {
			System.out.println("Nepostojeci recept");
			return null;
		}
		
		examination.setPrescription(PP);
		
		if(examination.getSymptoms() == null || examination.getSymptoms().size() == 0) {
			System.out.println("Nema simptoma");
			return null;
		}
		
		List<Symptom> newSymps = new ArrayList<Symptom>();
		for(Symptom ss: examination.getSymptoms()) {
			Symptom SS = symptomService.findById(ss.getId());
			
			if(SS == null) {
				System.out.println("Nepostojeci simptom");
				return null;
			}
			
			newSymps.add(SS);
		}
		
		examination.setSymptoms(newSymps);
		
		examination.setDate(new Date());
		
		return examinationRepository.save(examination);
	}
	
	@Transactional
	public Examination updateExamination(Examination examination) {
		if(examination.getDate() == null) {
			System.out.println("Nepostojeci datum za pregled");
			return null;
		}
		
		if(examination.getDoctor() == null) {
			System.out.println("Nema doktor");
			return null;
		}
		
		Doctor DD = doctorService.findById(examination.getDoctor().getLicenceId());
		
		if(DD == null) {
			System.out.println("Nepostojeci doktor");
			return null;
		}
		
		examination.setDoctor(DD);
		
		if(examination.getDisease() == null) {
			System.out.println("Nema bolest");
			return null;
		}
		
		Disease DDD = diseaseService.findById(examination.getDisease().getId());
		
		if(DDD == null) {
			System.out.println("Nepostojeca bolest");
			return null;
		}
		
		examination.setDisease(DDD);
		
		if(examination.getPrescription() == null) {
			System.out.println("Nema recept");
			return null;
		}
		
		Prescription PP = prescriptionService.findById(examination.getPrescription().getId());
		
		if(PP == null) {
			System.out.println("Nepostojeci recept");
			return null;
		}
		
		examination.setPrescription(PP);
		
		if(examination.getSymptoms() == null || examination.getSymptoms().size() == 0) {
			System.out.println("Nema simptoma");
			return null;
		}
		
		List<Symptom> newSymps = new ArrayList<Symptom>();
		for(Symptom ss: examination.getSymptoms()) {
			Symptom SS = symptomService.findById(ss.getId());
			if(SS == null) {
				System.out.println("Nepostojeci simptom");
				return null;
			}
			
			newSymps.add(SS);
		}
		
		examination.setSymptoms(newSymps);
		
		return examinationRepository.save(examination);
	}
	
	@Transactional
	public void deleteExamination(int id) throws SQLException {
		examinationRepository.deleteById(id);
	}
}