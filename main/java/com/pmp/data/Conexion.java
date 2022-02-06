/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.data;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Lenovo
 */
public class Conexion {
    private static Connection _conexion = null;
    private Conexion(){//metodo de seguridad para que no pueda ser instanciada
        
    }
    public static Connection getConexion(){
        try {
            if(_conexion == null){
                Class.forName("org.sqlite.JDBC");//JAVA DATABASE CONNECTION
                _conexion = DriverManager.getConnection("jdbc:sqlite:ferreteria.db"); //permite utilizar una conexion de una url en especifico, lee la conexion y da otra conexion
            } 
            return _conexion;
        } catch(Exception ex){
            System.err.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
