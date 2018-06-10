package drools.resource;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Examination;
import drools.service.ExaminationService;

@RestController
public class ExaminationResource {

	@Autowired
	ExaminationService examinationService;
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Examination getExamination(@PathParam("id") int id) {
		return examinationService.findById(id);
	}
	
	@RequestMapping(value = "/api/examinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Examination> getExaminations() {
		return examinationService.findAll();
	}
	
	@RequestMapping(value = "/api/examinations", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Examination newExamination(@RequestBody Examination examination) {
		if(examination.getId() != null) {
			return null;
		}
		
		return examinationService.createNewExamination(examination);
	}
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Examination editExamination(@RequestBody Examination examination, @PathParam("id") int id) {
		if(examination.getId() == null || examination.getId() != id) {
			return null;
		}
		
		return examinationService.updateExamination(examination);
	}
	
	@RequestMapping(value = "/api/examinations/{id}", method = RequestMethod.DELETE)
	public void deleteExamination(@PathParam("id") int id) {
		examinationService.deleteExamination(id);
	}
	
}
