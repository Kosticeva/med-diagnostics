package drools.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Disease;
import drools.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	SymptomService symptomService;
	
	@Transactional
	public Disease findById(int id) {
		Optional<Disease> d = diseaseRepository.findById(id);
		if(d.isPresent()) {
			return d.get();
		}
		
		return null;
	}

	@Transactional
	public List<Disease> findByStartName(String name){
		return diseaseRepository.findByNameStartingWithIgnoreCase(name);
	}
	
	@Transactional
	public List<Disease> findAll() {
		return diseaseRepository.findAll();
	}
	
	@Transactional
	public Disease createNewDisease(Disease disease) {
		if(disease.getName() == null || disease.getName().equals("")) {
			System.out.println("Nema naziva bolesti");
			return null;
		}
		
		List<Disease> dds= diseaseRepository.findByName(disease.getName());
		for(Disease ddd: dds){
			System.out.println(ddd);
		}
		
		if(dds.size() != 0) {
			System.out.println("Vec postojeca bolest");
			return null;
		}
		
		return diseaseRepository.save(disease);
	}
	
	@Transactional
	public Disease updateDisease(Disease disease) {
		if(disease.getName() == null || disease.getName().equals("")) {
			System.out.println("Nema naziva bolesti");
			return null;
		}
		
		//vec postoji ime u bazi
		if(diseaseRepository.findByName(disease.getName()).size() != 1) {
			
			//to je staro ime
			if(diseaseRepository.getOne(disease.getId()).getName().equals(disease.getName())) {
				//
			}else {
				//menja se ime al postoji u bazi
				System.out.println("Vec zauzeto ime");
				return null;
			}
		}
		
		return diseaseRepository.save(disease);
	}
	
	@Transactional
	public void deleteDisease(int id) throws SQLException{
		diseaseRepository.deleteById(id);
	}
}
