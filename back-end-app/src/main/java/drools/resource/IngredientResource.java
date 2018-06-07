package drools.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import drools.model.Ingredient;
import drools.service.IngredientService;

@RestController
@Path("/api/ingredients")
public class IngredientResource {

	@Autowired
	IngredientService ingredientService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient getIngredient(@PathParam("id") int id) {
		return ingredientService.findById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingredient> getIngredients() {
		return ingredientService.findAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient newIngredient(Ingredient ingredient) {
		if(ingredient.getId() != null) {
			return null;
		}
		
		return ingredientService.createNewIngredient(ingredient);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient editIngredient(Ingredient ingredient, @PathParam("id") int id) {
		if(ingredient.getId() == null || ingredient.getId() != id) {
			return null;
		}
		
		return ingredientService.updateIngredient(ingredient);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteIngredient(@PathParam("id") int id) {
		ingredientService.deleteIngredient(id);
	}
	
}
