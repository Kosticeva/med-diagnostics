package drools.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Drug;
import drools.model.Ingredient;
import drools.repository.DrugRepository;

@Service
public class DrugService {
	
	@Autowired
	DrugRepository drugRepository;
	
	@Autowired
	IngredientService ingredientService;
	
	@Transactional
	public Drug findById(int id) {
		return drugRepository.getOne(id);
	}
	
	@Transactional
	public List<Drug> findByName(String name){
		return drugRepository.findByNameStartingWith(name);
	}
	
	@Transactional
	public List<Drug> findAll(){
		return drugRepository.findAll();
	}
	
	@Transactional
	public Drug saveDrug(Drug drug) {
		if(drug.getName() == null || drug.getName().equals("")) {
			System.out.println("Nema imena za lek");
			return null;
		}
		
		if(drug.getDrugType() == null) {
			System.out.println("Nema tipa za lek");
			return null;
		}
		
		List<Ingredient> newIgns = new ArrayList<Ingredient>();
		
		for(Ingredient ii: drug.getIngredients()) {
			Ingredient II = ingredientService.findById(ii.getId());
			
			if(II == null) {
				System.out.println("Nepostojeci sastojak za lek");
				return null;
			}
			
			newIgns.add(II);
		}
		
		drug.setIngredients(newIgns);
		return drugRepository.save(drug);
	}
	
	@Transactional
	public void deleteDrug(int id) {
		drugRepository.deleteById(id);
	}
}