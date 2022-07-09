package prog04_ejerc3;

import java.util.Scanner;

/*
Ejercicio 3:  El Mínimo Común Múltiplo (MCM) de un conjunto de dos números es el número 
positivo más pequeño que es múltiplo de los dos números. Es posible calcular el MCM de
tres o más números. Por ejemplo, el MCM (2,3) es 6. El 6 es el múltiplo mas pequeño de 2 
y de 3. Implementa un programa Java que pida dos números por teclado, compruebe que son 
positivos y calcule su MCM. En caso de no ser ambos números positivos, el programa mostrará
un mensaje por pantalla y finalizará.
*/

/**
 *
 * @author FColls
 */
public class PROG04_Ejerc3 {
    
    public static void main(String[] args) {
     
    int a=0;
    int b=0;
    Scanner num = new Scanner(System.in);
    
    System.out.println("Hallar el mínimo común múltiplo (m.c.m.) de 2 números \n");
    System.out.println("Ingrese un número:");
    a= num.nextInt();
    System.out.println("Ingrese un número:");
    b = num.nextInt();
    int num1=a;
    int num2=b;
   
    if (a>0 && b>0){
            int div=2;
            int mcm=1;
            while (a>1 || b>1){
                if (a%div==0 && b%div!=0){
                    a=a/div;
                    mcm=mcm*div;
                }
                if (a%div!=0 && b%div==0){
                    b=b/div;
                    mcm=mcm*div;
                }
                if (a%div==0 && b%div==0){
                    a=a/div;
                    b=b/div;
                    mcm=mcm*div;
                }
                if (a%div!=0 && b%div!=0){
                    div++;
                }
                //Los if comprueban si el resto de la divison del numero entre 2 es igual a 0,
                //si no es 0 aumenta el divisor a 3, asi sucesivamente hasta que los dividendos
                //sean 1.
            }
            System.out.println("El mínimo común múltiplo de: "+num1+" y "+num2+" es= "+mcm);      
    }
    else {
            System.out.println("Ambos números deben ser positivos.....Programa Finalizado");
    } 
    }      
}
