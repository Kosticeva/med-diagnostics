package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer>{

}
