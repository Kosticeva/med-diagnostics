package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

	List<Ingredient> findByName(String name);
	List<Ingredient> findByNameStartingWith(String name);
}
