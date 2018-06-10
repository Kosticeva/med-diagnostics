package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer>{

	List<Disease> findByName(String name);
}
