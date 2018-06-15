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
import drools.model.Doctor;
import drools.model.enums.DoctorType;
import drools.repository.DoctorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class DoctorServiceTest {

	@Autowired
	DoctorService doctorService;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
    private EntityManager em;
	
	public final String DOCTOR_FNAME = "Default_ime";
	public final String DOCTOR_LNAME = "Default_prezime";
	public final String DOCTOR_UPDATE_FNAME = "Default_update_ime";
	public final String DOCTOR_UPDATE_LNAME = "Default_update_prezime";
	public final String DOCTOR_USERNAME = "Default_username";
	public final String DOCTOR_UPDATE_USERNAME = "Default_update_username";
	public final String DOCTOR_PASSWORD = "Default_password";
	public final String DOCTOR_UPDATE_PASSWORD = "Default_update_password";
	public final Integer DOCTOR_ID = 100;
	public final Integer NON_EX_DOCTOR_ID = 200;
	public final DoctorType TYPE = DoctorType.REGULAR;
	public final DoctorType UPDATE_TYPE = DoctorType.ADMINISTRATOR;
	
	@Test
	@Transactional
	public void testCreateNewDoctor() {
		
		List<Doctor> allDoctors = doctorRepository.findAll();
		for(Doctor ddd: allDoctors) {
			if(ddd.getUsername().equals(DOCTOR_USERNAME)) {
				doctorRepository.delete(ddd);
			}
		}
		
		Doctor d = new Doctor();
		d.setLicenceId(null);
		d.setFirstName(DOCTOR_FNAME);
		d.setLastName(DOCTOR_LNAME);
		d.setUsername(DOCTOR_USERNAME);
		d.setPassword(DOCTOR_PASSWORD);
		d.setType(TYPE);
		
		Doctor dd = doctorService.createNewDoctor(d);
		assertThat(dd.getLicenceId()).isNotNull();
		
		List<Doctor> allDoctorsMiddle = doctorRepository.findAll();
		
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsAfter.size(),allDoctorsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testUpdateNewDoctor() {
		
		Doctor d = new Doctor(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_PASSWORD, TYPE);
		d = doctorRepository.saveAndFlush(d);
		em.detach(d);
		
		d.setFirstName(DOCTOR_UPDATE_FNAME);
		List<Doctor> allDoctorsBefore = doctorRepository.findAll();
		
		Doctor neww = doctorService.updateDoctor(d);
		assertThat(neww).isNotNull();
		assertEquals(neww.getFirstName(), d.getFirstName());
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsBefore.size(), allDoctorsAfter.size());
		
		d.setUsername(DOCTOR_UPDATE_USERNAME);
		assertThat(doctorService.updateDoctor(d)).isNull();
	}
	
	@Test
	@Transactional
	public void testCorrectDoctorPersistence() {
		Doctor d = new Doctor();
		
		assertThat(doctorService.createNewDoctor(d)).isNull();
		em.detach(d);
		
		d.setFirstName("");
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setFirstName(DOCTOR_FNAME);
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setLastName("");
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setLastName(DOCTOR_LNAME);
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setUsername("");
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		List<Doctor> allDoctors = doctorRepository.findAll();
		for(Doctor ddd: allDoctors) {
			if(ddd.getUsername().equals(DOCTOR_USERNAME)) {
				doctorRepository.delete(ddd);
			}
		}
		
		d.setUsername(DOCTOR_USERNAME);
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setPassword("");
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setPassword(DOCTOR_PASSWORD);
		assertThat(doctorService.createNewDoctor(d)).isNull();
		
		d.setType(TYPE);
		
		List<Doctor> allDoctorsBefore = doctorRepository.findAll();
		assertThat(doctorService.createNewDoctor(d)).isNotNull();
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsBefore.size()+1, allDoctorsAfter.size());
	}
	
	@Test
	@Transactional
	public void testCreateToExistingName() {
		Doctor d = new Doctor(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_PASSWORD, TYPE);
		d = doctorRepository.saveAndFlush(d);
		em.detach(d);

		List<Doctor> allDoctorsBefore = doctorRepository.findAll();
		
		Doctor A = new Doctor(DOCTOR_UPDATE_FNAME, DOCTOR_UPDATE_LNAME, NON_EX_DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_UPDATE_PASSWORD, UPDATE_TYPE);
		assertThat(doctorService.createNewDoctor(A)).isNull();
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsAfter.size(),allDoctorsBefore.size());		
	}
	
	@Test
	@Transactional
	public void testDeleteDoctor() throws SQLException{
		Doctor d = new Doctor(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_PASSWORD, TYPE);
		d = doctorRepository.saveAndFlush(d);
		em.detach(d);
		
		List<Doctor> allDoctorsBefore = doctorRepository.findAll();
		doctorService.deleteDoctor(d.getLicenceId());
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsAfter.size(),allDoctorsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindDoctor() {
		Doctor d = new Doctor(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_PASSWORD, TYPE);
		d = doctorRepository.saveAndFlush(d);
		em.detach(d);
		
		assertEquals(doctorService.findById(d.getLicenceId()), d);
		assertThat(doctorService.findById(NON_EX_DOCTOR_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllDoctor() {
		
		List<Doctor> allDoctorsBefore = doctorRepository.findAll();
		Doctor d1 = new Doctor(DOCTOR_FNAME, DOCTOR_LNAME, DOCTOR_ID, DOCTOR_USERNAME, DOCTOR_PASSWORD, TYPE);
		Doctor d2 = new Doctor(DOCTOR_UPDATE_FNAME, DOCTOR_UPDATE_LNAME, NON_EX_DOCTOR_ID, DOCTOR_UPDATE_USERNAME, DOCTOR_UPDATE_PASSWORD, UPDATE_TYPE);
		
		d1 = doctorRepository.saveAndFlush(d1);
		d2 = doctorRepository.saveAndFlush(d2);
		
		List<Doctor> allDoctorsAfter = doctorRepository.findAll();
		assertEquals(allDoctorsAfter.size(),allDoctorsBefore.size()+2);
		
	}
}
