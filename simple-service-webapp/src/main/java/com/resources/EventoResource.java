package com.resources;

import com.dao.EventoDAO;
import com.models.Evento;
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
@Path("evento")
public class EventoResource {

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
    public List<Evento> getEventos() {
        EventoDAO dao = new EventoDAO();
        List<Evento> listaEventos = dao.getEventos();
        return listaEventos;
    }
    
    @GET
    @Path("/{idEvento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Evento getEvento(@PathParam("idEvento") int idEvento) {
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.getEvento(idEvento);
        return evento;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Evento addEvento(Evento evento) {
        evento.setTipo(evento.getTipo());
        evento.setNombre(evento.getNombre());
        evento.setLugar(evento.getLugar());
        evento.setFecha(evento.getFecha());
        evento.setArtista(evento.getArtista());
        
        
        EventoDAO dao = new EventoDAO();
        Evento eventoNuevo = dao.addEvento(evento);
        return eventoNuevo;
    }
    
    @PUT
    @Path("/update/{idEvento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEvento(@PathParam("idEvento") int idEvento, Evento emp) {
        EventoDAO dao = new EventoDAO();
        int count = dao.updateEvento(idEvento, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idEvento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteEvento(@PathParam("idEvento") int idEvento) {
        EventoDAO dao = new EventoDAO();
        int count = dao.deleteEvento(idEvento);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    /*@GET
    @Path("showForm")
    @Produces(MediaType.TEXT_HTML)
    public Viewable showForm() {
        return new Viewable("/EventoForm");
    }*/
}
