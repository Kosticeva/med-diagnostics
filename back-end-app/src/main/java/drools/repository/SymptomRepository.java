package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Symptom;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer>{

}
