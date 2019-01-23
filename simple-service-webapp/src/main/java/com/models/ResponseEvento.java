/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.util.List;

/**
 *
 * @author anni
 */
public class ResponseEvento {
    private int idUsuario;
    private List<Evento> lista;
    
    public ResponseEvento() {
    }
    
    public ResponseEvento(int idUsuario, List<Evento> lista) {
        this.idUsuario = idUsuario;
        this.lista = lista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Evento> getLista() {
        return lista;
    }

    public void setLista(List<Evento> lista) {
        this.lista = lista;
    }
    
    
    
    
}
