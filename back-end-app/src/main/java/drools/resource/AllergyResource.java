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
import org.springframework.web.bind.annotation.RestController;

import drools.model.Allergy;
import drools.service.AllergyService;


@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class AllergyResource {

	@Autowired
	AllergyService allergyService;
	
	@RequestMapping(value = "/api/allergys/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Allergy> getAllergy(@PathVariable("id") Integer id) {
		if(id != null) {
			Allergy a = allergyService.findById(id);
			
			System.out.println("ALERGY GET ID OK");
			return ResponseEntity.ok().body(a);
		}
		
		System.out.println("ALERGY GET ID BAD");
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/allergys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Allergy> getAllergys() {
		return allergyService.findAll();
	}
	
	@RequestMapping(value = "/api/allergys", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public List<Allergy> newAllergy(@RequestBody List<Allergy> allergies) throws URISyntaxException {
		List<Allergy> retVal = new ArrayList<Allergy>();

		for(Allergy allergy: allergies){
			
			Allergy a = allergyService.createNewAllergy(allergy);
			if(a!=null) {
				System.out.println("ALERGY POST OK");
				//return ResponseEntity.created(new URI("/api/allergys/"+a.getId())).body(a);
				retVal.add(a);
			}else{
				return null;
			}
		}

		return retVal;
	}
	
	@RequestMapping(value = "/api/allergys/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Allergy> editAllergy(@RequestBody Allergy allergy, @PathVariable("id") Integer id) {
		if(allergy.getId() == null || !allergy.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim allergyom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Allergy a = allergyService.updateAllergy(allergy);
		if(a != null) {
			System.out.println("ALERGY PUT OK");
			return ResponseEntity.ok().body(a);
		}
		
		System.out.println("ALERGY PUT BAD");
		return ResponseEntity.unprocessableEntity().body(a);
	}
	
	@RequestMapping(value = "/api/allergys/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Allergy> deleteAllergy(@PathVariable("id") Integer id) {
		try {
			allergyService.deleteAllergy(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		System.out.println("ALERGY DELETE OK");
		return ResponseEntity.ok().body(null);
	}
	
}
