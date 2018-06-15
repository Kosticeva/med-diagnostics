package drools.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Drug;
import drools.model.Ingredient;
import drools.model.enums.DrugType;
import drools.repository.DrugRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class DrugServiceTest {

	@Autowired
	DrugService drugService;
	
	@Autowired
	DrugRepository drugRepository;
	
	@Autowired
	IngredientService ingredientService;
	
	@Autowired
    private EntityManager em;
	
	public final String DRUG_NAME = "Default_lek";
	public final String DRUG_UPDATE_NAME = "Default_update_lek";
	public final Integer DRUG_ID = 100;
	public final Integer NON_EX_DRUG_ID = 200;
	public final DrugType DRUG_TYPE = DrugType.OTHER;
	public final DrugType UPDATE_DRUG_TYPE = DrugType.ANALGESIC;
	
	
	@Test
	@Transactional
	public void testCreateNewDrug() {
		
		Drug d = new Drug();
		d.setId(null);
		d.setName(DRUG_NAME);
		d.setIngredients(new ArrayList<>());
		d.setDrugType(DRUG_TYPE);
		
		Drug dd = drugService.saveDrug(d);
		assertThat(dd.getId()).isNotNull();
		
		List<Drug> allDrugsMiddle = drugRepository.findAll();
		
		assertEquals(drugService.saveDrug(d).getId(), dd.getId());
		
		List<Drug> allDrugsAfter = drugRepository.findAll();
		assertEquals(allDrugsAfter.size(),allDrugsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewDrug() {
		
		Drug d = new Drug(DRUG_ID, DRUG_NAME, new ArrayList<>(), DRUG_TYPE);
		d = drugRepository.saveAndFlush(d);
		em.detach(d);
		
		List<Drug> allDrugsBefore = drugRepository.findAll();
		assertThat(drugService.saveDrug(d)).isNotNull();
		
		List<Drug> allDrugsMiddle = drugRepository.findAll();
		assertEquals(allDrugsBefore.size(),allDrugsMiddle.size());
		
		d.setName(DRUG_UPDATE_NAME);
		assertThat(drugService.saveDrug(d)).isNotNull();
		
		List<Drug> allDrugsAfter = drugRepository.findAll();
		assertEquals(allDrugsAfter.size(),allDrugsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectDrugPersistence() {
		Drug d = new Drug();
		
		List<Drug> allDrugsBefore = drugRepository.findAll();
		assertThat(drugService.saveDrug(d)).isNull();
		
		List<Drug> allDrugsMiddle = drugRepository.findAll();
		assertEquals(allDrugsBefore.size(),allDrugsMiddle.size());
		
		em.detach(d);
		d.setName("");
	
		assertThat(drugService.saveDrug(d)).isNull();
		
		d.setName(DRUG_NAME);
		assertThat(drugService.saveDrug(d)).isNull();
		
		d.setDrugType(DRUG_TYPE);
		d.setIngredients(new ArrayList<>());
		
		Ingredient i = new Ingredient(13000, "BLah");
		d.getIngredients().add(i);
		
		assertThat(drugService.saveDrug(d)).isNull();
		d.getIngredients().remove(i);
		i = ingredientService.createNewIngredient(i);
		d.getIngredients().add(i);
		
		assertThat(drugService.saveDrug(d)).isNotNull();
		List<Drug> allDrugsAfter = drugRepository.findAll();
		assertEquals(allDrugsAfter.size(),allDrugsMiddle.size()+1);
	}
	
	@Test
	@Transactional
	public void testDeleteDrug() throws SQLException{
		Drug d = new Drug(DRUG_ID, DRUG_NAME, new ArrayList<>(), DRUG_TYPE);
		d = drugRepository.saveAndFlush(d);
		em.detach(d);
		
		List<Drug> allDrugsBefore = drugRepository.findAll();
		drugService.deleteDrug(d.getId());
		
		List<Drug> allDrugsAfter = drugRepository.findAll();
		assertEquals(allDrugsAfter.size(),allDrugsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindDrug() {
		Drug d = new Drug(DRUG_ID, DRUG_NAME, new ArrayList<>(), DRUG_TYPE);
		d = drugRepository.saveAndFlush(d);
		em.detach(d);
		
		assertEquals(drugService.findById(d.getId()), d);
		assertThat(drugService.findById(NON_EX_DRUG_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllDrug() {
		
		List<Drug> allDrugsBefore = drugRepository.findAll();
		Drug d1 = new Drug(DRUG_ID, DRUG_NAME, new ArrayList<>(), DRUG_TYPE);
		Drug d2 = new Drug(NON_EX_DRUG_ID, DRUG_UPDATE_NAME, new ArrayList<>(), UPDATE_DRUG_TYPE);
		
		d1 = drugRepository.saveAndFlush(d1);
		d2 = drugRepository.saveAndFlush(d2);
		
		List<Drug> allDrugsAfter = drugRepository.findAll();
		assertEquals(allDrugsAfter.size(),allDrugsBefore.size()+2);
		
	}
}
