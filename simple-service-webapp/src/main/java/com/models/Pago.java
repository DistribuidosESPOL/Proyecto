package com.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author melina
 */
@Entity
@Table(name="pago")
public class Pago implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="fechaPago")
    private Date fechaPago;
    
    @ManyToOne
    @JoinColumn(name="evento")
    private Evento evento;
    
    @Column(name="total")
    private float total;
    
    public Pago(){}
    
    public Pago(int id, String tipo,Evento evento, Date fechaPago, float total){
        this.id = id;
        this.tipo = tipo;
        this.fechaPago=fechaPago;
        this.evento = evento;
        this.total = total;
    }
    
    public Pago(String tipo,Evento evento, Date fechaPago, float total){
        this.tipo = tipo;
        this.fechaPago=fechaPago;
        this.evento = evento;
        this.total = total;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
   
}