/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices;

import com.models.Evento;
import com.models.ResponseEventos;
import com.models.Usuario;
import com.resources.UsuarioResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;


/**
 *
 * @author anni
 */
@Path("sesion")
public class Cuentas {
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/lugar")
    public Response registro(@FormParam("alias") String alias, 
            @FormParam("contrasena") String contrasena) {
        if(alias.length() < 3 || contrasena.length() < 6){
                URI uri = UriBuilder.fromUri("sesion/registroForm").build();
                return Response.seeOther( uri ).build();
        }
        Usuario user = new Usuario(alias, contrasena);
        UsuarioResource nuevoUsuario = new UsuarioResource();
        nuevoUsuario.addUsuario(user);
        
        URI uri = UriBuilder.fromUri("sesion").build();
        return Response.seeOther( uri ).build();
    }
    
    /*@POST
    @Path("/validar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/lugar")
    public Response inicioSesion(@FormParam("alias") String alias, 
            @FormParam("contrasena") String contrasena) throws URISyntaxException {
        Usuario user = new Usuario(alias, contrasena);
        UsuarioResource nuevoUsuario = new UsuarioResource();
        List<Usuario> usuarios = nuevoUsuario.getUsuarios();
        for(Usuario u:usuarios){
            if (u.getAlias().equals(alias) && u.getContrasena().equals(contrasena)){
                //  LugarDAO e = new LugarDAO();
                URI uri = UriBuilder.fromUri("../api/evento/"+u.getId()).build();
                return Response.seeOther( uri ).build();
            }
        }
        URI uri = UriBuilder.fromUri("../api/lugar").build();
        return Response.seeOther( uri ).build();
    }*/
    
    @POST
    @Path("/validar")
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/eventos")
    public Response inicioSesion(@FormParam("alias") String alias, 
            @FormParam("contrasena") String contrasena) throws URISyntaxException {
        
        Usuario user = new Usuario(alias, contrasena);
        UsuarioResource nuevoUsuario = new UsuarioResource();
        List<Usuario> usuarios = nuevoUsuario.getUsuarios();
        // Create Jersey client
        /*ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        
        WebResource webResource = client.resource("http://localhost:8080/simple-service-webapp/api/evento");
 
        ClientResponse response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        
        List<Evento> listaEventos = webResource.get(new GenericType<List<Evento>>() {});*/
        
        /*for(Usuario u:usuarios){
            if (u.getAlias().equals(alias) && u.getContrasena().equals(contrasena)){
                ResponseEventos re=new ResponseEventos(true, listaEventos);
                return re;
            }
        }
        ResponseEventos re=new ResponseEventos(false, listaEventos);
        return re;*/
        
        //List<Usuario> usuarios = nuevoUsuario.getUsuarios();
        for(Usuario u:usuarios){
            if (u.getAlias().equals(alias) && u.getContrasena().equals(contrasena)){
                //  LugarDAO e = new LugarDAO();
                URI uri = UriBuilder.fromUri("../micro/eventos/").build();
                return Response.seeOther( uri ).build();
            }
        }
        URI uri = UriBuilder.fromUri("sesion").build();
        return Response.seeOther( uri ).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable formInicioSesion() {
        return new Viewable("/inicioSesion");
    }
    
    @GET
    @Path("/registroForm")
    @Produces(MediaType.TEXT_HTML)
    public Viewable formRegistro() {
        return new Viewable("/registro");
    }
}