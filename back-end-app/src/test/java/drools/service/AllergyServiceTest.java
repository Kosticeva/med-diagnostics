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
import drools.model.Allergy;
import drools.repository.AllergyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class AllergyServiceTest {

	@Autowired
	AllergyService allergyService;
	
	@Autowired
	AllergyRepository allergyRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String ALLERGY_NAME = "Default_alergija";
	public final String ALLERGY_UPDATE_NAME = "Default_update_alergija";
	public final Integer ALLERGY_ID = 100;
	public final Integer NON_EX_ALLERGY_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewAllergy() {
		
		Allergy a = new Allergy();
		a.setId(null);
		a.setName(ALLERGY_NAME);
		
		Allergy aa = allergyService.createNewAllergy(a);
		assertThat(aa.getId()).isNotNull();
		
		List<Allergy> allAllergiesMiddle = allergyRepository.findAll();
		
		assertEquals(allergyService.createNewAllergy(a).getId(), aa.getId());
		
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewAllergy() {
		
		Allergy a = new Allergy(ALLERGY_ID, ALLERGY_NAME);
		a = allergyRepository.saveAndFlush(a);
		em.detach(a);
		
		List<Allergy> allAllergiesBefore = allergyRepository.findAll();
		assertThat(allergyService.updateAllergy(a)).isNotNull();
		
		List<Allergy> allAllergiesMiddle = allergyRepository.findAll();
		assertEquals(allAllergiesBefore.size(),allAllergiesMiddle.size());
		
		for(Allergy aa: allAllergiesMiddle) {
			if(aa.getName().equals(ALLERGY_UPDATE_NAME)) {
				allergyRepository.delete(aa);
			}
		}
		allAllergiesMiddle = allergyRepository.findAll();
		
		a.setName(ALLERGY_UPDATE_NAME);
		assertThat(allergyService.updateAllergy(a)).isNotNull();
		
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectAllergyPersistence() {
		Allergy a = new Allergy();
		
		List<Allergy> allAllergiesBefore = allergyRepository.findAll();
		assertThat(allergyService.updateAllergy(a)).isNull();
		
		List<Allergy> allAllergiesMiddle = allergyRepository.findAll();
		assertEquals(allAllergiesBefore.size(),allAllergiesMiddle.size());
		
		em.detach(a);
		a.setName("");
	
		assertThat(allergyService.updateAllergy(a)).isNull();
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateToExistingName() {
		Allergy a = new Allergy(ALLERGY_ID, ALLERGY_NAME);
		a = allergyRepository.saveAndFlush(a);
		em.detach(a);
		
		Allergy A = new Allergy();
		A.setName(ALLERGY_UPDATE_NAME);
		A = allergyService.createNewAllergy(A);
		
		em.detach(A);
		A.setName(ALLERGY_NAME);
		
		List<Allergy> allAllergiesBefore = allergyRepository.findAll();
		assertThat(allergyService.updateAllergy(A)).isNull();
		
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesBefore.size());		
	}
	
	@Test
	@Transactional
	public void testDeleteAllergy() throws SQLException{
		Allergy a = new Allergy(ALLERGY_ID, ALLERGY_NAME);
		a = allergyRepository.saveAndFlush(a);
		em.detach(a);
		
		List<Allergy> allAllergiesBefore = allergyRepository.findAll();
		allergyService.deleteAllergy(a.getId());
		
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindAllergy() {
		Allergy a = new Allergy(ALLERGY_ID, ALLERGY_NAME);
		a = allergyRepository.saveAndFlush(a);
		em.detach(a);
		
		assertEquals(allergyService.findById(a.getId()), a);
		assertThat(allergyService.findById(NON_EX_ALLERGY_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllAllergy() {
		
		List<Allergy> allAllergiesBefore = allergyRepository.findAll();
		Allergy a1 = new Allergy(ALLERGY_ID, ALLERGY_NAME);
		Allergy a2 = new Allergy(NON_EX_ALLERGY_ID, ALLERGY_UPDATE_NAME);
		
		a1 = allergyRepository.saveAndFlush(a1);
		a2 = allergyRepository.saveAndFlush(a2);
		
		List<Allergy> allAllergiesAfter = allergyRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesBefore.size()+2);
		
	}
}
