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
import drools.model.Allergy;
import drools.model.Patient;
import drools.repository.PatientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class PatientServiceTest {

	@Autowired
	PatientService patientService;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AllergyService allergyService;
	
	@Autowired
    private EntityManager em;
	
	public final String PATIENT_FNAME = "Default_pacijent_ime";
	public final String PATIENT_LNAME = "Default_pacijent_prezime";
	public final String PATIENT_UPDATE_FNAME = "Default_update_pacijent_ime";
	public final String PATIENT_UPDATE_LNAME = "Default_update_pacijent_prezime";
	public final Integer PATIENT_ID = 100;
	public final Integer NON_EX_PATIENT_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewPatient() {
		
		Patient p = new Patient();
		p.setId(null);
		p.setFirstName(PATIENT_FNAME);
		p.setLastName(PATIENT_LNAME);
		p.setAllergens(new ArrayList<>());
		
		Patient pp = patientService.savePatient(p);
		assertThat(pp.getId()).isNotNull();
		
		List<Patient> allPatientsMiddle = patientRepository.findAll();
		
		assertEquals(patientService.savePatient(p).getId(), pp.getId());
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewPatient() {
		
		Patient p = new Patient(PATIENT_FNAME, PATIENT_LNAME, PATIENT_ID, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		em.detach(p);
		
		List<Patient> allPatientsBefore = patientRepository.findAll();
		assertThat(patientService.savePatient(p)).isNotNull();
		
		List<Patient> allPatientsMiddle = patientRepository.findAll();
		assertEquals(allPatientsBefore.size(),allPatientsMiddle.size());
		
		p.setFirstName(PATIENT_UPDATE_FNAME);
		assertThat(patientService.savePatient(p)).isNotNull();
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectPatientPersistence() {
		Patient p = new Patient();
		
		List<Patient> allPatientsBefore = patientRepository.findAll();
		assertThat(patientService.savePatient(p)).isNull();
		
		List<Patient> allPatientsMiddle = patientRepository.findAll();
		assertEquals(allPatientsBefore.size(),allPatientsMiddle.size());
		
		em.detach(p);
		p.setFirstName("");
	
		assertThat(patientService.savePatient(p)).isNull();
		
		p.setFirstName(PATIENT_FNAME);
		assertThat(patientService.savePatient(p)).isNull();
		
		p.setLastName("");
		assertThat(patientService.savePatient(p)).isNull();
		
		p.setLastName(PATIENT_LNAME);
		p.setAllergens(new ArrayList<>());
		assertThat(patientService.savePatient(p)).isNotNull();
		em.detach(p);
		
		Allergy a = new Allergy();
		a.setId(13000);
		a.setName("Znj");
		a = allergyService.createNewAllergy(a);
		
		p.getAllergens().add(a);
		assertThat(patientService.savePatient(p)).isNotNull();
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsMiddle.size()+1);
	}
	
	@Test
	@Transactional
	public void testUpdateToNonExistingAllergy() {
		Patient p = new Patient(PATIENT_FNAME, PATIENT_LNAME, PATIENT_ID, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		em.detach(p);
		
		Allergy a = new Allergy();
		a.setId(13000);
		a.setName("Znj");
		p.getAllergens().add(a);
		
		List<Patient> allPatientsBefore = patientRepository.findAll();
		assertThat(patientService.savePatient(p)).isNull();
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsBefore.size());
	}
	
	@Test
	@Transactional
	public void testDeletePatient() throws SQLException{
		Patient p = new Patient(PATIENT_FNAME, PATIENT_LNAME, PATIENT_ID, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		em.detach(p);
		
		List<Patient> allPatientsBefore = patientRepository.findAll();
		patientService.deletePatient(p.getId());
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindPatient() {
		Patient p = new Patient(PATIENT_FNAME, PATIENT_LNAME, PATIENT_ID, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		em.detach(p);
		
		assertEquals(patientService.findById(p.getId()), p);
		assertThat(patientService.findById(NON_EX_PATIENT_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllPatient() {
		
		List<Patient> allPatientsBefore = patientRepository.findAll();
		Patient p1 = new Patient(PATIENT_FNAME, PATIENT_LNAME, PATIENT_ID, new ArrayList<>());
		Patient p2 = new Patient(PATIENT_UPDATE_FNAME, PATIENT_UPDATE_LNAME, NON_EX_PATIENT_ID, new ArrayList<>());
		
		p1 = patientRepository.saveAndFlush(p1);
		p2 = patientRepository.saveAndFlush(p2);
		
		List<Patient> allPatientsAfter = patientRepository.findAll();
		assertEquals(allPatientsAfter.size(),allPatientsBefore.size()+2);
		
	}
}
