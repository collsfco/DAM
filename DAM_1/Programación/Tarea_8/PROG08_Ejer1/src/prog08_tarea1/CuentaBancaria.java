package prog08_tarea1;

/**
 *
 * @author FColls
 */
//La clase CuentaBancaria implementa la interfaz Imprimible.
public abstract class CuentaBancaria implements Imprimible{
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
