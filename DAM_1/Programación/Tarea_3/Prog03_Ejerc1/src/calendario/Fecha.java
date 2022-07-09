/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendario; //Paquete creado calendario. 

/**
 *
 * @author FColls
 */

//Clase enum para los meses del año
enum enumMes{ENERO,FEBRERO,MARZO,ABRIL,MAYO,JUNIO,JULIO,AGOSTO,
    SEPTIEMBRE,OCTUBRE,NOVIEMBRE,DICIEMBRE}

public class Fecha {  //Clase Fecha sin metodo main.
    
    int dia;    //Atributos para dia, mes y año.
    int anio;
    enumMes mes;
    
    public Fecha(enumMes mes){  //Constructor 1
        dia=0;
        anio=0;
        this.mes=mes; //this.mes=atributo--mes=parametro
    }
    
    public Fecha(int dia, enumMes mes,int anio){ //Constructor 2: inicialice todos los atributos de la clase.
        this.dia=dia;
        this.anio=anio;
        this.mes=mes;
    }
    
    //Métodos getter y setter, para acceder y modificar los atributos.
    public void setDia(int dia){ 
        this.dia=dia;
    }
    
    public int getDia(){
        return dia;
    }
    
    public void setMes(enumMes mes){
        this.mes=mes;
    }
    
    public enumMes getMes(){
        return mes;
    }
    
    public void setAnio(int anio){
        this.anio=anio;
    }
    
    public int getAnio(){
        return anio;
    }
    
    //Método que devuelve true si el valor contenido en la fecha es verano 
    //y false en caso contrario.
    public boolean isSummer(){
        if (mes==enumMes.JULIO || mes==enumMes.AGOSTO){
            return true;
        }
      
        if (mes==enumMes.JUNIO && dia >= 21){
            return true;
        }
        if (mes==enumMes.SEPTIEMBRE && dia <= 21){
            return true;
        }
        else{
           return false;
        } 
    }
    //Método que devuelve una cadena indicando si es verano o no.
     public String verano(){
        if (isSummer()==true){
            return "Es verano";   
        }
        else{
            return "No es verano";
        }
    }
    //Método que devuelve una cadena con la fecha en formato largo
    @Override
    public String toString() {
        return "La fecha es:" + dia + " de " + mes + " de " + anio;
    }  
}
