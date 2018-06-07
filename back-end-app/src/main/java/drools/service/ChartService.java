package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Chart;
import drools.repository.ChartRepository;

@Service
public class ChartService {
	
	@Autowired
	ChartRepository chartRepository;
	
	public Chart findById(int id) {
		return chartRepository.getOne(id);
	}
	
	public List<Chart> findAll(){
		return chartRepository.findAll();
	}
	
	public Chart createNewChart(Chart chart) {
		return chartRepository.save(chart);
	}
	
	public Chart updateChart(Chart chart) {
		return chartRepository.save(chart);
	}
	
	public void deleteChart(int id) {
		chartRepository.delete(id);
	}
}
