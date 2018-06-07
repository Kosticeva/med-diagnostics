package drools.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Chart;
import drools.service.ChartService;

@RestController
@Path("/api/charts")
public class ChartResource {

	@Autowired
	ChartService chartService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Chart getChart(@PathParam("id") int id) {
		return chartService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chart> getCharts() {
		return chartService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Chart newChart(Chart chart) {
		if(chart.getId() != null) {
			return null;
		}
		
		return chartService.createNewChart(chart);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Chart editChart(Chart chart, @PathParam("id") int id) {
		if(chart.getId() == null || chart.getId() != id) {
			return null;
		}
		
		return chartService.updateChart(chart);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteChart(@PathParam("id") int id) {
		chartService.deleteChart(id);
	}
	
}
