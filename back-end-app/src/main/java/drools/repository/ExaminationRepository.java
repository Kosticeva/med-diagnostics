package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer>{

}
