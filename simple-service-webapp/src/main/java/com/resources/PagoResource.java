package com.resources;

import com.dao.PagoDAO;
import com.models.Evento;
import com.models.Pago;
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
import org.glassfish.jersey.server.mvc.Viewable;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("pago")
public class PagoResource {

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
    public List<Pago> getPagos() {
        PagoDAO dao = new PagoDAO();
        List<Pago> listaPagos = dao.getPagos();
        return listaPagos;
    }
    
    @GET
    @Path("/{idPago}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Pago getPago(@PathParam("idPago") int idPago) {
        Pago pago;
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Pago> bucket = redisson.getBucket("pago_" + Integer.toString(idPago));
        pago = bucket.get();
        if(pago == null){
            PagoDAO dao = new PagoDAO();
            pago = dao.getPago(idPago);
            bucket.set(pago);
        }
        
        redisson.shutdown();
        return pago;
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public static Pago addPago(@FormParam("tipo") String tipo, @FormParam("fechaPago") String fechaPago,
            @FormParam("evento") int evento, @FormParam("total") float total) {
        PagoDAO dao = new PagoDAO();
        Evento even = EventoResource.getEvento(evento);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fec = null;
        try {
            fec = sourceFormat.parse(fechaPago);
        } catch (ParseException ex) {
            Logger.getLogger(EventoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pago pago = new Pago(tipo, even, fec, total);
        Pago pagoNuevo = dao.addPago(pago);
        return pagoNuevo;
    }
    
    @PUT
    @Path("/update/{idPago}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatePago(@PathParam("idPago") int idPago, Pago nuevoPago) {
        PagoDAO dao = new PagoDAO();
        int count = dao.updatePago(idPago, nuevoPago);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Pago> bucket = redisson.getBucket("pago_" + Integer.toString(idPago));
        Pago pago = bucket.get();
        if(pago != null){
            bucket.set(nuevoPago);
        }
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idPago}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deletePago(@PathParam("idPago") int idPago) {
        PagoDAO dao = new PagoDAO();
        int count = dao.deletePago(idPago);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Pago> bucket = redisson.getBucket("pago_" + Integer.toString(idPago));
        Pago pago = bucket.get();
        if(pago != null){
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
        return new Viewable("/PagoForm");
    }*/
}