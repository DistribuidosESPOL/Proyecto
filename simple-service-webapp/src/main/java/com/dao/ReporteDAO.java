/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Reporte;
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

public class ReporteDAO {
    @PersistenceContext
    private EntityManager entityManager;
    
    public Reporte addReporte(Reporte reporte){
        Transaction tx = null;
        Session session = SessionUtil.getSession();
        tx = session.beginTransaction();
        int reporteId = (Integer)session.save(reporte);
        tx.commit();
        return reporte;
    }
    
    public List<Reporte> getReportes(){ 
        List<Reporte> reportes = new ArrayList<>();
        try (Session session = SessionUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reporte> query = builder.createQuery(Reporte.class);
            Root<Reporte> root = query.from(Reporte.class);
            query.select(root);
            Query<Reporte> q=session.createQuery(query);
            reportes =q.getResultList();
        }
        
        return reportes;
    }
    
    public Reporte getReporte(int id){ 
        
        Reporte reporte = (Reporte) SessionUtil.getSession().get(Reporte.class,
                id);
        return reporte;
    }
 
    public int deleteReporte(int id) {
        Transaction tx = null;
        int rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Reporte> query = builder.createCriteriaDelete(Reporte.class);
            Root<Reporte> root = query.from(Reporte.class);
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
    public int updateReporte(int id, Reporte reporte){
        Transaction tx = null;
        int rowCount=0;
        if(id<=0)
            return rowCount;
        try (Session session = SessionUtil.getSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Reporte> query = builder.createCriteriaUpdate(Reporte.class);
            Root<Reporte> root = query.from(Reporte.class);
            query.set("tipo", reporte.getTipo());
            query.set("usuario", reporte.getUsuario());
            query.set("fechaCreacion", reporte.getFechaCreacion());
           
            
            query.where(builder.equal(root.get("id"), id));
            Query q = session.createQuery(query);
            rowCount = q.executeUpdate();
            tx.commit();
        }
        return rowCount;
    }
    
}

