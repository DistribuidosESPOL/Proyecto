package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author melina
 */
@Entity
@Table(name="asiento")
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="categoria")
    private String categoria;
    
   
    
    @Column(name="numero")
    private int numero;
    
    @Column(name="lugar")
    private int lugar;
    
    public Asiento(){}
    public Asiento(int id, String categoria,  int numero, int lugar){
        this.id = id;
        this.categoria = categoria;
        this.numero = numero;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    

    public String getCategoria() {
        return categoria;
    }
    

    public int getNumero() {
        return numero;
    }

    public int getLugar() {
        return lugar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
    
    
    
}
