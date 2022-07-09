package prog04_ejerc2;

import java.util.Scanner;

/*
Ejercicio 2: Un número es primo si solo tiene dos divisores: el 1 y el propio número.
Implementa un programa Java que pida por teclado 5 números. Para cada uno de ellos:
    #Comprueba si es negativo. En caso afirmativo, muestra el mensaje por pantalla "El número es negativo".
    #Si es positivo, deberá mostrar por pantalla si es primo o no.
    #Procesados los 5 números, el programa finaliza.
########################################################################################################
Un número primo es un número natural mayor que 1 que tiene únicamente dos divisores positivos distintos: 
él mismo y el 1. Así, los veinte primeros números primos son: 
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67 y 71.
########################################################################################################
*/
/**
 *
 * @author FColls
 */
public class PROG04_Ejerc2 {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner num = new Scanner(System.in);
        System.out.println("<<<< IDENTIFICADOR DE NÚMEROS PRIMOS >>>>>\n");
        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese un numero:");
            int numero = num.nextInt();
        
            if (numero<0) {
                System.out.println("Es un número negativo\n");  
            }
            if (numero==0 || numero==1){
                System.out.println("Ingrese un número mayor a 2.\n");
            }
            if (numero>1) { //Calcular números primos a partir del número 2.
                int contador_div = 0;
                int divisor = 2;
                while (divisor < numero) {
                    if (numero % divisor == 0) { //El resto debe ser diferente de 0,sólo debe ser 0 cuando se divide entre 1 y entre si mismo.
                    contador_div++; //Cada vez que el resto es 0 aumenta el contador
                    }
                    divisor++;
                }
                if (contador_div == 0 && numero>1) { //Si el contador es 0 quiere decir que el resto es diferente a 0, y entonces es primo.
                    System.out.printf("El número es primo.\n\n");
                } 
                else {
                System.out.printf("El número no es primo.\n\n");
                }      
            }     
        } 
    }
}
