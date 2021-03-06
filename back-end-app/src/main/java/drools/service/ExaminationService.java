package drools.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import drools.model.Disease;
import drools.model.Doctor;
import drools.model.Examination;
import drools.model.Prescription;
import drools.model.Symptom;
import drools.repository.ExaminationRepository;
import drools.resource.AuthenticationResource;

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
		Optional<Examination> e = examinationRepository.findById(id);
		if(e.isPresent()) {
			return e.get();
		}
		
		return null;
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
		
		if(examination.getDisease() != null) {
			System.out.println("Ima bolest");
			return null;
		}
		
		/*Disease DDD = diseaseService.findById(examination.getDisease().getId());
		
		if(DDD == null) {
			System.out.println("Nepostojeca bolest");
			return null;
		}
		
		examination.setDisease(DDD);*/
		
		if(examination.getPrescription() != null) {
			System.out.println("Ima recept");
			return null;
		}
		
		/*Prescription PP = prescriptionService.findById(examination.getPrescription().getId());
		
		if(PP == null) {
			System.out.println("Nepostojeci recept");
			return null;
		}
		
		examination.setPrescription(PP);*/
		
		if(examination.getSymptoms() == null/* && examination.getSymptoms().size() != 0*/) {
			System.out.println("Los update");
			return null;
		}

		if(examination.getSymptoms().size() != 0){
			System.out.println("Simptom koji se kreira ne sme da ima simptome");
		}
		
		/*List<Symptom> newSymps = new ArrayList<Symptom>();
		for(Symptom ss: examination.getSymptoms()) {
			Symptom SS = symptomService.findById(ss.getId());
			
			if(SS == null) {
				System.out.println("Nepostojeci simptom");
				return null;
			}
			
			newSymps.add(SS);
		}
		
		examination.setSymptoms(newSymps);*/
		
		examination.setDate(new Date());
		
		return examinationRepository.save(examination);
	}
	
	@Transactional
	public Examination updateExamination(Examination examination) {

		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null){
			f = ks.getFactHandle(findById(examination.getId()));
		}

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
		
		if(f != null){
			ks.update(f, examination);
		}

		return examinationRepository.save(examination);
	}
	
	@Transactional
	public void deleteExamination(int id) throws SQLException {
		examinationRepository.deleteById(id);
	}
}