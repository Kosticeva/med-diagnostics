package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import drools.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	List<Doctor> findByUsername(String username);
}
