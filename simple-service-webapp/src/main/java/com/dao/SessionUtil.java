/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author anni
 */
public class SessionUtil {
    private static final SessionUtil instance=new SessionUtil();
    private static SessionFactory sessionFactory;
    
    public static SessionUtil getInstance(){
            return instance;
    }
    
    private SessionUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
                
        sessionFactory = configuration.buildSessionFactory();
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }
    public static void close() {
        if(sessionFactory != null) 
            sessionFactory.close();

        sessionFactory = null;
    }
}
