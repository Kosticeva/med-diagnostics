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

import drools.model.Disease;
import drools.service.DiseaseService;

@RestController
public class DiseaseResource {

	@Autowired
	DiseaseService diseaseService;
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Disease> getDisease(@PathVariable("id") Integer id) {
		if(id != null) {
			Disease d = diseaseService.findById(id);
			
			return ResponseEntity.ok().body(d);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/diseases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Disease> getDiseases() {
		return diseaseService.findAll();
	}
	
	@RequestMapping(value = "/api/diseases", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Disease> newDisease(@RequestBody Disease disease) throws URISyntaxException {
		if(disease.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Disease d = diseaseService.createNewDisease(disease);
		if(d!=null) {
			return ResponseEntity.created(new URI("/api/diseases/"+d.getId())).body(d);
		}
		
		return ResponseEntity.unprocessableEntity().body(d);
	}
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Disease> editDisease(@RequestBody Disease disease, @PathVariable("id") Integer id) {
		if(disease.getId() == null || !disease.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim diseaseom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Disease d = diseaseService.updateDisease(disease);
		if(d != null) {
			return ResponseEntity.ok().body(d);
		}
		
		return ResponseEntity.unprocessableEntity().body(d);
	}
	
	@RequestMapping(value = "/api/diseases/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Disease> deleteDisease(@PathVariable("id") Integer id) {
		try {
			diseaseService.deleteDisease(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
