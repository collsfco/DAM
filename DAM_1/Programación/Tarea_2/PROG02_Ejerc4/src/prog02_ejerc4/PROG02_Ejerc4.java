/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog02_ejerc4;
import java.util.Scanner;
/**
 *
 * @author FColls
 */
public class PROG02_Ejerc4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner teclado = new Scanner( System.in );
        System.out.print( "Introduzca la edad: " );
        int edad= teclado.nextInt();
        String a= "Es mayor de edad";
        String b= "Es menor de edad";
        String resultado = (edad>=18)?a:b;
        System.out.println(resultado);
        
    }
    
}
