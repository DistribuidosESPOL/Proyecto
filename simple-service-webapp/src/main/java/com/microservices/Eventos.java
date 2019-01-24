/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices;

import com.models.Evento;
import com.models.ResponseEventos;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.mvc.Template;

/**
 *
 * @author anni
 */
@Path("eventos")
public class Eventos {
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/eventos")
    public List<Evento> paginaEventos() throws URISyntaxException {
        // Create Jersey client
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        
        WebResource webResource = client.resource("http://52.91.78.14:8080/simple-service-webapp/api/evento");
 
        ClientResponse response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        
        List<Evento> listaEventos = webResource.get(new GenericType<List<Evento>>() {});
        ResponseEventos r = new ResponseEventos(false, listaEventos);
        
        return listaEventos;
    }
    
    @GET
    @Path("/{idEvento}")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/evento")
    public Evento paginaEvento(@PathParam("idEvento") int idEvento) throws URISyntaxException {
        // Create Jersey client
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        
        WebResource webResource = client.resource("http://52.91.78.14:8080/simple-service-webapp/api/evento/"+idEvento);
 
        ClientResponse response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        
        Evento evento = webResource.get(new GenericType<Evento>() {});
        
        return evento;
    }
}
