package drools.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer>{
	List<Drug> findByNameStartingWithIgnoreCase(String name);
}
