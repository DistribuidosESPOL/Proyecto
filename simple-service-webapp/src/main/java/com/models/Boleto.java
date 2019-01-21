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
@Table(name="boleto")
public class  Boleto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="usuario")
    private int usuario;
    
   
    @Column(name="evento")
    private String evento;
    
    @Column(name="fechaCompra")
    private Date fechaCompra;
    
    @Column(name="pago")
    private int pago;
    
    @Column(name="asiento")
    private int asiento;
    
    public Boleto(){}
    public Boleto(int id, int usuario, String evento, Date fechaCompra, int pago, int asiento){
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaCompra = fechaCompra;
        this.pago=pago;
        this.asiento=asiento;
        
    }

    public int getId() {
        return id;
    }

    public int getUsuario() {
        return usuario;
    }

    public String getEvento() {
        return evento;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public int getPago() {
        return pago;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }


    
    
}
