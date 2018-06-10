package drools.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Allergy;
import drools.repository.AllergyRepository;

@Service
public class AllergyService {

	@Autowired
	AllergyRepository allergyRepository;
	
	@Transactional
	public Allergy findById(int id) {
		return allergyRepository.findOne(id);
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
		
		if(allergyRepository.findByName(allergy.getName()).size() != 0) {
			System.out.println("Postojeca alergija");
			return null;
		}
		
		return allergyRepository.save(allergy);
	}
	
	@Transactional
	public Allergy updateAllergy(Allergy allergy) {
		if(allergy.getName() == null || allergy.getName().equals("")) {
			System.out.println("Nema naziva alergije");
			return null;
		}
		
		//vec postoji ime u bazi
		if(allergyRepository.findByName(allergy.getName()).size() != 0) {
			
			//to je staro ime
			if(allergyRepository.findOne(allergy.getId()).getName().equals(allergy.getName())) {
				//
			}else {
				//menja se ime al postoji u bazi
				System.out.println("Vec zauzeto ime");
				return null;
			}
		}
		
		
		return allergyRepository.save(allergy);
	}
	
	@Transactional
	public void deleteAllergy(int id) throws SQLException{
		allergyRepository.delete(id);
	}
}
