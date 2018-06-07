package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Patient;
import drools.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public Patient findById(int id) {
		return patientRepository.getOne(id);
	}
	
	public List<Patient> findAll(){
		return patientRepository.findAll();
	}
	
	public Patient createNewPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public Patient updatePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public void deletePatient(int id) {
		patientRepository.delete(id);
	}
}