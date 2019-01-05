/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tvenueslate file, choose Tools | Tvenueslates
 * and open the tvenueslate in the editor.
 */
package com.utils;

import com.clases.Lugar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anni
 */
public class LugarList {
    private static final Map<Integer, Lugar> venuesMap = new HashMap<Integer, Lugar>();
 
    static {
        initEmps();
    }
 
    private static void initEmps() {
        Lugar venues1 = new Lugar(1,"Estadio Modelo", "Estadio", 150, "fdsg");
        Lugar venues2 = new Lugar(2, "Teatro Sánchez Aguilar", "Teatro", 80, "dssg");
        Lugar venues3 = new Lugar(3, "Estadio Banco Pichincha", "Estadio", 40, "fddsdg");
        Lugar venues4 = new Lugar(4, "Centro de Convenciones de Guayaquil "
                + "Simón Bolívar", "Centro de convenciones", 100, "fdsg 45");
 
        venuesMap.put(venues1.getId(), venues1);
        venuesMap.put(venues2.getId(), venues2);
        venuesMap.put(venues3.getId(), venues3);
        venuesMap.put(venues4.getId(), venues4);
    }
 
    public static Lugar getVenues(int venuesId) {
        return venuesMap.get(venuesId);
    }
 
    public static Lugar addVenues(Lugar venues) {
        venuesMap.put(venues.getId(), venues);
        return venues;
    }
 
    public static Lugar updateVenues(Lugar venues) {
        venuesMap.put(venues.getId(), venues);
        return venues;
    }
 
    public static void deleteVenues(int venuesId) {
        venuesMap.remove(venuesId);
    }
 
    public static List<Lugar> getAllVenues() {
        Collection<Lugar> c = venuesMap.values();
        List<Lugar> list = new ArrayList<>();
        list.addAll(c);
        return list;
    }
     
    List<Lugar> list;
 
}
