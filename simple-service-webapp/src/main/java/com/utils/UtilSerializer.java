/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author anni
 */
public class UtilSerializer implements Serializable {
    public static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream( baos )) {
            oos.writeObject( o );
        }
        
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }
     
    /** Read the object from Base64 string.
     * @param s
     * @return 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException */
    public static Object fromString( String s ) throws IOException ,
                                                        ClassNotFoundException {
         byte [] data = Base64.getDecoder().decode( s );
         Object o;
        try (ObjectInputStream ois = new ObjectInputStream( 
                new ByteArrayInputStream(  data ) )) {
            o = ois.readObject();
        }
         return o;
    }
}
