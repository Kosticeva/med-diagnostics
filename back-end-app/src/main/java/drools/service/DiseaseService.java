package drools.service;

import java.sql.SQLException;
import java.util.List;

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
		return diseaseRepository.findOne(id);
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
		
		if(diseaseRepository.findByName(disease.getName()).size() != 0) {
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
			if(diseaseRepository.findOne(disease.getId()).getName().equals(disease.getName())) {
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
		diseaseRepository.delete(id);
	}
}
