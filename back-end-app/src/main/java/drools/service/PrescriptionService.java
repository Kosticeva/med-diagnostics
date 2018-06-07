package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Prescription;
import drools.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	public Prescription findById(int id) {
		return prescriptionRepository.getOne(id);
	}
	
	public List<Prescription> findAll(){
		return prescriptionRepository.findAll();
	}
	
	public Prescription createNewPrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	public Prescription updatePrescription(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}
	
	public void deletePrescription(int id) {
		prescriptionRepository.delete(id);
	}
}