package drools.resource;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Ingredient;
import drools.service.IngredientService;

@RestController
@Path("/api/ingredients")
public class IngredientResource {

	@Autowired
	IngredientService ingredientService;
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Ingredient getIngredient(@PathParam("id") int id) {
		return ingredientService.findById(id);
	}
	
	@RequestMapping(value = "/api/ingredients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Ingredient> getIngredients() {
		return ingredientService.findAll();
	}
	
	@RequestMapping(value = "/api/ingredients", method = RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Ingredient newIngredient(@RequestBody Ingredient ingredient) {
		if(ingredient.getId() != null) {
			return null;
		}
		
		return ingredientService.createNewIngredient(ingredient);
	}
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public Ingredient editIngredient(@RequestBody Ingredient ingredient, @PathParam("id") int id) {
		if(ingredient.getId() == null || ingredient.getId() != id) {
			return null;
		}
		
		return ingredientService.updateIngredient(ingredient);
	}
	
	@RequestMapping(value = "/api/ingredients/{id}", method = RequestMethod.DELETE)
	public void deleteIngredient(@PathParam("id") int id) {
		ingredientService.deleteIngredient(id);
	}
	
}
