package prog04_ejerc5;

import java.util.Scanner;

/*
Ejercicio 5: Cuando dividimos un número entre 0 se genera un valor indeterminado. 
En cualquier lenguaje de programación este tipo de operaciones genera un error de 
ejecución que debe ser controlado desde el código para evitar malas experiencias al usuario.
En Java, cuando se produce esta operación se genera la excepción ArithmeticException. 
Queremos implementar un programa Java que calcule la división de dos números solicitados 
por teclado (dividendo y divisor). El programa solicitará números indefinidamente hasta 
que los dos números solicitados sean -1. Se debe controlar mediante excepciones que el 
divisor no sea 0. En caso de serlo, se mostrará un mensaje por pantalla. También habrá 
que mostrar por pantalla el número de divisiones calculadas. Utiliza número enteros para 
las variables.
*/

/**
 *
 * @author FColls
 */
public class PROG04_Ejerc5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int div=0;
        int dividendo=0;
        int divisor=0;
        int numDivisiones=0;
        Scanner num = new Scanner(System.in);
    
    
        while (dividendo!=-1 && divisor!=-1){
            System.out.println(">>>>DIVISIÓN DE 2 NÚMEROS<<<<<");
            System.out.println("Ingrese un número (dividendo):");
            dividendo= num.nextInt();
            System.out.println("Ingrese un número (divisor):");
            divisor = num.nextInt();
        
            try{ //La declaración try, permite definir un bloque de código que se probará en busca de errores mientras se ejecuta.
            div=dividendo/divisor;
            System.out.println("El resultado de la división es= "+div);
            numDivisiones++;
            System.out.println(">>> Nº de divisiones realizadas= "+ numDivisiones+"\n");
            }
            catch (Exception e){ //La declaración catch, permite definir un bloque de código a ejecutar, si ocurre un error en el bloque try.
            System.out.println("El divisor es zero, intenta con un número diferente.\n");  
            } 
            
        }
    }
    
}
