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

import drools.model.Examination;
import drools.service.ExaminationService;

//@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class ExaminationResource {

	@Autowired
	ExaminationService examinationService;
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Examination> getExamination(@PathVariable("id") Integer id) {
		if(id != null) {
			Examination e = examinationService.findById(id);
			
			return ResponseEntity.ok().body(e);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/examinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Examination> getExaminations() {
		return examinationService.findAll();
	}
	
	@RequestMapping(value = "/api/examinations", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Examination> newExamination(@RequestBody Examination examination) throws URISyntaxException {
		if(examination.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Examination e = examinationService.createNewExamination(examination);
		if(e!=null) {
			return ResponseEntity.created(new URI("/api/examinations/"+e.getId())).body(e);
		}
		
		return ResponseEntity.unprocessableEntity().body(e);
	}
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Examination> editExamination(@RequestBody Examination examination, @PathVariable("id") Integer id) {
		if(examination.getId() == null || !examination.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim examinationom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Examination e = examinationService.updateExamination(examination);
		if(e != null) {
			return ResponseEntity.ok().body(e);
		}
		
		return ResponseEntity.unprocessableEntity().body(e);
	}
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Examination> deleteExamination(@PathVariable("id") Integer id) {
		try {
			examinationService.deleteExamination(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
}
