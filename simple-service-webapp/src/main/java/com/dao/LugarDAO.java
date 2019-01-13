/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import com.models.Lugar;
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
 * @author anni
 */
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class LugarDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Lugar addLugar(Lugar lugar){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int lugarId = (Integer)session.save(lugar);
        tx.commit();
        return lugar;
    }
    
    public List<Lugar> getLugares(){ 
        List<Lugar> lugares = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Lugar> query = builder.createQuery(Lugar.class);
            Root<Lugar> root = query.from(Lugar.class);
            query.select(root);
            Query<Lugar> q=session.createQuery(query);
            q.setCacheable(true);
            lugares =q.getResultList();
        }
        
        return lugares;
    }
    
    public Lugar getLugar(int id){ 
        
        Lugar lugar = (Lugar) SessionUtil.getSession().get(Lugar.class,
                id);
        return lugar;
    }
 
    public int deleteLugar(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Lugar> query = builder.createCriteriaDelete(Lugar.class);
            Root<Lugar> root = query.from(Lugar.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            q.setCacheable(true);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateLugar(int id, Lugar lugar){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Lugar> query = builder.createCriteriaUpdate(Lugar.class);
            Root<Lugar> root = query.from(Lugar.class);
            query.set("nombre", lugar.getNombre());
            query.set("tipo", lugar.getTipo());
            query.set("capacidad", lugar.getCapacidad());
            query.set("direccion", lugar.getDireccion());
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            q.setCacheable(true);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
}
