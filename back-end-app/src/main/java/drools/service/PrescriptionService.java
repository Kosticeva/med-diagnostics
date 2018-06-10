package drools.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Drug;
import drools.model.Prescription;
import drools.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	DrugService drugService;
	
	@Transactional
	public Prescription findById(int id) {
		return prescriptionRepository.findOne(id);
	}
	
	@Transactional
	public List<Prescription> findAll(){
		return prescriptionRepository.findAll();
	}
	
	@Transactional
	public Prescription savePrescription(Prescription prescription) {
		if(prescription.getPlan() == null || prescription.getPlan().equals("")) {
			System.out.println("Nema plana");
			return null;
		}
		
		if(prescription.getDrug() == null) {
			System.out.println("Nema leka");
			return null;
		}
		
		Drug DD = drugService.findById(prescription.getDrug().getId());
		if(DD == null) {
			System.out.println("Nepostojeci lek");
			return null;
		}
		
		prescription.setDrug(DD);
		
		return prescriptionRepository.save(prescription);
	}
	
	@Transactional
	public void deletePrescription(int id) throws SQLException {
		prescriptionRepository.delete(id);
	}
}