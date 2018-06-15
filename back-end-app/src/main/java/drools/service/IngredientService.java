package drools.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Ingredient;
import drools.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Transactional
	public Ingredient findById(int id) {
		Optional<Ingredient> i = ingredientRepository.findById(id);
		if(i.isPresent()) {
			return i.get();
		}
		
		return null;
	}
	
	@Transactional
	public List<Ingredient> findByName(String name){
		return ingredientRepository.findByNameStartingWith(name);
	}
	
	@Transactional
	public List<Ingredient> findAll(){
		return ingredientRepository.findAll();
	}
	
	@Transactional
	public Ingredient createNewIngredient(Ingredient ingredient) {
		if(ingredient.getName() == null || ingredient.getName().equals("")) {
			System.out.println("Nema imena za sastojak");
			return null;
		}
		
		List<Ingredient> i = ingredientRepository.findByName(ingredient.getName());
		if(i.size() != 0) {
			System.out.println("Vec zauzeto ime");
			return i.get(0);
		}
		
		return ingredientRepository.save(ingredient);
	}
	
	@Transactional
	public Ingredient updateIngredient(Ingredient ingredient) {
		if(ingredient.getName() == null || ingredient.getName().equals("")) {
			System.out.println("Nema imena za sastojak");
			return null;
		}
		
		//vec postoji ime u bazi
		if(ingredientRepository.findByName(ingredient.getName()).size() != 0) {
			
			//to je staro ime
			if(ingredientRepository.getOne(ingredient.getId()).getName().equals(ingredient.getName())) {
				//
			}else {
				//menja se ime al postoji u bazi
				System.out.println("Vec zauzeto ime");
				return null;
			}
		}
		
		return ingredientRepository.save(ingredient);
	}
	
	@Transactional
	public void deleteIngredient(int id) throws SQLException{
		ingredientRepository.deleteById(id);
	}
}