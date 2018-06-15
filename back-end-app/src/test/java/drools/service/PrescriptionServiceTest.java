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
import drools.model.Prescription;
import drools.model.enums.DrugType;
import drools.repository.DrugRepository;
import drools.repository.PrescriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class PrescriptionServiceTest {

	@Autowired
	PrescriptionService prescriptionService;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	DrugRepository drugRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String PRESCR_PLAN = "Default_plan";
	public final String PRESCR_UPDATE_PLAN = "Default_update_plan";
	public final Integer PRESCR_ID = 100;
	public final Integer NON_EX_PRESCR_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewPrescription() {
		
		Drug d = drugRepository.findAll().get(0);
		Prescription p = new Prescription();
		p.setId(null);
		p.setPlan(PRESCR_PLAN);
		p.setDrug(d);
		
		Prescription pp = prescriptionService.savePrescription(p);
		assertThat(pp.getId()).isNotNull();
		
		List<Prescription> allPrescriptionsMiddle = prescriptionRepository.findAll();
		
		assertEquals(prescriptionService.savePrescription(p).getId(), pp.getId());
		
		List<Prescription> allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewPrescription() {
		
		Drug d = drugRepository.findAll().get(0);
		
		Prescription p = new Prescription(PRESCR_ID, PRESCR_PLAN, d);
		p = prescriptionRepository.saveAndFlush(p);
		em.detach(p);
		
		List<Prescription> allPrescriptionsBefore = prescriptionRepository.findAll();
		assertThat(prescriptionService.savePrescription(p)).isNotNull();
		
		List<Prescription> allPrescriptionsMiddle = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsBefore.size(),allPrescriptionsMiddle.size());
		
		p.setPlan(PRESCR_UPDATE_PLAN);
		assertThat(prescriptionService.savePrescription(p)).isNotNull();
		
		List<Prescription> allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectPrescriptionPersistence() {
		Prescription p = new Prescription();
		
		List<Prescription> allPrescriptionsBefore = prescriptionRepository.findAll();
		assertThat(prescriptionService.savePrescription(p)).isNull();
		
		List<Prescription> allPrescriptionsMiddle = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsBefore.size(),allPrescriptionsMiddle.size());
		
		em.detach(p);
		p.setPlan("");
	
		assertThat(prescriptionService.savePrescription(p)).isNull();
		List<Prescription> allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsMiddle.size());
		
		p.setPlan(PRESCR_UPDATE_PLAN);
		Drug d = new Drug(900, "Lala", new ArrayList<>(), DrugType.OTHER);
		p.setDrug(d);
		
		assertThat(prescriptionService.savePrescription(p)).isNull();
		allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsMiddle.size());
		
		d = drugRepository.findAll().get(0);
		p.setDrug(d);
		assertThat(prescriptionService.savePrescription(p)).isNotNull();
		allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsMiddle.size()+1);
	}
	
	
	@Test
	@Transactional
	public void testDeletePrescription() throws SQLException{
		Drug d = new Drug(900, "Lala", new ArrayList<>(), DrugType.OTHER);
		drugRepository.save(d);
		
		Prescription p = new Prescription(PRESCR_ID, PRESCR_PLAN, d);
		p = prescriptionRepository.saveAndFlush(p);
		em.detach(p);
		
		List<Prescription> allPrescriptionsBefore = prescriptionRepository.findAll();
		prescriptionService.deletePrescription(p.getId());
		
		List<Prescription> allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindPrescription() {
		Drug d = new Drug(900, "Lala", new ArrayList<>(), DrugType.OTHER);
		d = drugRepository.save(d);
		
		Prescription p = new Prescription(PRESCR_ID, PRESCR_PLAN, d);
		p = prescriptionRepository.saveAndFlush(p);
		em.detach(p);
		
		assertEquals(prescriptionService.findById(p.getId()), p);
		assertThat(prescriptionService.findById(NON_EX_PRESCR_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllPrescription() {
		Drug d = new Drug(900, "Lala", new ArrayList<>(), DrugType.OTHER);
		d = drugRepository.save(d);
		
		List<Prescription> allPrescriptionsBefore = prescriptionRepository.findAll();
		Prescription p1 = new Prescription(PRESCR_ID, PRESCR_PLAN, d);
		Prescription p2 = new Prescription(NON_EX_PRESCR_ID, PRESCR_UPDATE_PLAN, d);
		
		p1 = prescriptionRepository.saveAndFlush(p1);
		p2 = prescriptionRepository.saveAndFlush(p2);
		
		List<Prescription> allPrescriptionsAfter = prescriptionRepository.findAll();
		assertEquals(allPrescriptionsAfter.size(),allPrescriptionsBefore.size()+2);
		
	}
}
