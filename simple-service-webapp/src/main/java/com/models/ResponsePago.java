/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author daniel
 */
public class ResponsePago {
    private Evento evento;
    private String categoria;
    private int num_asientos;
    private float total;
    
    public ResponsePago() {}

    public ResponsePago(Evento evento, String categoria, int num_asientos, float total) {
        this.evento = evento;
        this.categoria = categoria;
        this.num_asientos = num_asientos;
        this.total = total;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getNum_asientos() {
        return num_asientos;
    }

    public void setNum_asientos(int num_asientos) {
        this.num_asientos = num_asientos;
    }
    
    
    
}