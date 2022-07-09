/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog03.figuras;

/**
 *
 * @author FColls
 */
public class Rectangulo {
    //Declare atributos para la base y la altura de un rectángulo.
    int base;
    int altura;
    
    //Declare un constructor vacío que inicialice los atributos a 0.
    public Rectangulo(){
        base=0;
        altura=0;
    }
    
    //Declara un constructor que inicialice base y altura.
    public Rectangulo(int base,int altura){
        
        this.base=base;
        this.altura=altura;
    }
    
    //Métodos getter y setter, para acceder y modificar los atributos.
    public void setBase(int base){
        this.base=base;
    }
    
    public float getBase(){
        return base;
    }
    
    public void setAltura(int altura){
        this.altura=altura;
    }
    
    public float getAltura(){
        return altura;
    }
    
    public float getArea(){  //Devuelve el área del rectángulo.
        float area=base*altura;
        return area;
    }

    //Devuelve una cadena conteniendo su área y su altura.
    @Override
    public String toString() {
        return "Rectangulo{" + "área=" + getArea()+"cm2" + ", altura=" + altura + "cm"+'}';
    }
    //Devuelve un booleadno indicando si el rectángulo es cuadrado.
    public boolean isCuadrado(){
        if(base==altura){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Devuelve una cadena indicando si es un rectángulo o un cuadrado.
    public String forma(){
        if(isCuadrado()==true){
            return "Es un cuadrado";
        }
        else{
            return "Es un rectángulo";
        }
    }
    
    
}
