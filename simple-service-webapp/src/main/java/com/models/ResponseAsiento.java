package com.models;

import java.util.List;

/**
 *
 * @author daniel
 */
public class ResponseAsiento {
    private int idEvento;
    private List<Asiento> listaAsientos;
    
    public ResponseAsiento() {}
    
    public ResponseAsiento(int idEvento, List<Asiento> lista) {
        this.idEvento = idEvento;
        this.listaAsientos = lista;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }
    
}