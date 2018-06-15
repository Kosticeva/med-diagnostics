package drools.resource;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Drug;
import drools.service.DrugService;

//@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class DrugResource {

	@Autowired
	DrugService drugService;
	
	@RequestMapping(value = "/api/drugs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Drug getDrug(@PathVariable("id") Integer id) {
		return drugService.findById(id);
	}
	
	@RequestMapping(value = "/api/drugs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Drug> getDrugs() {
		return drugService.findAll();
	}
	
	@RequestMapping(value = "/api/drugs", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Drug newDrug(@RequestBody Drug drug) {
		if(drug.getId() != null) {
			return null;
		}
		
		return drugService.saveDrug(drug);
	}
	
	@RequestMapping(value = "/api/drugs/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Drug editDrug(@RequestBody Drug drug, @PathVariable("id") Integer id) {
		if(drug.getId() == null || drug.getId() != id) {
			return null;
		}
		
		return drugService.saveDrug(drug);
	}
	
	@RequestMapping(value = "/api/drugs/{id}", method = RequestMethod.DELETE)
	public void deleteDrug(@PathVariable("id") Integer id) {
		drugService.deleteDrug(id);
	}
	
	@RequestMapping(value = "/api/drugs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON,
			params="name")
	public List<Drug> getDrugsByName(@RequestParam("name") String name){
		return drugService.findByName(name);
	}
}
