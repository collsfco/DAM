/*
*Diseña un programa Java denominado PROG02_Ejerc5 que dado un número de
*segundos, muestre en pantalla cuántos minutos, horas y días contiene..
 */
package prog02_ejerc5;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int segundos = 1650000;
        
        int dias= segundos / 86400; //86400 son los segundos que contiene un Día.
        segundos %= 86400;
        int horas= segundos /3600;
        segundos %=3600;
        int minutos= segundos/60;
        segundos %=60;
       
        System.out.println("La cantidad de segundos indicada contiene:");
        System.out.println("minutos:"+minutos);
        System.out.println("horas:"+horas);
        System.out.println("días:"+dias);
        
    }
    
}
