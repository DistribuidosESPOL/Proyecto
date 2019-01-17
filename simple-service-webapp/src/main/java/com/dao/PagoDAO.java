/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Pago;
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

public class PagoDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Pago addPago(Pago pago){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int pagoId = (Integer)session.save(pago);
        tx.commit();
        return pago;
    }
    
    public List<Pago> getPagos(){ 
        List<Pago> pagos = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Pago> query = builder.createQuery(Pago.class);
            Root<Pago> root = query.from(Pago.class);
            query.select(root);
            Query<Pago> q=session.createQuery(query);
            pagos =q.getResultList();
        }
        
        return pagos;
    }
    
    public Pago getPago(int id){ 
        
        Pago pago = (Pago) SessionUtil.getSession().get(Pago.class,
                id);
        return pago;
    }
 
    public int deletePago(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Pago> query = builder.createCriteriaDelete(Pago.class);
            Root<Pago> root = query.from(Pago.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updatePago(int id, Pago pago){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Pago> query = builder.createCriteriaUpdate(Pago.class);
            Root<Pago> root = query.from(Pago.class);
            query.set("tipo", pago.getTipo());
            query.set("monto", pago.getMonto());
            query.set("banco", pago.getBanco());
            query.set("fechaPago", pago.getFechaPago());
           
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}
