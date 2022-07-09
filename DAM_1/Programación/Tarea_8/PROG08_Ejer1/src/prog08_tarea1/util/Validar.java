package prog08_tarea1.util;

/**
 *
 * @author FColls
 */
public class Validar {
    
    public static boolean validaIBAN (String iban){
        return iban.matches("^ES[0-9]{22}$");

    }
    
   public static boolean validaDNI (String dni){
        return dni.matches("^[0-9]{8}[A-Z]$");  //Versión simple para validar un DNI.
        
    }
    
}
