package com.resources;

import com.dao.AsientoDAO;
import com.models.Asiento;
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
@Path("asiento")
public class AsientoResource {

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
    public List<Asiento> getAsientos() {
        AsientoDAO dao = new AsientoDAO();
        List<Asiento> listaAsientos = dao.getAsientos();
        return listaAsientos;
    }
    
    @GET
    @Path("/{idAsiento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Asiento getAsiento(@PathParam("idAsiento") int idAsiento) {
        Asiento asiento;
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Asiento> bucket = redisson.getBucket(Integer.toString(idAsiento));
        asiento = bucket.get();
        if(asiento == null){
            AsientoDAO dao = new AsientoDAO();
            asiento = dao.getAsiento(idAsiento);
            bucket.set(asiento);
        }
        
        redisson.shutdown();
        return asiento;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Asiento addAsiento(Asiento asiento) {
        asiento.setCategoria(asiento.getCategoria());
        asiento.setNumero(asiento.getNumero());
        asiento.setLugar(asiento.getLugar());
        AsientoDAO dao = new AsientoDAO();
        Asiento asientoNuevo = dao.addAsiento(asiento);
        return asientoNuevo;
    }
    
    @PUT
    @Path("/update/{idAsiento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAsiento(@PathParam("idAsiento") int idAsiento, Asiento nuevoAsiento) {
        AsientoDAO dao = new AsientoDAO();
        int count = dao.updateAsiento(idAsiento, nuevoAsiento);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Asiento> bucket = redisson.getBucket(Integer.toString(idAsiento));
        Asiento asiento = bucket.get();
        if(asiento != null){
            bucket.set(nuevoAsiento);
        }
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idAsiento}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteAsiento(@PathParam("idAsiento") int idAsiento) {
        AsientoDAO dao = new AsientoDAO();
        int count = dao.deleteAsiento(idAsiento);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Asiento> bucket = redisson.getBucket(Integer.toString(idAsiento));
        Asiento asiento = bucket.get();
        if(asiento != null){
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
        return new Viewable("/AsientoForm");
    }*/
}