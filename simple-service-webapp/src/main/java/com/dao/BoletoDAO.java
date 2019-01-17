/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Boleto;
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

public class BoletoDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Boleto addBoleto(Boleto boleto){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int boletoId = (Integer)session.save(boleto);
        tx.commit();
        return boleto;
    }
    
    public List<Boleto> getBoletos(){ 
        List<Boleto> boletos = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Boleto> query = builder.createQuery(Boleto.class);
            Root<Boleto> root = query.from(Boleto.class);
            query.select(root);
            Query<Boleto> q=session.createQuery(query);
            boletos =q.getResultList();
        }
        
        return boletos;
    }
    
    public Boleto getBoleto(int id){ 
        
        Boleto boleto = (Boleto) SessionUtil.getSession().get(Boleto.class,
                id);
        return boleto;
    }
 
    public int deleteBoleto(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Boleto> query = builder.createCriteriaDelete(Boleto.class);
            Root<Boleto> root = query.from(Boleto.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateBoleto(int id, Boleto boleto){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Boleto> query = builder.createCriteriaUpdate(Boleto.class);
            Root<Boleto> root = query.from(Boleto.class);
            query.set("usuario", boleto.getUsuario());
            query.set("evento", boleto.getEvento());
            query.set("fechaCompra", boleto.getFechaCompra());
           
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}
