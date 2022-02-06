/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.tareaferreteria;

import static com.pmp.tareaferreteria.Listado.separador;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random; 
import com.pmp.data.Productos;
import com.pmp.data.FerreteriaModelo;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static FerreteriaModelo model = new FerreteriaModelo();
    
    public static void main ( String[] args ) {
    
       Listado.encabezado("Gestion de Productos");
        String menuOption = "L";
        while (!menuOption.endsWith("S")){
            switch(menuOption){
                case "L":
                    mostrarListado();
                    break;
                case "I":
                  
                   insertarUnProducto();
                   break;
                case "A":
                    Listado.print("Actualizar");
                    actualizarProducto();
                   break;
                case "E":
                    Listado.print("Eliminar");
                    eliminarUnProducto();
                    break;
                default:
                   Listado.print("Opcion no encontrada!!!");
                   break;
           }
        
           Listado.menu();
            menuOption = entrada.nextLine().toUpperCase();
        }
        Listado.menu();
      Listado.separador();
    }
    private static void insertarUnProducto(){
        Productos nuevoProducto = new Productos();
        Listado.encabezado("Nuevo Producto");
        nuevoProducto.setNombre(Listado.capturarCampo(entrada,"Nombre:","Producto XYZ"));
        nuevoProducto.setPrecio(Double.parseDouble(Listado.capturarCampo(entrada,"Precio:","100.00")));
        nuevoProducto.setCantidad(Integer.parseInt(Listado.capturarCampo(entrada, "Cantidad", "10")));
        nuevoProducto.setDetalleProducto(Listado.capturarCampo(entrada, "detalleProducto", ""));
        Listado.separador();
       int insertado =  model.agregarProducto(nuevoProducto);
       if (insertado > 0){
           Listado.print("Producto Agregado Satisfactoriamente!");
       }
       Listado.separador();
     }
    
    private static void eliminarUnProducto(){
         int eliminar = 0;
         ArrayList<Productos> productos = model.obtenerProductos();
         
         Listado.encabezado("Eliminar un Producto");
         Listado.print("Ingresar ID del registro que desea Eliminar");
         int capturarId = Integer.parseInt(entrada.nextLine());
         
         for(int i = 0; i < productos.size(); i++){
             if(capturarId == (productos.get(i).getId())){
                 
                 Listado.print("Nombre: " + productos.get(i).getNombre() + "\n");
                 Listado.print("Precio: " + productos.get(i).getPrecio() + "\n");
                 Listado.print("Cantidad: " + productos.get(i).getCantidad() + "\n");
                 
                 Listado.print("Esta seguro en Eliminar este Producto? S/N");
                 String confirmar = entrada.nextLine().toUpperCase();
                 
                 if(confirmar.contentEquals("S")){
                     Listado.separador();
                     
                     eliminar = model.deleteProducto(capturarId);
                 }else{
                     break;
                 }
             }
         }
         
         if(eliminar > 0){
             Listado.print("El Producto ha sido Eliminado Exitosamente.");
         }
     }
    
    private static void actualizarProducto(){
         int actualizar = 0;
         Productos productoActualizar = new Productos();
         ArrayList<Productos> productos = model.obtenerProductos();
         
         Listado.encabezado("*Actualizacion de un Producto*");
         Listado.print("Ingresar ID del registro que desea actualizar: ");
         
         int tomarId = Integer.parseInt(entrada.nextLine());
         
         for(int i = 0; i < productos.size(); i++){
             if(tomarId == (productos.get(i).getId())){
                 productoActualizar.setNombre(Listado.capturarCampo(entrada,"Nombre", productos.get(i).getNombre()));
                 productoActualizar.setPrecio(Double.parseDouble(Listado.capturarCampo(entrada,"Precio", Double.toString(productos.get(i).getPrecio()))));
                 productoActualizar.setCantidad(Integer.parseInt(Listado.capturarCampo(entrada,"Cantidad", Integer.toString(productos.get(i).getCantidad()))));
                 productoActualizar.setDetalleProducto(Listado.capturarCampo(entrada, "DetalleProducto", productos.get(i).getDetalleProducto()));
                 productoActualizar.setId(productos.get(i).getId());
                 Listado.separador();
                 
             actualizar = model.actualizarProducto(productoActualizar);
             }
         }
         if(actualizar > 0){
             Listado.print("El Producto ha sido Actualizado Exitosamente.");
         }
         Listado.separador();
     }
    

    private static void mostrarListado() {
        Listado.printEncabezadoTabla();
        ArrayList<Productos> producto = model.obtenerProductos();
        for (int i = 0; i < producto.size(); i++){
            Listado.print(producto.get(i).getRow());
            Listado.separador();
        }
    }
}

