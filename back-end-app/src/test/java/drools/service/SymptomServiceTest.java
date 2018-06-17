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
import drools.model.Symptom;
import drools.repository.SymptomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class SymptomServiceTest {

	@Autowired
	SymptomService symptomService;
	
	@Autowired
	SymptomRepository symptomRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String SYMPTOM_NAME = "Default_simptom";
	public final String SYMPTOM_UPDATE_NAME = "Default_update_simptom";
	public final Integer SYMPTOM_ID = 100;
	public final Integer NON_EX_SYMPTOM_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewSymptom() {
		
		Symptom s = new Symptom();
		s.setId(null);
		s.setName(SYMPTOM_NAME);
		
		Symptom ss = symptomService.createNewSymptom(s);
		assertThat(ss.getId()).isNotNull();
		
		List<Symptom> allSymptomsMiddle = symptomRepository.findAll();
		
		assertEquals(symptomService.createNewSymptom(s).getId(), ss.getId());
		
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewSymptom() {
		
		Symptom s = new Symptom(SYMPTOM_NAME, SYMPTOM_ID);
		s = symptomRepository.saveAndFlush(s);
		em.detach(s);
		
		List<Symptom> allSymptomsBefore = symptomRepository.findAll();
		assertThat(symptomService.updateSymptom(s)).isNotNull();
		
		List<Symptom> allSymptomsMiddle = symptomRepository.findAll();
		assertEquals(allSymptomsBefore.size(),allSymptomsMiddle.size());
		
		for(Symptom ss: allSymptomsMiddle) {
			if(ss.getName().equals(SYMPTOM_UPDATE_NAME)) {
				symptomRepository.delete(ss);
			}
		}
		allSymptomsMiddle = symptomRepository.findAll();
		
		s.setName(SYMPTOM_UPDATE_NAME);
		assertThat(symptomService.updateSymptom(s)).isNotNull();
		
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectSymptomPersistence() {
		Symptom s = new Symptom("", -1);
		
		List<Symptom> allSymptomsBefore = symptomRepository.findAll();
		assertThat(symptomService.updateSymptom(s)).isNull();
		
		List<Symptom> allSymptomsMiddle = symptomRepository.findAll();
		assertEquals(allSymptomsBefore.size(),allSymptomsMiddle.size());
		
		em.detach(s);
		s.setName("");
	
		assertThat(symptomService.updateSymptom(s)).isNull();
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateToExistingName() {
		Symptom s = new Symptom(SYMPTOM_NAME, SYMPTOM_ID);
		s = symptomRepository.saveAndFlush(s);
		em.detach(s);
		
		Symptom S = new Symptom();
		S.setName(SYMPTOM_UPDATE_NAME);
		S = symptomService.createNewSymptom(S);
		
		em.detach(S);
		S.setName(SYMPTOM_NAME);
		
		List<Symptom> allSymptomsBefore = symptomRepository.findAll();
		assertThat(symptomService.updateSymptom(S)).isNull();
		
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsBefore.size());		
	}
	
	@Test
	@Transactional
	public void testDeleteSymptom() throws SQLException{
		Symptom s = new Symptom(SYMPTOM_NAME, SYMPTOM_ID);
		s = symptomRepository.saveAndFlush(s);
		em.detach(s);
		
		List<Symptom> allSymptomsBefore = symptomRepository.findAll();
		symptomService.deleteSymptom(s.getId());
		
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindSymptom() {
		Symptom s = new Symptom(SYMPTOM_NAME, SYMPTOM_ID);
		s = symptomRepository.saveAndFlush(s);
		em.detach(s);
		
		assertEquals(symptomService.findById(s.getId()), s);
		assertThat(symptomService.findById(NON_EX_SYMPTOM_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllSymptom() {
		
		List<Symptom> allSymptomsBefore = symptomRepository.findAll();
		Symptom s1 = new Symptom(SYMPTOM_NAME, SYMPTOM_ID);
		Symptom s2 = new Symptom(SYMPTOM_UPDATE_NAME, NON_EX_SYMPTOM_ID);
		
		s1 = symptomRepository.saveAndFlush(s1);
		s2 = symptomRepository.saveAndFlush(s2);
		
		List<Symptom> allSymptomsAfter = symptomRepository.findAll();
		assertEquals(allSymptomsAfter.size(),allSymptomsBefore.size()+2);
		
	}
}
