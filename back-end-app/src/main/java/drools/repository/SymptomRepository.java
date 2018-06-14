package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Symptom;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer>{

	List<Symptom> findByName(String name);
	List<Symptom> findByNameStartingWith(String name);
}
