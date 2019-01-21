package com.resources;

import com.dao.PagoDAO;
import com.models.Pago;
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
        PagoDAO dao = new PagoDAO();
        Pago pago = dao.getPago(idPago);
        return pago;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Pago addPago(Pago pago) {
        pago.setTipo(pago.getTipo());
        pago.setMonto(pago.getMonto());
        pago.setBanco(pago.getBanco());
        pago.setFechaPago(pago.getFechaPago());        
        PagoDAO dao = new PagoDAO();
        Pago pagoNuevo = dao.addPago(pago);
        return pagoNuevo;
    }
    
    @PUT
    @Path("/update/{idPago}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updatePago(@PathParam("idPago") int idPago, Pago emp) {
        PagoDAO dao = new PagoDAO();
        int count = dao.updatePago(idPago, emp);
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