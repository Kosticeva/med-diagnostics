package drools.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import drools.model.Doctor;
import drools.model.Drug;
import drools.model.Examination;
import drools.model.Prescription;
import drools.model.Symptom;
import drools.model.enums.DoctorType;
import drools.model.enums.DrugType;
import drools.repository.ExaminationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class ExaminationServiceTest {

	@Autowired
	ExaminationService examService;
	
	@Autowired
	ExaminationRepository examRepository;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	DrugService drugService;
	
	@Autowired
    private EntityManager em;
	
	public final Integer ALLERGY_ID = 100;
	public final Integer NON_EX_ALLERGY_ID = 200;
	
	public final Date DEF_DATE = new Date();
	
	@Test
	@Transactional
	public void testCreateNewExamination() {
		
		Examination e = new Examination();
		e.setId(null);
		e.setDoctor(doctorService.findAll().get(0));
		e.setSymptoms(new ArrayList<>());
		
		List<Examination> allExamsMiddle = examRepository.findAll();
		
		Examination ee = examService.createNewExamination(e);
		assertThat(ee).isNotNull();
		
		List<Examination> allExamsAfter = examRepository.findAll();
		assertEquals(allExamsAfter.size(),allExamsMiddle.size()+1);
	}
	
	@Test
	@Transactional
	public void testUpdateNewExamination() {
		
		Examination e = new Examination();
		e.setId(null);
		e.setDate(new Date());
		e.setDoctor(doctorService.findAll().get(0));
		
		e = examRepository.saveAndFlush(e);
		em.detach(e);
		
		e.setDisease(diseaseService.findAll().get(0));
		
		Drug d = new Drug();
		d.setName("MM");
		d.setDrugType(DrugType.ANALGESIC);
		d.setIngredients(new ArrayList<>());
		d = drugService.saveDrug(d);
		
		Prescription p = new Prescription();
		p.setPlan("dasfd");
		p.setDrug(d);
		p = prescriptionService.savePrescription(p);
		
		e.setPrescription(p);
		e.setSymptoms(new ArrayList<>());
		e.getSymptoms().add(symptomService.createNewSymptom(new Symptom("dasadsasd", 0)));
		
		List<Examination> allAllergiesBefore = examRepository.findAll();
		assertThat(examService.updateExamination(e)).isNotNull();
		
		List<Examination> allAllergiesMiddle = examRepository.findAll();
		assertEquals(allAllergiesBefore.size(),allAllergiesMiddle.size());
		
		e.getSymptoms().add(symptomService.createNewSymptom(new Symptom("DADDadsasd", 0)));
		
		assertThat(examService.updateExamination(e)).isNotNull();
		
		List<Examination> allAllergiesAfter = examRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectExaminationPersistence() {
		Examination e = new Examination();
		
		assertThat(examService.createNewExamination(e)).isNull();
		em.detach(e);
		
		Doctor DD = new Doctor("F", "G", 6456, "fasssss", "mas", DoctorType.ADMINISTRATOR);
		
		e.setDoctor(DD);
		assertThat(examService.createNewExamination(e)).isNull();
		
		DD = doctorService.findAll().get(0);
		e.setDoctor(DD);
		
		assertThat(examService.createNewExamination(e)).isNull();

		e.setSymptoms(new ArrayList<>());
		e = examService.createNewExamination(e);
		assertThat(e).isNotNull();
		
		Disease Dg = new Disease(67, "qtgeywuetio");
		e.setDisease(Dg);
		assertThat(examService.updateExamination(e)).isNull();
		
		Dg = diseaseService.findAll().get(0);
		e.setDisease(Dg);
		assertThat(examService.updateExamination(e)).isNull();
		
		Drug d = new Drug();
		d.setName("MM");
		d.setDrugType(DrugType.ANALGESIC);
		d.setIngredients(new ArrayList<>());
		d = drugService.saveDrug(d);
		
		Prescription p = new Prescription();
		p.setPlan("dasfd");
		p.setDrug(d);
		p.setId(133333);
		
		e.setPrescription(p);
		assertThat(examService.updateExamination(e)).isNull();
		
		p = prescriptionService.savePrescription(p);
		e.setPrescription(p);
		assertThat(examService.updateExamination(e)).isNull();
		
		Symptom sss = new Symptom("fgewrjt", 98);
		e.getSymptoms().add(sss);
		assertThat(examService.updateExamination(e)).isNull();
		
		e.getSymptoms().remove(sss);
		sss = symptomService.createNewSymptom(sss);
		e.getSymptoms().add(sss);
		assertThat(examService.updateExamination(e)).isNotNull();
	}
	
	
	@Test
	@Transactional
	public void testDeleteExamination() throws SQLException{
		Examination e = new Examination();
		e.setId(null);
		e.setDoctor(doctorService.findAll().get(0));
		e.setDate(new Date());
		
		e = examRepository.saveAndFlush(e);
		em.detach(e);
		
		List<Examination> allAllergiesBefore = examRepository.findAll();
		examService.deleteExamination(e.getId());
		
		List<Examination> allAllergiesAfter = examRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindExamination() {
		Examination e = new Examination();
		e.setId(null);
		e.setDoctor(doctorService.findAll().get(0));
		e.setDate(new Date());
		
		e = examRepository.saveAndFlush(e);
		
		assertEquals(examService.findById(e.getId()), e);
		assertThat(examService.findById(NON_EX_ALLERGY_ID)).isNull();
		examRepository.delete(e);
	}
	
	@Test
	@Transactional
	public void testFindAllExamination() {
		
		List<Examination> allAllergiesBefore = examRepository.findAll();
		Examination e = new Examination();
		e.setId(null);
		e.setDoctor(doctorService.findAll().get(0));
		e.setDate(new Date());
		e.setSymptoms(new ArrayList<>());
		
		Examination e1 = new Examination();
		e1.setId(null);
		e1.setDoctor(doctorService.findAll().get(0));
		e1.setDate(new Date());
		e1.setSymptoms(new ArrayList<>());
		
		e = examRepository.saveAndFlush(e);
		e1 = examRepository.saveAndFlush(e1);
		
		List<Examination> allAllergiesAfter = examRepository.findAll();
		assertEquals(allAllergiesAfter.size(),allAllergiesBefore.size()+2);
		examRepository.delete(e);
		examRepository.delete(e1);
		
	}
}
