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
public class ResponseEventos {
    private boolean idUsuario;
    private List<Evento> lista;
    
    public ResponseEventos() {
    }
    
    public ResponseEventos(boolean idUsuario, List<Evento> lista) {
        this.idUsuario = idUsuario;
        this.lista = lista;
    }

    public boolean getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(boolean idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Evento> getLista() {
        return lista;
    }

    public void setLista(List<Evento> lista) {
        this.lista = lista;
    }
    
    
    
    
}
