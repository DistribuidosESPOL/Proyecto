package com.microservices;

import com.dao.PagoDAO;
import com.models.Asiento;
import com.models.Evento;
import com.models.Lugar;
import com.models.Pago;
import com.models.ResponseAsiento;
import com.models.ResponsePago;
import com.resources.AsientoResource;
import com.resources.EventoResource;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Template;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author daniel
 */
@Path("compra")
public class Compras {
    
    
    @GET
    @Path("/pagos")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/pagos")
    public List<Pago> mostrarPagos() {
        PagoDAO dao = new PagoDAO();
        List<Pago> pagos = dao.getPagos();
        return pagos;
    }     
    
    @GET
    @Path("/{idEvento}")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/asiento")
    public ResponseAsiento seleccionarAsientos(@PathParam("idEvento") int idEvento) {
        Evento evento = EventoResource.getEvento(idEvento);
        Lugar lugar = evento.getLugar();
        List<Asiento> asientos = AsientoResource.getAsientos();
        List<Asiento> as = new ArrayList<>();
        for(Asiento asiento : asientos) {
            if(asiento.getLugar().getId() == lugar.getId()) {
                as.add(asiento);
            }
        }
        ResponseAsiento ra = new ResponseAsiento(idEvento, as);
        return ra;
    }    
    
    @POST
    @Path("/pago/final")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Produces(MediaType.TEXT_HTML)
    //@Template(name="/pago")
    public Response comprarBoleto(@FormParam("tipo_pago") String tipo, 
            @FormParam("evento_id") int evento_id, @FormParam("total") float total) {
        PagoDAO dao = new PagoDAO();
        Evento evento = EventoResource.getEvento(evento_id);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fec = new Date();
        Pago pago = new Pago(tipo, evento, fec, total);
        Pago pagoNuevo = dao.addPago(pago);
        URI uri = UriBuilder.fromUri("compra/pagos").build();
        return Response.seeOther( uri ).build();
        
    }
    
    @POST
    @Path("/pago/{idEvento}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/pago")
    public ResponsePago mostrarBoleto(@PathParam("idEvento") int idEvento, 
        @FormParam("categoria") String categoria, @FormParam("num_asientos") int num_asientos) {
        Evento evento = EventoResource.getEvento(idEvento);
        float total = evento.getPrecio() * num_asientos;
        ResponsePago rp = new ResponsePago(evento, categoria, num_asientos, total);        
        return rp;
    }
    
}