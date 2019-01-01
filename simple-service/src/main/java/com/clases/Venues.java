/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

/**
 *
 * @author anni
 */
public class Venues {
    private int id;
    private String nombre;
    private String categoria;
    
    public Venues(){}
    public Venues(int id, String nombre, String categoria){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    public int getIdVenue(){
        return this.id;
    }
    
    public void setIdVenue(int id){
        this.id = id;
    }
    
    public String getNombreVenue(){
        return this.nombre;
    }
    
    public void setNombreVenue(String nombre){
        this.nombre = nombre;
    }
    
    public String getCategoriaVenue(){
        return this.categoria;
    }
    
    public void setCategoriaVenue(String categoria){
        this.categoria = categoria;
    }
    
    
}
