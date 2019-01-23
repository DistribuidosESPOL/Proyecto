package com.resources;

import com.dao.UsuarioDAO;
import com.models.Usuario;
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
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Path("usuario")
public class UsuarioResource {

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
    public List<Usuario> getUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> listaUsuarios = dao.getUsuarios();
        return listaUsuarios;
    }
    
    @GET
    @Path("/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Usuario getUsuario(@PathParam("idUsuario") int idUsuario) {
        Usuario usuario;
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Usuario> bucket = redisson.getBucket(Integer.toString(idUsuario));
        usuario = bucket.get();
        if(usuario == null){
            UsuarioDAO dao = new UsuarioDAO();
            usuario = dao.getUsuario(idUsuario);
            bucket.set(usuario);
        }
        
        redisson.shutdown();
        return usuario;
    }
    
    @POST
    @Path("/add")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Usuario addUsuario(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioNuevo = dao.addUsuario(usuario);
        return usuarioNuevo;
    }
    
    @PUT
    @Path("/update/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateUsuario(@PathParam("idUsuario") int idUsuario, Usuario nuevoUsuario) {
        UsuarioDAO dao = new UsuarioDAO();
        int count = dao.updateUsuario(idUsuario, nuevoUsuario);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Usuario> bucket = redisson.getBucket(Integer.toString(idUsuario));
        Usuario usuario = bucket.get();
        if(usuario != null){
            bucket.set(nuevoUsuario);
        }
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
 
    @DELETE
    @Path("/delete/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteUsuario(@PathParam("idUsuario") int idUsuario) {
        UsuarioDAO dao = new UsuarioDAO();
        int count = dao.deleteUsuario(idUsuario);
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://127.0.0.1:6379");
        
        RedissonClient redisson = Redisson.create(config);
        RBucket<Usuario> bucket = redisson.getBucket(Integer.toString(idUsuario));
        Usuario usuario = bucket.get();
        if(usuario != null){
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
        return new Viewable("/UsuarioForm");
    }*/
}
