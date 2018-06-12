package drools.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Doctor;
import drools.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Transactional
	public Doctor findById(int id) {
		return doctorRepository.findOne(id);
	}
	
	@Transactional
	public List<Doctor> findAll(){
		return doctorRepository.findAll();
	}
	
	@Transactional
	public Doctor createNewDoctor(Doctor doctor) {
		if(doctor.getFirstName() == null || doctor.getFirstName().equals("")) {
			System.out.println("Nema imena za doktora");
			return null;
		}
		
		if(doctor.getLastName() == null || doctor.getLastName().equals("")) {
			System.out.println("Nema prezimena za doktora");
			return null;
		}
		
		if(doctor.getUsername() == null) {
			System.out.println("Nema kor imena za doktora");
			return null;
		}
		
		if(doctorRepository.findByUsername(doctor.getUsername())!= null) {
			System.out.println("Vec iskoriceno kor ime za doktora");
			return null;
		}
		
		if(doctor.getPassword() == null || doctor.getPassword().equals("")) {
			System.out.println("Nema sifre za doktora");
			return null;
		}
		
		if(doctor.getType() == null) {
			System.out.println("Nema tipa za doktora");
			return null;
		}
		
		return doctorRepository.save(doctor);
	}
	
	@Transactional
	public Doctor updateDoctor(Doctor doctor) {
		if(doctor.getFirstName() == null || doctor.getFirstName().equals("")) {
			System.out.println("Nema imena za doktora");
			return null;
		}
		
		if(doctor.getLastName() == null || doctor.getLastName().equals("")) {
			System.out.println("Nema prezimena za doktora");
			return null;
		}
		
		if(doctor.getUsername() == null) {
			System.out.println("Nema kor imena za doktora");
			return null;
		}
		
		if(!doctorRepository.findOne(doctor.getLicenceId()).getUsername().equals(doctor.getUsername())) {
			System.out.println("Nedozvoljena promena kor imena za doktora");
			return null;
		}
		
		if(doctor.getPassword() == null || doctor.getPassword().equals("")) {
			System.out.println("Nema sifre za doktora");
			return null;
		}
		
		if(doctor.getType() == null) {
			System.out.println("Nema tipa za doktora");
			return null;
		}
		
		return doctorRepository.save(doctor);
	}
	
	@Transactional
	public void deleteDoctor(int id) throws SQLException {
		doctorRepository.delete(id);
	}
}
