package drools.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Ingredient;
import drools.repository.IngredientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class IngredientServiceTest {

	@Autowired
	IngredientService ingredientService;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String INGR_NAME = "Default_sastojak";
	public final String INGR_UPDATE_NAME = "Default_update_sastojak";
	public final Integer INGR_ID = 100;
	public final Integer NON_EX_INGR_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewIngredient() {
		
		Ingredient i = new Ingredient();
		i.setId(null);
		i.setName(INGR_NAME);
		
		Ingredient ii = ingredientService.createNewIngredient(i);
		assertThat(ii.getId()).isNotNull();
		
		List<Ingredient> allIngredientsMiddle = ingredientRepository.findAll();
		
		assertEquals(ingredientService.createNewIngredient(i).getId(), ii.getId());
		
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewIngredient() {
		
		Ingredient i = new Ingredient(INGR_ID, INGR_NAME);
		i = ingredientRepository.saveAndFlush(i);
		em.detach(i);
		
		List<Ingredient> allIngredientsBefore = ingredientRepository.findAll();
		assertThat(ingredientService.updateIngredient(i)).isNotNull();
		
		List<Ingredient> allIngredientsMiddle = ingredientRepository.findAll();
		assertEquals(allIngredientsBefore.size(),allIngredientsMiddle.size());
		
		for(Ingredient ii: allIngredientsMiddle) {
			if(ii.getName().equals(INGR_UPDATE_NAME)) {
				ingredientRepository.delete(ii);
			}
		}
		allIngredientsMiddle = ingredientRepository.findAll();
		
		i.setName(INGR_UPDATE_NAME);
		assertThat(ingredientService.updateIngredient(i)).isNotNull();
		
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectIngredientPersistence() {
		Ingredient i = new Ingredient();
		
		List<Ingredient> allIngredientsBefore = ingredientRepository.findAll();
		assertThat(ingredientService.updateIngredient(i)).isNull();
		
		List<Ingredient> allIngredientsMiddle = ingredientRepository.findAll();
		assertEquals(allIngredientsBefore.size(),allIngredientsMiddle.size());
		
		em.detach(i);
		i.setName("");
	
		assertThat(ingredientService.updateIngredient(i)).isNull();
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateToExistingName() {
		Ingredient i = new Ingredient(INGR_ID, INGR_NAME);
		i = ingredientRepository.saveAndFlush(i);
		em.detach(i);
		
		Ingredient I = new Ingredient();
		I.setName(INGR_UPDATE_NAME);
		I = ingredientService.createNewIngredient(I);
		
		em.detach(I);
		I.setName(INGR_NAME);
		
		List<Ingredient> allIngredientsBefore = ingredientRepository.findAll();
		assertThat(ingredientService.updateIngredient(I)).isNull();
		
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsBefore.size());		
	}
	
	@Test
	@Transactional
	public void testDeleteIngredient() throws SQLException{
		Ingredient i = new Ingredient(INGR_ID, INGR_NAME);
		i = ingredientRepository.saveAndFlush(i);
		em.detach(i);
		
		List<Ingredient> allIngredientsBefore = ingredientRepository.findAll();
		ingredientService.deleteIngredient(i.getId());
		
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindIngredient() {
		Ingredient i = new Ingredient(INGR_ID, INGR_NAME);
		i = ingredientRepository.saveAndFlush(i);
		em.detach(i);
		
		assertEquals(ingredientService.findById(i.getId()), i);
		assertThat(ingredientService.findById(NON_EX_INGR_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllIngredient() {
		
		List<Ingredient> allIngredientsBefore = ingredientRepository.findAll();
		Ingredient i1 = new Ingredient(INGR_ID, INGR_NAME);
		Ingredient i2 = new Ingredient(NON_EX_INGR_ID, INGR_UPDATE_NAME);
		
		i1 = ingredientRepository.saveAndFlush(i1);
		i2 = ingredientRepository.saveAndFlush(i2);
		
		List<Ingredient> allIngredientsAfter = ingredientRepository.findAll();
		assertEquals(allIngredientsAfter.size(),allIngredientsBefore.size()+2);
		
	}
}
