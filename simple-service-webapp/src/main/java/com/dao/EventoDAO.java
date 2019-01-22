/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Evento;
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

public class EventoDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Evento addEvento(Evento evento){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int eventoId = (Integer)session.save(evento);
        tx.commit();
        return evento;
    }
    
    public List<Evento> getEventos(){ 
        List<Evento> eventos = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
            Root<Evento> root = query.from(Evento.class);
            query.select(root);
            Query<Evento> q=session.createQuery(query);
            eventos =q.getResultList();
        }
        
        return eventos;
    }
    
    public Evento  getEvento(int id){ 
        
        Evento evento = (Evento) SessionUtil.getSession().get(Evento.class,
                id);
        return evento;
    }
 
    public int deleteEvento(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Evento> query = builder.createCriteriaDelete(Evento.class);
            Root<Evento> root = query.from(Evento.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateEvento(int id, Evento evento){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Evento> query = builder.createCriteriaUpdate(Evento.class);
            Root<Evento> root = query.from(Evento.class);
            query.set("tipo", evento.getTipo());
            query.set("nombre", evento.getNombre());
            query.set("lugar", evento.getLugar());
            query.set("fecha", evento.getFecha());
            query.set("precio", evento.getPrecio());
            query.set("artista", evento.getArtista());
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}
