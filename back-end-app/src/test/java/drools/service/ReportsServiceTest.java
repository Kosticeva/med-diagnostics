package drools.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
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
import drools.resource.AuthenticationResource;

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

	@Autowired
	AuthenticationResource authResource;
		

	
	@Test
	@Transactional
	public void findPossibleAddicts() {

		authResource.login(doctorRepository.findAll().get(0));
		//run addict-patients.sql
		List<Patient> addictPatients = reportsService.findAddicts(AuthenticationResource.getKieSessionOf());
		assertEquals(addictPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void findChronicPatients() {

		authResource.login(doctorRepository.findAll().get(0));
		//run chronic-patients.sql
		List<Patient> chronicPatients = reportsService.findChronics(AuthenticationResource.getKieSessionOf());
		assertEquals(chronicPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void findWeakPatients() {
		//run weak-patients.sql

		authResource.login(doctorRepository.findAll().get(0));
		List<Patient> weakPatients = reportsService.findWeaks(AuthenticationResource.getKieSessionOf());
		assertEquals(weakPatients.size(), 1);
	}
	
	@Test
	@Transactional
	public void checkAllergies() {
		
		authResource.login(doctorRepository.findAll().get(0));

		Integer bb = null;
		Patient p = new Patient("Zoran", "Zoric", null, new ArrayList<>());
		Allergy a = new Allergy(null, "Penicilin");
		a = allergyRepository.save(a);
		bb = a.getId();
		p.getAllergens().add(a);
		p = patientRepository.save(p);
		bb = p.getId();
		
		Ingredient i = new Ingredient(null, "Penicilin");
		i = ingredientRepository.save(i);
		bb = i.getId();
		
		Drug dg = new Drug(null, "AntiBio", new ArrayList<>(), DrugType.ANTIBIOTIC);
		dg.getIngredients().add(i);
		dg = drugRepository.save(dg);
		bb = dg.getId();
		
		Prescription ps = new Prescription(null, "Dva puta dnevno", dg);
		ps = prescriptionRepository.save(ps);
		bb = ps.getId();
		
		Doctor d = new Doctor("Uros", "Urosevic", null, "drUrosUros", "uros654321", DoctorType.REGULAR);
		d = doctorRepository.save(d);
		bb = d.getLicenceId();
		
		Disease ds = new Disease(null, "Infekcija uha");
		ds = diseaseRepository.save(ds);
		bb = ds.getId();

		Examination e = new Examination(null, new Date(), d, new ArrayList<>(), ds, ps);
		e = examRepository.save(e);
		bb = e.getId();
		
		Chart c = new Chart(new ArrayList<>(), p, null);
		c.getExaminations().add(e);
		bb = c.getId();
		c = chartRepository.save(c);
		bb = e.getId();
		
		assertEquals(reportsService.checkAllergyWarnings(p.getId(), dg, AuthenticationResource.getKieSessionOf()).size(), 1);
		
	}
	
}
