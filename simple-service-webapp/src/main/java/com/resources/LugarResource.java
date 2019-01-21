package com.resources;

import com.dao.LugarDAO;
import com.models.Lugar;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("lugar")
public class LugarResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    /*
    @GET
    @Produces("text/plain")
    public String getIt() {
        return "Listo!";
    }*/
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Lugar> getLugares() {
        LugarDAO dao = new LugarDAO();
        List<Lugar> listaLugares = dao.getLugares();
        return listaLugares;
    }
    
    @GET
    @Path("/{idLugar}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lugar getLugar(@PathParam("idLugar") int idLugar) {
        LugarDAO dao = new LugarDAO();
        Lugar lugar = dao.getLugar(idLugar);
        return lugar;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lugar addLugar(Lugar lugar) {
        lugar.setNombre(lugar.getNombre());
        lugar.setTipo(lugar.getTipo());
        lugar.setCapacidad(lugar.getCapacidad());
        lugar.setDireccion(lugar.getDireccion());
        LugarDAO dao = new LugarDAO();
        Lugar lugarNuevo = dao.addLugar(lugar);
        return lugarNuevo;
    }
    
    @PUT
    @Path("/update/{idLugar}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateLugar(@PathParam("idLugar") int idLugar, Lugar emp) {
        LugarDAO dao = new LugarDAO();
        int count = dao.updateLugar(idLugar, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idLugar}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteLugar(@PathParam("idLugar") int idLugar) {
        LugarDAO dao = new LugarDAO();
        int count = dao.deleteLugar(idLugar);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @GET
    @Path("showForm")
    @Produces(MediaType.TEXT_HTML)
    public Viewable showForm() {
        return new Viewable("/lugarForm");
    }
}
