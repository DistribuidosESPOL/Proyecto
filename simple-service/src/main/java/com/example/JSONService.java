package com.example;

import com.clases.Venues;
import com.microservicios.VenuesList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("venues")
public class JSONService {

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
    public List<Venues> getVenues_JSON() {
        List<Venues> listaVenues = VenuesList.getAllVenues();
        return listaVenues;
    }
    
    @GET
    @Path("/{idVenue}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Venues getVenues(@PathParam("idVenue") int idVenue) {
        return VenuesList.getVenues(idVenue);
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Venues addVenues(Venues emp) {
        return VenuesList.addVenues(emp);
    }
    
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Venues updateVenues(Venues emp) {
        return VenuesList.updateVenues(emp);
    }
 
    @DELETE
    @Path("/{idVenue}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteVenues(@PathParam("idVenue") int idVenue) {
        VenuesList.deleteVenues(idVenue);
    }
}
