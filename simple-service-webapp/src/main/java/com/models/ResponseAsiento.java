package com.models;

import java.util.List;

/**
 *
 * @author daniel
 */
public class ResponseAsiento {
    private int idEvento;
    private List<Asiento> lista;
    
    public ResponseAsiento() {}
    
    public ResponseAsiento(int idEvento, List<Asiento> lista) {
        this.idEvento = idEvento;
        this.lista = lista;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public List<Asiento> getLista() {
        return lista;
    }

    public void setLista(List<Asiento> lista) {
        this.lista = lista;
    }
    
}