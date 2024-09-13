package br.com.evonutri.EvoNutri.Controllers;

import br.com.evonutri.EvoNutri.DAO.NutriDAO;
import br.com.evonutri.EvoNutri.Model.Nutri;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/nutricionistas")
public class NutriController {

    private NutriDAO nutriDAO = new NutriDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNutricionista(Nutri nutricionista) {
        try {
            nutriDAO.addNutricionista(nutricionista);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding nutricionista: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNutricionistas() {
        try {
            List<Nutri> nutricionistas = nutriDAO.getAllNutricionistas();
            return Response.ok(nutricionistas).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving nutricionistas: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNutricionistaById(@PathParam("id") String id) {
        try {
            Nutri nutricionista = nutriDAO.getNutricionistaById(id);
            if (nutricionista != null) {
                return Response.ok(nutricionista).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Nutricionista not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving nutricionista: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNutricionista(Nutri nutricionista) {
        try {
            nutriDAO.updateNutricionista(nutricionista);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating nutricionista: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteNutricionista(@PathParam("id") String id) {
        try {
            nutriDAO.deleteNutricionista(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting nutricionista: " + e.getMessage()).build();
        }
    }
}
