package drools.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Disease;
import drools.model.Link;
import drools.model.Symptom;
import drools.repository.LinkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class LinkServiceTest {

	@Autowired
	LinkService linkService;
	
	@Autowired
	LinkRepository linkRepository;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	SymptomService symptomService;
	
	public final Integer LINK_ID = 100;
	public final Integer NON_EX_LINK_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewLink() {
		
		Disease d = diseaseService.findAll().get(0);
		Symptom s = symptomService.findAll().get(0);
		
		List<Link> allLinksMiddle = linkRepository.findAll();
		for(Link LLLL: allLinksMiddle) {
			if(LLLL.getDisease().equals(d) && LLLL.getSymptom().equals(s)) {
				linkRepository.delete(LLLL);
			}
		}
		
		allLinksMiddle = linkRepository.findAll();
		Link ll = linkService.connect(d.getId(), s.getId());
		assertThat(ll.getId()).isNotNull();
		
		List<Link> allLinksAfter = linkRepository.findAll();
		assertEquals(allLinksAfter.size(),allLinksMiddle.size()+1);
	}
	
	@Test
	@Transactional
	public void testDeleteLink() throws SQLException{
		List<Disease> diseases = diseaseService.findAll();
		for(Disease dddd: diseases) {
			if(dddd.getName().equals("XXX")) {
				diseaseService.deleteDisease(dddd.getId());
			}
		}
		
		Disease d = new Disease();
		d.setName("XXX");
		d = diseaseService.createNewDisease(d);
		
		Symptom s = new Symptom();
		s.setName("YYY");
		s = symptomService.createNewSymptom(s);
		
		Link ll = linkService.connect(d.getId(), s.getId());
		
		List<Link> allLinksBefore = linkRepository.findAll();
		linkService.disconnect(ll.getDisease().getId(), ll.getSymptom().getId());
		
		List<Link> allLinksAfter = linkRepository.findAll();
		assertEquals(allLinksAfter.size(),allLinksBefore.size()-1);
	}
	
}