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
   
    @Column(name="monto")
    private int monto;
    
    @Column(name="banco")
    private String banco;
    
    @Column(name="fechaPago")
    private Date fechaPago;
    
    
    
    public Pago(){}
    public Pago(int id, String tipo,int monto, String banco, Date fechaPago){
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.banco = banco;
        this.fechaPago=fechaPago;
        
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

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    


    
    
}

