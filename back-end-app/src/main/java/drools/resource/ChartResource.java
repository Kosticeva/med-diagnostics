package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Chart;
import drools.service.ChartService;

@RestController
public class ChartResource {

	@Autowired
	ChartService chartService;
	
	@RequestMapping(value = "/api/charts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Chart getChart(@PathParam("id") int id) {
		return chartService.findById(id);
	}
	
	@RequestMapping(value = "/api/charts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Chart> getCharts() {
		return chartService.findAll();
	}
	
	@RequestMapping(value = "/api/charts", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Chart newChart(@RequestBody Chart chart) {
		if(chart.getId() != null) {
			return null;
		}
		
		return chartService.createNewChart(chart);
	}
	
	@RequestMapping(value = "/api/charts/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Chart editChart(@RequestBody Chart chart, @PathParam("id") int id) {
		if(chart.getId() == null || chart.getId() != id) {
			return null;
		}
		
		return chartService.updateChart(chart);
	}
	
	@RequestMapping(value = "/api/charts/{id}", method = RequestMethod.DELETE)
	public void deleteChart(@PathParam("id") int id) {
		chartService.deleteChart(id);
	}
	
}
