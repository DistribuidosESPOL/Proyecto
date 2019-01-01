/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tvenueslate file, choose Tools | Tvenueslates
 * and open the tvenueslate in the editor.
 */
package com.microservicios;

import com.clases.Venues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anni
 */
public class VenuesList {
    private static final Map<Integer, Venues> venuesMap = new HashMap<Integer, Venues>();
 
    static {
        initEmps();
    }
 
    private static void initEmps() {
        Venues venues1 = new Venues(1,"Estadio Modelo", "Estadio");
        Venues venues2 = new Venues(2, "Teatro Sánchez Aguilar", "Teatro");
        Venues venues3 = new Venues(3, "Estadio Banco Pichincha", "Estadio");
        Venues venues4 = new Venues(4, "Centro de Convenciones de Guayaquil "
                + "Simón Bolívar", "Centro de convenciones");
 
        venuesMap.put(venues1.getIdVenue(), venues1);
        venuesMap.put(venues2.getIdVenue(), venues2);
        venuesMap.put(venues3.getIdVenue(), venues3);
        venuesMap.put(venues4.getIdVenue(), venues4);
    }
 
    public static Venues getVenues(int venuesId) {
        return venuesMap.get(venuesId);
    }
 
    public static Venues addVenues(Venues venues) {
        venuesMap.put(venues.getIdVenue(), venues);
        return venues;
    }
 
    public static Venues updateVenues(Venues venues) {
        venuesMap.put(venues.getIdVenue(), venues);
        return venues;
    }
 
    public static void deleteVenues(int venuesId) {
        venuesMap.remove(venuesId);
    }
 
    public static List<Venues> getAllVenues() {
        Collection<Venues> c = venuesMap.values();
        List<Venues> list = new ArrayList<>();
        list.addAll(c);
        return list;
    }
     
    List<Venues> list;
 
}
