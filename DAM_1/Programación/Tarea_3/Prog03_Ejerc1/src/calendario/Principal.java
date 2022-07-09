/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendario; //Paquete creado calendario. 

/**
 *
 * @author FColls
 */
public class Principal {
    
    public static void main(String[] args) {
        
        //Instancia un objeto de la clase Fecha denominado objFecha1 con el primer constructor.
        Fecha objFecha1 = new Fecha(enumMes.AGOSTO); 
        objFecha1.setDia(19);
        objFecha1.setAnio(1992);
        
        //Instancia otro objeto de la clase Fecha denomiando objFecha2 con el segundo constructor.
        Fecha objFecha2= new Fecha(05,enumMes.MAYO,2014);
      
        System.out.println("Primera fecha, inicializada con el primer constructor.");
        //Muestra la fecha por pantalla en formato largo.
        System.out.println(objFecha1.toString()); 
        //Muestra un mensaje por pantalla indicando si la fecha es verano
        System.out.println(objFecha1.isSummer()+": "+ objFecha1.verano() + "\n"); 
      
        System.out.println("Segunda fecha, inicializada con el segundo constructor.");
        //Muestra el año de esta fecha por pantalla.
        System.out.println("La fecha 2 contiene el año " + objFecha2.anio );
        //Muestra la fecha en formato largo por pantalla.
        System.out.println(objFecha2.toString());
        ////Muestra un mensaje por pantalla indicando si la fecha es verano
        System.out.println(objFecha2.isSummer()+": "+ objFecha2.verano() + "\n");
      
        
    }
    
}
