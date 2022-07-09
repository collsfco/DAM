package prog04_ejerc1;
/*
Ejercicio 1: Implementar un programa que muestre la tabla de multiplicar de un número 
leido desde teclado utilizando al menos tres bucles diferentes. El número leído desde 
teclado debe ser menor que 30. En caso contrario se mostrará un mensaje por pantalla y 
el programa finalizará.
*/

import java.util.Scanner;
/**
 *
 * @author FColls
 */
public class PROG04_Ejerc1 {

    public static void esunFor(int numX)
    {
        //Calcula la multiplicación de un número aplicando un FOR.
        System.out.println(">>>>ES UN FOR<<<<"); 
        for (int i = 1; i <= 10; i++) {
            int multi= numX*i;
        System.out.println(numX+"X"+i+"= "+multi);
        }
    }
    public static void esunWhile(int numX)
    {
        //Calcula la multiplicación de un número aplicando un WHILE.
        System.out.println(">>>>ES UN WHILE<<<<");
        int i = 1;
        while (i <= 10) {
            int multi= numX*i;
            System.out.println(numX+"X"+i+"= "+multi);
            i++;
        }
    } 
    public static void esunDowhile (int numX)
    {
        //Calcula la multiplicación de un número aplicando un DO WHILE.
        System.out.println(">>>>ES UN DOWHILE<<<<");
        int i = 1;
        do {
            int multi= numX*i;
            System.out.println(numX+"X"+i+"= "+multi);
            i++;
        }
        while (i <=10);
    }
    
    public static void main(String[] args) {
        
        System.out.println("<<<<<TABLA DE MULTIPLICAR CON BUCLES>>>>>");
        Scanner numero = new Scanner(System.in);
        System.out.println("Ingrese un número entre 0 y 30");
        int numX = numero.nextInt();
        
        if (numX>0 && numX<= 30) { //Rango para aceptar solo números positivos y menores de 30.
            System.out.println("\n");
            esunFor(numX);
            System.out.println("\n");
            esunWhile(numX);
            System.out.println("\n");
            esunDowhile(numX);
        }
        else{
            System.out.println("Número fuera de rango....Programa Finalizado");
        }   
    }
}
