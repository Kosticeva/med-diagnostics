package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

}
