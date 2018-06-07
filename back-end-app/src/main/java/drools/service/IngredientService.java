package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Ingredient;
import drools.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	public Ingredient findById(int id) {
		return ingredientRepository.getOne(id);
	}
	
	public List<Ingredient> findAll(){
		return ingredientRepository.findAll();
	}
	
	public Ingredient createNewIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	public Ingredient updateIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	public void deleteIngredient(int id) {
		ingredientRepository.delete(id);
	}
}