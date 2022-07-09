/*
 Diseña un programa Java denominado PROG02_Ejerc6 que cree un tipo
enumerado para las siguientes razas de perro: Mastín, Terrier, Bulldog, Pekines,
Caniche y Galgo. El programa debe realizar las siguientes operaciones:

Crea una variable denominada var1 del tipo enumerador. Asígnale un valor.
Crea una variable denominada var2 del tipo enumerador. Asígnale un valor.
Muestra por pantalla el valor obtenido de comparar ambas variables.

Investiga sobre la posibilidad averiguar la posición que ocupa un determinado valor en el
enumerado así como mostrar la cantidad de valores que contiene. Si lo consigues,
muestra la posición de las dos variables en el tipo enumerado.
 */
package prog02_ejerc6;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc6 {

    public enum perro {Mastín, Terrier, Bulldog, Pekines,Caniche, Galgo}
    
    public static void main(String[] args) {
        
        perro var1= perro.Pekines;
        perro var2= perro.Bulldog;
     
        System.out.println("Posición Pekines en el enum:"+ var1.ordinal());
        System.out.println("Posición Bulldog en el enum:"+ var2.ordinal());
        System.out.println("El enum perro contiene "+ perro.values().length + " elementos.");
     
    }
    
}
