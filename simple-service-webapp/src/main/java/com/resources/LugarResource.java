package com.resources;

import com.dao.LugarDAO;
import com.models.Lugar;
import java.net.URI;
import java.util.List;
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
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

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
    
    /*@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })*/
    
    @GET
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Produces(MediaType.TEXT_HTML)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    //@Template(name="/lugar")
    public List<Lugar> getLugares() {
        
        LugarDAO dao;
        dao = new LugarDAO();
        List<Lugar> listaLugares = dao.getLugares();
        
        return listaLugares;
    }
    
    @GET
    @Path("/{idLugar}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public static Lugar getLugar(@PathParam("idLugar") int idLugar) {
        Lugar lugar;
        /*Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Lugar> bucket = redisson.getBucket(Integer.toString(idLugar));
        lugar = bucket.get();
        if(lugar==null){*/
            LugarDAO dao = new LugarDAO();
            lugar = dao.getLugar(idLugar);
            /*bucket.set(lugar);
        }
        
        redisson.shutdown();*/
        return lugar;
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/lugar")
    public Response addLugar(@FormParam("nombre") String nombre, @FormParam("tipo") String tipo,
                        @FormParam("capacidad") int capacidad, @FormParam("direccion") String direccion) {
        LugarDAO dao = new LugarDAO();
        Lugar lugar = new Lugar(nombre, tipo, capacidad, direccion);
        Lugar lugarNuevo = dao.addLugar(lugar);
        URI uri = UriBuilder.fromUri("lugar").build();
        return Response.seeOther(uri).build();
    }
    
    /**
     *
     * @param idLugar
     * @param nuevoLugar
     * @return
     */
    @PUT
    @Path("/update/{idLugar}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateLugar(@PathParam("idLugar") int idLugar, Lugar nuevoLugar) {
        LugarDAO dao = new LugarDAO();
        int count = dao.updateLugar(idLugar, nuevoLugar);
        
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Lugar> bucket = redisson.getBucket(Integer.toString(idLugar));
        Lugar lugar = bucket.get();
        if(lugar!=null){
            bucket.set(nuevoLugar);
        }
        
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
        
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Lugar> bucket = redisson.getBucket(Integer.toString(idLugar));
        Lugar lugar = bucket.get();
        if(lugar!=null){
            bucket.delete();
        }
        
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
