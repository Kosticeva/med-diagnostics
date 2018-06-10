package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Allergy;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer>{

	List<Allergy> findByName(String name);
}
