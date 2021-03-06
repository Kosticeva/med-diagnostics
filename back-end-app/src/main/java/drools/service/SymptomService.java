package drools.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import drools.model.Symptom;
import drools.repository.SymptomRepository;
import drools.resource.AuthenticationResource;

@Service
public class SymptomService {
	
	@Autowired
	SymptomRepository symptomRepository;
	
	@Transactional
	public Symptom findById(int id) {
		Optional<Symptom> s = symptomRepository.findById(id);
		if(s.isPresent()) {
			return s.get();
		}
		
		return null;
	} 
	
	@Transactional
	public List<Symptom> findByName(String name){
		return symptomRepository.findByNameStartingWithIgnoreCase(name);
	}
	
	@Transactional
	public List<Symptom> findAll(){
		return symptomRepository.findAll();
	}
	
	@Transactional
	public Symptom createNewSymptom(Symptom symptom) {
		if(symptom.getName() == null || symptom.getName().equals("")) {
			System.out.println("Nema imena");
			return null;
		}
		
		List<Symptom> s = symptomRepository.findByName(symptom.getName());
		if(s.size() != 0) {
			System.out.println("Zauzeto ime");
			return s.get(0);
		}
		
		return symptomRepository.save(symptom);
	}
	
	@Transactional
	public Symptom updateSymptom(Symptom symptom) {

		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null){
			f = ks.getFactHandle(findById(symptom.getId()));
		}
		
		if(symptom.getName() == null || symptom.getName().equals("")) {
			System.out.println("Nema imena");
			return null;
		}
		
		//vec postoji ime u bazi
		if(symptomRepository.findByName(symptom.getName()).size() != 0) {
			
			//to je staro ime
			if(symptomRepository.getOne(symptom.getId()).getName().equals(symptom.getName())) {
				//
			}else {
				//menja se ime al postoji u bazi
				System.out.println("Vec zauzeto ime");
				return null;
			}
		}
		
		if(f != null){
			ks.update(f, symptom);
		}

		return symptomRepository.save(symptom);
	}
	
	@Transactional
	public void deleteSymptom(int id) throws SQLException {
		symptomRepository.deleteById(id);
	}
}