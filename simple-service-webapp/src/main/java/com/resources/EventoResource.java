package com.resources;

import com.dao.EventoDAO;
import com.models.Evento;
import com.models.Lugar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
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
    @Template(name="/evento")
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/evento")
    public List<Evento> addEvento(@FormParam("tipo") String tipo, 
            @FormParam("nombre") String nombre, @FormParam("lugar") int lugar, 
            @FormParam("fecha") String fecha, @FormParam("precio") float precio,
            @FormParam("artista") String artista) {
        EventoDAO dao = new EventoDAO();
        Lugar lug = LugarResource.getLugar(lugar);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fec = null;
        try {
            fec = sourceFormat.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        Evento evento = new Evento(tipo, nombre, lug, fec, artista, precio);
        Evento eventoNuevo = dao.addEvento(evento);
        return dao.getEventos();
        /*try {
            return Response.temporaryRedirect(new URI("/simple-service-webapp/api/lugar")).build();
        } catch (URISyntaxException ex) {
            Logger.getLogger(LugarResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;*/
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
    
    /*@GET
    @Path("showForm")
    @Produces(MediaType.TEXT_HTML)
    public Viewable showForm() {
        return new Viewable("/EventoForm");
    }*/
}
