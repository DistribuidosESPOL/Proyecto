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
@Table(name="evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="nombre")
    private String nombre;
   
    @Column(name="lugar")
    private int lugar;
    
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="artista")
    private String artista;
    
    
    
    public Evento(){}
    public Evento(int id, String tipo,  String nombre, int lugar, Date fecha, String artista){
        this.id = id;
        this.tipo=tipo;
        this.nombre=nombre;
        this.lugar=lugar;
        this.fecha = fecha;
        this.artista=artista;
    }

    public int getId() {
        return id;
    }

    

    public String getTipo() {
        return tipo;
    }
    

    public String getNombre() {
        return nombre;
    }

    public int getLugar() {
        return lugar;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public String getArtista() {
        return artista;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    
    
}
