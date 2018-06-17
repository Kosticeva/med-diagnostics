package drools.resource;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Symptom;
import drools.service.SymptomService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class SymptomResource {

	@Autowired
	SymptomService symptomService;
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Symptom> getSymptom(@PathVariable("id") Integer id) {
		if(id != null) {
			Symptom s = symptomService.findById(id);
			
			return ResponseEntity.ok().body(s);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/symptoms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Symptom> getSymptoms() {
		return symptomService.findAll();
	}
	
	@RequestMapping(value = "/api/symptoms", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public List<Symptom> newSymptom(@RequestBody List<Symptom> symptoms) throws URISyntaxException {

		List<Symptom> retVal = new ArrayList<Symptom>();
		for(Symptom symptom: symptoms){
			Symptom s = symptomService.createNewSymptom(symptom);
			if(s!=null) {
				retVal.add(s);
			}else{
				System.out.println("post smyp - los simp");
				return null;
			}
		}

		return retVal;
	}
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Symptom> editSymptom(@RequestBody Symptom symptom, @PathVariable("id") Integer id) {
		if(symptom.getId() == null || !symptom.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim symptomom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Symptom s = symptomService.updateSymptom(symptom);
		if(s != null) {
			return ResponseEntity.ok().body(s);
		}
		
		return ResponseEntity.unprocessableEntity().body(s);
	}
	
	@RequestMapping(value = "/api/symptoms/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Symptom> deleteSymptom(@PathVariable("id") Integer id) {
		try {
			symptomService.deleteSymptom(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}

	@RequestMapping(value = "/api/symptoms", method = RequestMethod.GET, params = "name",
			produces = MediaType.APPLICATION_JSON)
	public List<Symptom> getSymptomsByName(@RequestParam("name") String name){
		return symptomService.findByName(name);
	}
}
