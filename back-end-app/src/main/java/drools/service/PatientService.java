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

import drools.model.Allergy;
import drools.model.Patient;
import drools.repository.PatientRepository;
import drools.resource.AuthenticationResource;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AllergyService allergyService;
	
	@Transactional
	public Patient findById(int id) {
		Optional<Patient> p = patientRepository.findById(id);
		if(p.isPresent()) {
			return p.get();
		}
		
		return null;
	}

	@Transactional
	public List<Patient> findAll(){
		return patientRepository.findAll();
	}
	
	@Transactional
	public List<Patient> findByName(String name){
		return patientRepository.findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(name, name);
	}
	
	@Transactional
	public Patient savePatient(Patient patient) {

		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null && patient.getId()!= null){
			f = ks.getFactHandle(findById(patient.getId()));
		}

		if(patient.getFirstName() == null || patient.getFirstName().equals("")) {
			System.out.println("Nema prvog imena za pacijenta");
			return null;
		}
		
		if(patient.getLastName() == null || patient.getLastName().equals("")) {
			System.out.println("Nema prezimena za pacijenta");
			return null;
		}
		
		List<Allergy> newAlgs = new ArrayList<Allergy>();
		for(Allergy aa: patient.getAllergens()) {
			Allergy AA = allergyService.findById(aa.getId());
			
			if(AA == null) {
				System.out.println("Nepostojeca alergija");
				return null;
			}
			
			newAlgs.add(AA);
		}
		
		patient.setAllergens(newAlgs);

		if(f != null){
			ks.update(f, patient);
		}

		return patientRepository.save(patient);
	}

	@Transactional
	public void deletePatient(int id) throws SQLException {
		patientRepository.deleteById(id);
	}
}