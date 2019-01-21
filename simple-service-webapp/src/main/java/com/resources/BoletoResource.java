package com.resources;

import com.dao.BoletoDAO;
import com.models.Boleto;
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
@Path("boleto")
public class BoletoResource {

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
    public List<Boleto> getBoletos() {
        BoletoDAO dao = new BoletoDAO();
        List<Boleto> listaBoletos = dao.getBoletos();
        return listaBoletos;
    }
    
    @GET
    @Path("/{idBoleto}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Boleto getBoleto(@PathParam("idBoleto") int idBoleto) {
        BoletoDAO dao = new BoletoDAO();
        Boleto boleto = dao.getBoleto(idBoleto);
        return boleto;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Boleto addBoleto(Boleto boleto) {
        boleto.setUsuario(boleto.getUsuario());
        boleto.setEvento(boleto.getEvento());
        boleto.setFechaCompra(boleto.getFechaCompra());
        boleto.setPago(boleto.getPago());
        boleto.setAsiento(boleto.getAsiento());       
        BoletoDAO dao = new BoletoDAO();
        Boleto boletoNuevo = dao.addBoleto(boleto);
        return boletoNuevo;
    }
    
    @PUT
    @Path("/update/{idBoleto}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateBoleto(@PathParam("idBoleto") int idBoleto, Boleto emp) {
        BoletoDAO dao = new BoletoDAO();
        int count = dao.updateBoleto(idBoleto, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idBoleto}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteBoleto(@PathParam("idBoleto") int idBoleto) {
        BoletoDAO dao = new BoletoDAO();
        int count = dao.deleteBoleto(idBoleto);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    /*@GET
    @Path("showForm")
    @Produces(MediaType.TEXT_HTML)
    public Viewable showForm() {
        return new Viewable("/BoletoForm");
    }*/
}