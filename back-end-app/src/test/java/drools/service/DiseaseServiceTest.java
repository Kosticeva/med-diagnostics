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
import drools.model.Disease;
import drools.repository.DiseaseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class DiseaseServiceTest {

	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String DISEASE_NAME = "Default_bolest";
	public final String DISEASE_UPDATE_NAME = "Default_update_bolest";
	public final Integer DISEASE_ID = 100;
	public final Integer NON_EX_DISEASE_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewDisease() {
		
		Disease d = new Disease();
		d.setId(null);
		List<Disease> allDiseases = diseaseRepository.findAll();
		for(Disease ddd: allDiseases) {
			if(ddd.getName().equals(DISEASE_NAME)) {
				diseaseRepository.delete(ddd);
			}
		}
		
		d.setName(DISEASE_NAME);

		List<Disease> allDiseasesMiddle = diseaseRepository.findAll();
		Disease dd = diseaseService.createNewDisease(d);
		assertThat(dd.getId()).isNotNull();
		
		
		List<Disease> allDiseasesAfter = diseaseRepository.findAll();
		assertEquals(allDiseasesAfter.size(),allDiseasesMiddle.size()+1);
	}
	
	//TODO
	//@Test
	//@Transactional
	/*public void testUpdateNewDisease() {
		
		List<Disease> allDiseasesMiddle = diseaseRepository.findAll();
		
		for(Disease dd: allDiseasesMiddle) {
			if(dd.getName().equals(DISEASE_NAME)) {
				diseaseRepository.delete(dd);
			}
		}
		
		Disease d = new Disease(DISEASE_ID, DISEASE_NAME);
		d = diseaseRepository.saveAndFlush(d);
		em.detach(d);

		allDiseasesMiddle = diseaseRepository.findAll();
		
		for(Disease dd: allDiseasesMiddle) {
			if(dd.getName().equals(DISEASE_UPDATE_NAME)) {
				diseaseRepository.delete(dd);
			}
		}
		allDiseasesMiddle = diseaseRepository.findAll();
		
		d.setName(DISEASE_UPDATE_NAME);
		assertThat(diseaseService.updateDisease(d)).isNotNull();
		
		List<Disease> allDiseasesAfter = diseaseRepository.findAll();
		assertEquals(allDiseasesAfter.size(),allDiseasesMiddle.size());
	}*/
	
	@Test
	@Transactional
	public void testCorrectDiseasePersistence() {
		Disease d = new Disease();
		
		List<Disease> allDiseasesBefore = diseaseRepository.findAll();
		assertThat(diseaseService.updateDisease(d)).isNull();
		
		List<Disease> allDiseasesMiddle = diseaseRepository.findAll();
		assertEquals(allDiseasesBefore.size(),allDiseasesMiddle.size());
		
		em.detach(d);
		d.setName("");
	
		assertThat(diseaseService.updateDisease(d)).isNull();
		List<Disease> allDiseasesAfter = diseaseRepository.findAll();
		assertEquals(allDiseasesAfter.size(),allDiseasesMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateToExistingName() {
		Disease d = new Disease(DISEASE_ID, DISEASE_NAME);
		d = diseaseRepository.saveAndFlush(d);
		em.detach(d);
		
		Disease D = new Disease();
		D.setName(DISEASE_UPDATE_NAME);
		
		List<Disease> allDiseases = diseaseRepository.findAll();
		for(Disease ddd: allDiseases) {
			if(ddd.getName().equals(DISEASE_UPDATE_NAME)) {
				diseaseRepository.delete(ddd);
			}
		}
		
		D = diseaseService.createNewDisease(D);
		
		em.detach(D);
		D.setName(DISEASE_NAME);
	
		assertThat(diseaseService.updateDisease(D)).isNull();
	}
	
	@Test
	@Transactional
	public void testDeleteDisease() throws SQLException{
		Disease d = new Disease(DISEASE_ID, DISEASE_NAME);
		d = diseaseRepository.saveAndFlush(d);
		em.detach(d);
		
		List<Disease> allDiseasesBefore = diseaseRepository.findAll();
		diseaseService.deleteDisease(d.getId());
		
		List<Disease> allDiseasesAfter = diseaseRepository.findAll();
		assertEquals(allDiseasesAfter.size(),allDiseasesBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindDisease() {
		Disease d = new Disease(DISEASE_ID, DISEASE_NAME);
		d = diseaseRepository.saveAndFlush(d);
		em.detach(d);
		
		assertEquals(diseaseService.findById(d.getId()), d);
		assertThat(diseaseService.findById(NON_EX_DISEASE_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllDisease() {
		
		List<Disease> allDiseasesBefore = diseaseRepository.findAll();
		Disease d1 = new Disease(DISEASE_ID, DISEASE_NAME);
		Disease d2 = new Disease(NON_EX_DISEASE_ID, DISEASE_UPDATE_NAME);
		
		d1 = diseaseRepository.saveAndFlush(d1);
		d2 = diseaseRepository.saveAndFlush(d2);
		
		List<Disease> allDiseasesAfter = diseaseRepository.findAll();
		assertEquals(allDiseasesAfter.size(),allDiseasesBefore.size()+2);
		
	}
}
