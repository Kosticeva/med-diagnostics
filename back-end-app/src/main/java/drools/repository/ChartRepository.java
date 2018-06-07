package drools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import drools.model.Chart;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Integer>{

	
}
