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
import org.glassfish.jersey.server.mvc.Template;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

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
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/eventos")
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Evento> getEventos() {
        EventoDAO dao = new EventoDAO();
        List<Evento> listaEventos = dao.getEventos();
        return listaEventos;
    }
    
    @GET
    @Path("/{idEvento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Evento getEvento(@PathParam("idEvento") int idEvento) {
        Evento evento;
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Evento> bucket = redisson.getBucket(Integer.toString(idEvento));
        evento = bucket.get();
        if(evento == null){
            EventoDAO dao = new EventoDAO();
            evento = dao.getEvento(idEvento);
            bucket.set(evento);
        }
        
        redisson.shutdown();
        return evento;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Evento addEvento(Evento evento) {
        EventoDAO dao = new EventoDAO();
        Evento eventoNuevo = dao.addEvento(evento);
        return eventoNuevo;
    }
    
    @PUT
    @Path("/update/{idEvento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEvento(@PathParam("idEvento") int idEvento, Evento nuevoEvento) {
        EventoDAO dao = new EventoDAO();
        int count = dao.updateEvento(idEvento, nuevoEvento);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Evento> bucket = redisson.getBucket(Integer.toString(idEvento));
        Evento evento = bucket.get();
        if(evento != null){
            bucket.set(nuevoEvento);
        }
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
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Evento> bucket = redisson.getBucket(Integer.toString(idEvento));
        Evento evento = bucket.get();
        if(evento != null){
            bucket.delete();
        }
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
