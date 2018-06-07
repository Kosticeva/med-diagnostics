package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Symptom;
import drools.repository.SymptomRepository;

@Service
public class SymptomService {
	
	@Autowired
	SymptomRepository symptomRepository;
	
	public Symptom findById(int id) {
		return symptomRepository.getOne(id);
	}
	
	public List<Symptom> findAll(){
		return symptomRepository.findAll();
	}
	
	public Symptom createNewSymptom(Symptom symptom) {
		return symptomRepository.save(symptom);
	}
	
	public Symptom updateSymptom(Symptom symptom) {
		return symptomRepository.save(symptom);
	}
	
	public void deleteSymptom(int id) {
		symptomRepository.delete(id);
	}
}