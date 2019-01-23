package com.microservices;

import com.models.Asiento;
import com.models.Evento;
import com.models.Lugar;
import com.resources.AsientoResource;
import com.resources.EventoResource;
import java.util.ArrayList;
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

/**
 *
 * @author daniel
 */
@Path("compra")
public class Compras {
    
    @GET
    @Path("/{idEvento}")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/asiento")
    public List<Asiento> seleccionarAsientos(@PathParam("idEvento") int idEvento) {
        Evento evento = EventoResource.getEvento(idEvento);
        Lugar lugar = evento.getLugar();
        List<Asiento> asientos = AsientoResource.getAsientos();
        List<Asiento> as = new ArrayList<>();
        for(Asiento asiento : asientos) {
            if(asiento.getLugar() == lugar) {
                as.add(asiento);
            }
        }
        return as;
    }    
    
    @POST
    @Path("/pago")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/pago")
    public Response verCompra(@FormParam("categoria") String categoria, 
            @FormParam("categoria") int num_boletos) {
        //Necesito tener aquí el id del evento para enviar el evento, el lugar y los 
        //asiento a la vista pago
        //si el num_boletos es mayor o igual al numero de asientos de esa categoria
        //enviar los datos al view pago, caso contrario redirigir a asiento.mustache
        return null;
    }
    
    @POST
    @Path("/pago/final")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/pago")
    public Response comprarBoleto() {
        
        return null;
    }
    
}