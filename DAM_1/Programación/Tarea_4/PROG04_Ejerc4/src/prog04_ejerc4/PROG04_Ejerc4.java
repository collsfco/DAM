package prog04_ejerc4;

import java.util.Scanner;

/*
Ejercicio 4: Deseamos implementar un juego en Java que permita al usuario adivinar un número oculto (que será aleatorio).  
El funcionamiento será el siguiente:
#El programa mostrará un pequeño menú en pantalla con las siguientes opciones (1. Configurar, 2. Jugar, 3. Salir).
    
    -Si el usuario selecciona el la primera opción, se le solicitará por teclado el número de intentos 
    permitidos (numInt) y el número máximo  (numMax) generado.
    
    -Si el usuario selecciona la opción 2,  el programa generará un número aleatorio entre 0 y numMax que 
    será el número a adivinar (numOculto). A partir de este momento, se le solicitarán al usuario números 
    hasta adivinar el número oculto.
        >Si el usuario adivina el número, se mostrará un mensaje "Has ganado!. Has necesitado X intentos".
        >Si se consume el número de intentos sin adivinar el número, se mostrará el mensaje "Perdiste!. Intentos consumidos".
        >En cada intento, si el usuario no adivina el número se le proporcionará una pista, por ejemplo, "El número oculto es menor".
        >En ambos casos, la siguiente acción será mostrar el menú.
        >Si el usuario selecciona Jugar sin configurar previamente el número de intentos y el número máximo generado, 
        se tomarán como valores por defecto: numInt=5 y numMax=10.
    -Si el usuario pulsa la opción 3, el programa finaliza.

#Para generar un número aleatorio en java puedes utilizar el siguiente código:
int numOculto = (int)Math.floor(Math.random()*20+1); //genera un número aleatorio entre 0 y 20, ambos incluidos.
*/

/**
 *
 * @author FColls
 */
public class PROG04_Ejerc4 {

    public static void game(int numInt,int numMax){
        //Metodo para realizar el juego, genera el número aleatorio y
        //comprueba que se ha acertado el número correcto.
        int numOculto = (int)Math.floor(Math.random()*numMax+1);
        //System.out.println("numero oculto:" + numOculto);
        System.out.println("\n<<<<<Intenta adivinar el número oculto>>>>>");
        Scanner num = new Scanner(System.in);
        for (int i = 1; i <=numInt; i++) {
            System.out.println("Ingresa un número: ");
            int numAdivinar = num.nextInt();
            
           if(numAdivinar==numOculto){
               System.out.println("☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺");
               System.out.println("☺ Has ganado!. Has necesitado "+i+" intentos☺");
               System.out.println("☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺☺");
               break;
           }
           if(i==numInt){
               System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
               System.out.println("Perdiste!. Intentos consumidos");
               System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
               break;
           }
           
           if(numAdivinar>numOculto){
               System.out.println("###_El número oculto es menor_###");
           }
           else{
               System.out.println("###_El número oculto es mayor_###");
           }
        }
    }
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        boolean principal=true;
        int opcion=0;
        int numInt=5, numMax=10, menu=0;
        
        while(principal)
        {
            System.out.println("\n   <<<< ADIVINA UN NÚMERO>>>");
            System.out.println("   -------------------------");
            System.out.println("\nElige una opcion y presiona enter");
            System.out.println("1-Configuración // 2-Jugar // 3-Salir");
            Scanner numero = new Scanner(System.in);
            opcion = numero.nextInt();
            switch (opcion)
            {
                case 1:
                    System.out.print("Número de Intentos:");
                    numInt = numero.nextInt();
                    System.out.print("Número Maximo para averiguar: ");
                    numMax = numero.nextInt();
                    break;
                
                case 2:
                    game(numInt,numMax);
                    break;
                
                case 3:
                    principal=false;
                  
            } 
            
 
        }
    }
    
    
    
}
