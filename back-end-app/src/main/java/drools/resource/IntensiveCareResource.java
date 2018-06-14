package drools.resource;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Chart;
import drools.service.IntensiveCareService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
@Scope(value="application", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IntensiveCareResource {

	@Autowired
	IntensiveCareService intensiveCareService;
	
	@RequestMapping(value = "/api/intensive-care", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Collection<Chart> getPatientsInIC(){
		return intensiveCareService.getPatientsInIC().values();
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	public Chart placePatientInIC(@PathVariable("id") Integer id) {
		return intensiveCareService.addPatientToIC(id);
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
	public Chart removePatientFromIC(@PathVariable("id") Integer id) {
		return intensiveCareService.removePatientFromIC(id);
	}
	
	@RequestMapping(value = "/api/intensive-care/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Chart checkPatientInIC(@PathVariable("id") Integer id) {
		return intensiveCareService.checkIfInIC(id);
	}
}
