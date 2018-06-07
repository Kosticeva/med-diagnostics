package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Doctor;
import drools.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor findById(int id) {
		return doctorRepository.getOne(id);
	}
	
	public List<Doctor> findAll(){
		return doctorRepository.findAll();
	}
	
	public Doctor createNewDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public void deleteDoctor(int id) {
		doctorRepository.delete(id);
	}
}
