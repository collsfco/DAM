package prog09_tarea1;

/**
 *
 * @author FColls
 */
/*
La interfaz Imprimible tan solo declarará el devolverInfoString, 
que devolverá la información de una cuenta como una cadena de caracteres.
*/
public interface Imprimible {
    
    //Método que debe ser sobrescrito en las clases correspondientes.
    
    public abstract String devolverInfoString();
    
}
