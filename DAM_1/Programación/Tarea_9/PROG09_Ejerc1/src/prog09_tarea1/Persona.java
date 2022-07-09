package prog09_tarea1;

import java.io.Serializable;

/**
 *
 * @author FColls
 */


/**
 ***** Modificaciones Tarea 9.*****
 * Implementamos la interfaz Serializable en las clases Persona y CuentaBancaria, 
 * para poder serializar los datos en el archivo .dat.
 * La clase Serializable no contiene métodos que sobreescribir, por lo tanto sólo
 * es necesario declararla para poder usarla.
 */

//La clase persona implementa la interfaz Imprimible.
public class Persona implements Imprimible, Serializable{
    /*Clase persona contiene los datos personales del cliente, tales como
    Nombre, Apellidos y DNI */
    
    private String nombre,apellidos,dni;
    
    //Constructor de la clase Persona
    public Persona(String nombre,String apellidos,String dni){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.dni=dni;
    }
    
    //Setter y Getter.
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    
    public String getApellidos(){
        return apellidos;
    }
    
    public void setDni(String dni){
        this.dni=dni;
    }
    
    public String getDni(){
        return dni;
    }
    
    //Devuelve la información del Titular como una cadena de caracteres.
    @Override //Nos indica un error si el método descrito no esta siendo sobrescrito.
    public String devolverInfoString(){
        return nombre +" "+ apellidos+", "+"DNI: "+dni;
    }
}
