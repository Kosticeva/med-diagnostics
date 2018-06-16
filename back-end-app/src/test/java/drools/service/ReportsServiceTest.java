package drools.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Allergy;
import drools.model.Chart;
import drools.model.Disease;
import drools.model.Doctor;
import drools.model.Drug;
import drools.model.Examination;
import drools.model.Ingredient;
import drools.model.Patient;
import drools.model.Prescription;
import drools.model.enums.DoctorType;
import drools.model.enums.DrugType;
import drools.repository.AllergyRepository;
import drools.repository.ChartRepository;
import drools.repository.DiseaseRepository;
import drools.repository.DoctorRepository;
import drools.repository.DrugRepository;
import drools.repository.ExaminationRepository;
import drools.repository.IngredientRepository;
import drools.repository.PatientRepository;
import drools.repository.PrescriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class ReportsServiceTest {
	
	@Autowired
	ReportsService reportsService;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	ExaminationRepository examRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	AllergyRepository allergyRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	DrugRepository drugRepository;	
	
	@Test
	@Transactional
	public void findPossibleAddicts() {
		//run addict-patients.sql
		List<Patient> addictPatients = reportsService.findAddicts();
		assertEquals(addictPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void findChronicPatients() {
		//run chronic-patients.sql
		List<Patient> chronicPatients = reportsService.findChronics();
		assertEquals(chronicPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void findWeakPatients() {
		//run weak-patients.sql
		List<Patient> weakPatients = reportsService.findWeaks();
		assertEquals(weakPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void checkAllergies() {
		
		Patient p = new Patient("Zoran", "Zoric", null, new ArrayList<>());
		Allergy a = new Allergy(null, "Penicilin");
		a = allergyRepository.save(a);
		p.getAllergens().add(a);
		p = patientRepository.save(p);
		
		Ingredient i = new Ingredient(null, "Penicilin");
		i = ingredientRepository.save(i);
		
		Drug dg = new Drug(null, "AntiBio", new ArrayList<>(), DrugType.ANTIBIOTIC);
		dg.getIngredients().add(i);
		dg = drugRepository.save(dg);
		
		Prescription ps = new Prescription(null, "Dva puta dnevno", dg);
		ps = prescriptionRepository.save(ps);
		
		Doctor d = new Doctor("Uros", "Urosevic", null, "drUrosUros", "uros654321", DoctorType.REGULAR);
		d = doctorRepository.save(d);
		
		Disease ds = new Disease(null, "Infekcija uha");
		ds = diseaseRepository.save(ds);
		
		Examination e = new Examination(null, new Date(), d, new ArrayList<>(), ds, ps);
		e = examRepository.save(e);
		
		Chart c = new Chart(new ArrayList<>(), p, null);
		c.getExaminations().add(e);
		c = chartRepository.save(c);
		
		assertEquals(reportsService.checkAllergyWarnings(p.getId(), dg).size(), 1);
		
	}
	
}
