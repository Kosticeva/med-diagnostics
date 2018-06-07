package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

}
