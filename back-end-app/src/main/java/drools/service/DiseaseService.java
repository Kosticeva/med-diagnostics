package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Disease;
import drools.repository.DiseaseRepository;

@Service
public class DiseaseService {

	@Autowired
	DiseaseRepository diseaseRepository;
	
	public Disease findById(int id) {
		return diseaseRepository.getOne(id);
	}
	
	public List<Disease> findAll() {
		return diseaseRepository.findAll();
	}
	
	public Disease createNewDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}
	
	public Disease updateDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}
	
	public void deleteDisease(int id) {
		diseaseRepository.delete(id);
	}
}
