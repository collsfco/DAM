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
 * En este caso sólo se implemento en la clase CuentaBancaria, ya que las clases
 * que heredan de esta clase heredan la interfaz. 
 */

//La clase CuentaBancaria implementa la interfaz Imprimible.
public abstract class CuentaBancaria implements Imprimible, Serializable {
    /*Todas los tipos de cuentas Bancarias tienen en común los datos
    Titular, Saldo e IBAN. Dependiendo del tipo de cuenta se agregan otros 
    atributos,por ese motivo en la clase Padre agregamos estos atributos comunes*/
    
    private Persona titular;
    private float saldo;
    private String iban;
    
    //Constructor de la clase CuentaBancaria
    public CuentaBancaria(Persona titular,float saldo, String iban){
        this.titular=titular;
        this.saldo=saldo;
        this.iban=iban;
    }
    
    //Métodos Getter y Setter.
    public void setTitular(Persona titular){
        this.titular=titular;
    }
    /*Como Titular es un atributo que es un objeto se debe evitar su devolución
    por eso realizamos una copia para preservar la ocultación */
    public Persona getTitular(){
        Persona copTitular;
        copTitular=this.titular;
        return copTitular;
    }
    
    public void setSaldo(float saldo){
        this.saldo=saldo;
    }
    public float getSaldo(){
        return saldo;
    }
    
    public void setIban(String iban){
        this.iban=iban;
    }
    public String getIban (){
        return iban;
    }
    //Devuelve la información de la cuenta como una cadena de caracteres.
    @Override
    public String devolverInfoString() {
        return "Titular: "+getTitular().devolverInfoString()+", Saldo: "+saldo+", IBAN= "+iban;
    }
    
    
    
}
