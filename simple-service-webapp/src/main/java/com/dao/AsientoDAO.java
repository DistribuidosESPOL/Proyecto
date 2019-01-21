/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Asiento;
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

public class AsientoDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Asiento addAsiento(Asiento asiento){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int asientoId = (Integer)session.save(asiento);
        tx.commit();
        return asiento;
    }
    
    public List<Asiento> getAsientos(){ 
        List<Asiento> asientos = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Asiento> query = builder.createQuery(Asiento.class);
            Root<Asiento> root = query.from(Asiento.class);
            query.select(root);
            Query<Asiento> q=session.createQuery(query);
            asientos =q.getResultList();
        }
        
        return asientos;
    }
    
    public Asiento getAsiento(int id){ 
        
        Asiento asiento = (Asiento) SessionUtil.getSession().get(Asiento.class,
                id);
        return asiento;
    }
 
    public int deleteAsiento(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Asiento> query = builder.createCriteriaDelete(Asiento.class);
            Root<Asiento> root = query.from(Asiento.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateAsiento(int id, Asiento asiento){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Asiento> query = builder.createCriteriaUpdate(Asiento.class);
            Root<Asiento> root = query.from(Asiento.class);
            query.set("categoria", asiento.getCategoria());
            query.set("numero", asiento.getNumero());
            query.set("lugar", asiento.getLugar());
           
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}
