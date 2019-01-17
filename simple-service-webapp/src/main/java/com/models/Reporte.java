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
@Table(name="reporte")
public class Reporte{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
     @Column(name="tipo")
    private String tipo;
     
     
    @Column(name="usuario")
    private int usuario;
    
   
    @Column(name="fechaCreacion")
    private Date fechaCreacion;
    
    
    
    public Reporte(){}
    public Reporte(int id, String tipo, int usuario , Date fechaCreacion){
        this.id = id;
        this.tipo=tipo;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion; 
        
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

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   
    


    
    
}
