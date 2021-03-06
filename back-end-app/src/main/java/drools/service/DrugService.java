package drools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import drools.model.Drug;
import drools.model.Ingredient;
import drools.repository.DrugRepository;
import drools.resource.AuthenticationResource;

@Service
public class DrugService {
	
	@Autowired
	DrugRepository drugRepository;
	
	@Autowired
	IngredientService ingredientService;
	
	@Transactional
	public Drug findById(int id) {
		Optional<Drug> d = drugRepository.findById(id);
		if(d.isPresent()) {
			return d.get();
		}
		
		return null;
	}
	
	@Transactional
	public List<Drug> findByName(String name){
		return drugRepository.findByNameStartingWithIgnoreCase(name);
	}
	
	@Transactional
	public List<Drug> findAll(){
		return drugRepository.findAll();
	}
	
	@Transactional
	public Drug saveDrug(Drug drug) {
		KieSession ks = AuthenticationResource.getKieSessionOf();
		FactHandle f = null;
		if(ks != null && drug.getId()!= null){
			f = ks.getFactHandle(findById(drug.getId()));
		}

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
		
		if(f != null){
			ks.update(f, drug);
		}

		return drugRepository.save(drug);
	}
	
	@Transactional
	public void deleteDrug(int id) {
		drugRepository.deleteById(id);
	}
}