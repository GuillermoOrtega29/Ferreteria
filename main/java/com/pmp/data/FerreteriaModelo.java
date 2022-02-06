/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.data;

import java.sql.Connection;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Lenovo
 */
public class FerreteriaModelo {
     private Connection _conexionBD = null;
     
     public FerreteriaModelo(){
         _conexionBD = Conexion.getConexion();
         String sqlCreateTable = "CREATE TABLE IF NOT EXISTS productos"
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " nombre TEXT NOT NULL," 
                + " precio DECIMAL(10,2),"
                + " cantidad NUMERIC,"
                + " detalleProducto TEXT);";
         
           try{
            Statement comando = _conexionBD.createStatement();
            int resultado = comando.executeUpdate(sqlCreateTable);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
     }
     
     public ArrayList<Productos> obtenerProductos(){//va devolver un arreglo de una intedad
        try{
        ArrayList productos = new ArrayList<Productos>();
        Statement comandoSQL = _conexionBD.createStatement();
        ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * FROM productos;");
        while(registroEnTabla.next()){
            Productos productoActual = new Productos();
            productoActual.setId( registroEnTabla.getInt("id"));
            productoActual.setNombre( registroEnTabla.getString("nombre")); 
            productoActual.setDetalleProducto( registroEnTabla.getString("detalleProducto"));
            productoActual.setCantidad( registroEnTabla.getInt("cantidad"));
            productoActual.setPrecio( registroEnTabla.getDouble("precio"));
            
            productos.add(productoActual);
        }
        return productos;
     }catch (Exception ex){
            System.err.println(ex.getMessage());
            return new ArrayList<Productos>();
     }
    }
    
     public int agregarProducto(Productos nuevoProducto){
        try{
        String insertSql = "INSERT INTO productos (nombre, detalleProducto, cantidad, precio) values(?, ?, ?, ?);";
        PreparedStatement comandoSQL = _conexionBD.prepareStatement(insertSql); //es para hacer querys que tienen variables.
        comandoSQL.setString(1, nuevoProducto.getNombre());
        comandoSQL.setString(2, nuevoProducto.getDetalleProducto());
        comandoSQL.setInt(3, nuevoProducto.getCantidad());
        comandoSQL.setDouble (4, nuevoProducto.getPrecio());
        
        int RegistroAfectados = comandoSQL.executeUpdate();
        comandoSQL.close();
        return RegistroAfectados;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int actualizarProducto(Productos updateProducto){
        try{
        String updateSql = "UPDATE productos SET nombre=? , detalleProducto=?, cantidad=?, precio=? where id =?;"; //el signo de interrogacion es un parametro, 
        PreparedStatement comandoSQL = _conexionBD.prepareStatement(updateSql); //es para hacer querys que tienen variables.
        comandoSQL.setString(1, updateProducto.getNombre());
        comandoSQL.setString(2, updateProducto.getDetalleProducto());
        comandoSQL.setInt(3, updateProducto.getCantidad());
        comandoSQL.setDouble (4, updateProducto.getPrecio());
        comandoSQL.setInt(5, updateProducto.getId());
        
        int RegistroAfectados = comandoSQL.executeUpdate();
        comandoSQL.close();
        return RegistroAfectados;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
     public int deleteProducto(int id){
        try{
        String deleteSql = "DELETE FROM productos where id =?;"; //el signo de interrogacion es un parametro, 
        PreparedStatement comandoSQL = _conexionBD.prepareStatement(deleteSql); //es para hacer querys que tienen variables.
        comandoSQL.setInt(1, id);
        
        int RegistroAfectados = comandoSQL.executeUpdate();
        comandoSQL.close();
        return RegistroAfectados;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
