package drools.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import drools.model.Allergy;
import drools.repository.AllergyRepository;
import drools.resource.AuthenticationResource;

@Service
public class AllergyService {

	@Autowired
	AllergyRepository allergyRepository;

	@Transactional
	public Allergy findById(int id) {
		Optional<Allergy> a = allergyRepository.findById(id);
		if(a.isPresent())
			return a.get();
		
		return null;
	}
	
	@Transactional
	public List<Allergy> findAll() {
		return allergyRepository.findAll();
	}
	
	@Transactional
	public Allergy createNewAllergy(Allergy allergy) {
		if(allergy.getName() == null || allergy.getName().equals("")) {
			System.out.println("Nema naziva alergije");
			return null;
		}
		
		List<Allergy> a = allergyRepository.findByName(allergy.getName());
		if(a.size() != 0) {
			System.out.println("Postojeca alergija");
			return a.get(0);
		}
		
		return allergyRepository.save(allergy);
	}
	
	@Transactional
	public Allergy updateAllergy(Allergy allergy) {
		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null){
			f = ks.getFactHandle(findById(allergy.getId()));
		}

		if(allergy.getName() == null || allergy.getName().equals("")) {
			System.out.println("Nema naziva alergije");
			return null;
		}
		
		//vec postoji ime u bazi
		if(allergyRepository.findByName(allergy.getName()).size() != 0) {
			
			//to je staro ime
			Optional<Allergy> aa = allergyRepository.findById(allergy.getId());
			if(aa.isPresent() && aa.get().getName().equals(allergy.getName())) {
				//
			}else {
				//menja se ime al postoji u bazi
				System.out.println("Vec zauzeto ime");
				return null;
			}
		}
		
		if(f != null){
			ks.update(f, allergy);
		}

		return allergyRepository.save(allergy);
	}
	
	@Transactional
	public void deleteAllergy(int id) throws SQLException{
		allergyRepository.deleteById(id);
	}
}
