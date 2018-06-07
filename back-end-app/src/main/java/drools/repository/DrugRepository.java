package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Drug;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer>{

}
