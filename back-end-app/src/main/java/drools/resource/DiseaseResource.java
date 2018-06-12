package drools.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Disease;
import drools.model.Link;
import drools.repository.LinkRepository;
import drools.repository.SymptomRepository;
import drools.service.DiseaseService;
import drools.service.LinkService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class DiseaseResource {

	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	LinkService linkService;
	
	@Autowired
	SymptomRepository symptomRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
	
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
	
	@RequestMapping(value="/api/diseases/{id}/symptoms/{id1}", method = RequestMethod.POST)
	public ResponseEntity<Link> addSymptomToDisease(@PathVariable("id") Integer id, @PathVariable("id1") Integer symptomId){
		Link l = linkService.connect(id, symptomId);
		
		if(l != null) {
			return ResponseEntity.ok().body(l);
		}
		
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value="/api/diseases/{id}/symptoms/{id1}", method = RequestMethod.DELETE)
	public ResponseEntity<Link> removeSymptomFromDisease(@PathVariable("id") Integer id, @PathVariable("id1") Integer symptomId){
		Link l = linkService.disconnect(id, symptomId);
		
		if(l != null) {
			return ResponseEntity.ok().body(l);
		}
		
		return ResponseEntity.badRequest().body(null);
	}
}
