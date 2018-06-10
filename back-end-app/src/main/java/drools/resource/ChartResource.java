package drools.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Chart> getChart(@PathVariable("id") Integer id) {
		if(id != null) {
			Chart c = chartService.findById(id);
			
			return ResponseEntity.ok().body(c);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/charts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Chart> getCharts() {
		return chartService.findAll();
	}
	
	@RequestMapping(value = "/api/charts", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Chart> newChart(@RequestBody Chart chart) throws URISyntaxException {
		if(chart.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Chart c = chartService.createNewChart(chart);
		if(c!=null) {
			return ResponseEntity.created(new URI("/api/charts/"+c.getId())).body(c);
		}
		
		return ResponseEntity.unprocessableEntity().body(c);
	}
	
	@RequestMapping(value = "/api/charts/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Chart> editChart(@RequestBody Chart chart, @PathVariable("id") Integer id) {
		if(chart.getId() == null || !chart.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim chartom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Chart c = chartService.updateChart(chart);
		if(c != null) {
			return ResponseEntity.ok().body(c);
		}
		
		return ResponseEntity.unprocessableEntity().body(c);
	}
	
	@RequestMapping(value = "/api/charts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Chart> deleteChart(@PathVariable("id") Integer id) {
		try {
			chartService.deleteChart(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
