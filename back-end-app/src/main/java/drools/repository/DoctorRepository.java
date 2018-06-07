package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	
}
