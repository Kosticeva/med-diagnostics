package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Drug;
import drools.repository.DrugRepository;

@Service
public class DrugService {
	
	@Autowired
	DrugRepository drugRepository;
	
	public Drug findById(int id) {
		return drugRepository.getOne(id);
	}
	
	public List<Drug> findAll(){
		return drugRepository.findAll();
	}
	
	public Drug createNewDrug(Drug drug) {
		return drugRepository.save(drug);
	}
	
	public Drug updateDrug(Drug drug) {
		return drugRepository.save(drug);
	}
	
	public void deleteDrug(int id) {
		drugRepository.delete(id);
	}
}