/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices;

import com.dao.LugarDAO;
import com.models.Lugar;
import com.models.Usuario;
import com.resources.UsuarioResource;
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
public class cuentas {
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/lugar")
    public List<Lugar> registro(@FormParam("alias") String alias, 
            @FormParam("contrasena") String contrasena,
            @FormParam("tipo") String tipo) {
        Usuario user = new Usuario(tipo, alias, contrasena);
        UsuarioResource nuevoUsuario = new UsuarioResource();
        nuevoUsuario.addUsuario(user);
        
        LugarDAO e = new LugarDAO();
        return e.getLugares();
    }
    
    @POST
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
                URI uri = UriBuilder.fromUri("../api/evento").build();
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