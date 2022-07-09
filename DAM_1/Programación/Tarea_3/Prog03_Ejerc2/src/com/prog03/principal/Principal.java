/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog03.Principal;

import com.prog03.figuras.Rectangulo; //Importamos la clase Rectangulo.

/**
 *
 * @author FColls
 */
public class Principal {
    
    public static void main(String[] args){
        
        //Instancioamos el objeto obj1 de la clase Rectangulo.
        Rectangulo obj1= new Rectangulo();
        //Ingresar valor de base y altura en cm.
        obj1.setBase(15);
        obj1.setAltura(8);
       
        //Instancioamos el objeto obj2 de la clase Rectangulo.
        Rectangulo obj2= new Rectangulo(4,4); 
        //Ingresar valor de base y altura en cm.
        
        System.out.println("Rectángulo número 1: ");
        System.out.println("Su área es: " + obj1.getArea()+"cm2"); //Devuelve el área del rectángulo.
        System.out.println(obj1.toString()); //Devuelve una cadena conteniendo su área y su altura.
        System.out.println(obj1.forma()+"\n");//Devuelve una cadena indicando si es un rectángulo o un cuadrado.
        
        System.out.println("Rectángulo número 2: ");
        System.out.println("Su área es: " + obj2.getArea()+"cm2"); //Devuelve el área del rectángulo.
        System.out.println(obj2.toString()); //Devuelve una cadena conteniendo su área y su altura.
        System.out.println(obj2.forma()); //Devuelve una cadena indicando si es un rectángulo o un cuadrado.
    }
    
}
