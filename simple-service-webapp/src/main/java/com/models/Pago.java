package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 * @author melina
 */
@Entity
@Table(name="pago")
public class Pago{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id")
    private int id;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="fechaPago")
    private Date fechaPago;
    
    @Column(name="evento")
    private int evento;
    
    
    
    public Pago(){}
    public Pago(int id, String tipo,int evento, Date fechaPago){
        this.id = id;
        this.tipo = tipo;
        this.fechaPago=fechaPago;
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }
    
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    


    
    
}

