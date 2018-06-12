package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Disease;
import drools.model.Link;
import drools.model.Symptom;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {

	Link findByDiseaseAndSymptom(Disease disease, Symptom symptom);
}
