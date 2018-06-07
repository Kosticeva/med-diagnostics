package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
