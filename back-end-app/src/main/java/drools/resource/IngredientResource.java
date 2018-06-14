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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Ingredient;
import drools.service.IngredientService;

@CrossOrigin(value="http://localhost:4200", maxAge=1800)
@RestController
public class IngredientResource {

	@Autowired
	IngredientService ingredientService;
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") Integer id) {
		if(id != null) {
			Ingredient i = ingredientService.findById(id);
			
			return ResponseEntity.ok().body(i);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@RequestMapping(value = "/api/ingredients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Ingredient> getIngredients() {
		return ingredientService.findAll();
	}
	
	@RequestMapping(value = "/api/ingredients", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Ingredient> newIngredient(@RequestBody Ingredient ingredient) throws URISyntaxException {
		if(ingredient.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		Ingredient i = ingredientService.createNewIngredient(ingredient);
		if(i!=null) {
			return ResponseEntity.created(new URI("/api/ingredients/"+i.getId())).body(i);
		}
		
		return ResponseEntity.unprocessableEntity().body(i);
	}
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Ingredient> editIngredient(@RequestBody Ingredient ingredient, @PathVariable("id") Integer id) {
		if(ingredient.getId() == null || !ingredient.getId().equals(id)) {
			System.out.println("URL update sa neodgovarajucim ingredientom");
			return ResponseEntity.badRequest().body(null);
		}
		
		Ingredient i = ingredientService.updateIngredient(ingredient);
		if(i != null) {
			return ResponseEntity.ok().body(i);
		}
		
		return ResponseEntity.unprocessableEntity().body(i);
	}
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id) {
		try {
			ingredientService.deleteIngredient(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(null);
	}
	
	@RequestMapping(value = "/api/ingredients", method = RequestMethod.GET, params = "name",
			produces = MediaType.APPLICATION_JSON)
	public List<Ingredient> findIngredientsByName(@RequestParam("name") String name){
		return ingredientService.findByName(name);
	}
	
}
