package com.resources;

import com.dao.ReporteDAO;
import com.models.Reporte;
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
 *
 */
@Path("reporte")
public class ReporteResource {

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
    public List<Reporte> getReportes() {
        ReporteDAO dao = new ReporteDAO();
        List<Reporte> listaReportes = dao.getReportes();
        return listaReportes;
    }
    
    @GET
    @Path("/{idReporte}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Reporte getReporte(@PathParam("idReporte") int idReporte) {
        Reporte reporte;
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Reporte> bucket = redisson.getBucket(Integer.toString(idReporte));
        reporte = bucket.get();
        if(reporte == null){
            ReporteDAO dao = new ReporteDAO();
            reporte = dao.getReporte(idReporte);
            bucket.set(reporte);
        }
        
        redisson.shutdown();
        return reporte;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Reporte addReporte(Reporte reporte) {
        reporte.setUsuario(reporte.getUsuario());
        reporte.setTipo(reporte.getTipo());
        reporte.setFechaCreacion(reporte.getFechaCreacion());
        ReporteDAO dao = new ReporteDAO();
        Reporte reporteNuevo = dao.addReporte(reporte);
        return reporteNuevo;
    }
    
    @PUT
    @Path("/update/{idReporte}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateReporte(@PathParam("idReporte") int idReporte, Reporte nuevoReporte) {
        ReporteDAO dao = new ReporteDAO();
        int count = dao.updateReporte(idReporte, nuevoReporte);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Reporte> bucket = redisson.getBucket(Integer.toString(idReporte));
        Reporte reporte = bucket.get();
        if(reporte != null){
            bucket.set(nuevoReporte);
        }
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idReporte}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteReporte(@PathParam("idReporte") int idReporte) {
        ReporteDAO dao = new ReporteDAO();
        int count = dao.deleteReporte(idReporte);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Reporte> bucket = redisson.getBucket(Integer.idReporte));
        Reporte reporte = bucket.get();
        if(reporte != null){
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
        return new Viewable("/ReporteForm");
    }*/
}