/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;


/**
 *
 * @author melina
 */

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Usuario addUsuario(Usuario usuario){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int usuarioId = (Integer)session.save(usuario);
        tx.commit();
        return usuario;
    }
    
    public List<Usuario> getUsuarios(){ 
        List<Usuario> usuarios = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);
            query.select(root);
            Query<Usuario> q=session.createQuery(query);
            usuarios =q.getResultList();
        }
        
        return usuarios;
    }
    
    public Usuario getUsuario(int id){ 
        
        Usuario usuario = (Usuario) SessionUtil.getSession().get(Usuario.class,
                id);
        return usuario;
    }
 
    public int deleteUsuario(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Usuario> query = builder.createCriteriaDelete(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateUsuario(int id, Usuario usuario){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Usuario> query = builder.createCriteriaUpdate(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);
            query.set("nombre", usuario.getNombre());
            query.set("tipo", usuario.getTipo());
            query.set("alias", usuario.getAlias());
            query.set("contrasena", usuario.getContrasena());
            query.set("fechaRegistro", usuario.getFechaRegistro());
           
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}
