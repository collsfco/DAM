/*
Diseña un programa Java denominado PROG02_Ejerc7 para resolver una ecuación
de primer grado con una incógnita (x), suponiendo que los coeficientes de la ecuación
son C1 y C2 se inicializan en el método main:
C1x + C2 = 0
Se debe mostrar el resultado con 4 decimales.
 */
package prog02_ejerc7;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc7 {
    
    public static void main(String[] args) {
        float c1= 5;
        float c2= 7;
        float incognitaX= (-c2/c1);
        
        System.out.printf("Resultado de X: " + "%.4f",incognitaX);
       
        //método printf
        //la sintaxis de %.4f le dice a Java que devuelva su variable con 4 decimales
    }
    
}
