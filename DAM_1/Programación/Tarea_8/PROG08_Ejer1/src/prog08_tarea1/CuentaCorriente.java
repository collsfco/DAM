package prog08_tarea1;

/**
 *
 * @author FColls
 */
//Clase CuentaCorriente herada de la Clase CuentaBancaria.
public abstract class CuentaCorriente extends CuentaBancaria{

    /*Lista de entidades autorizadas para cobrar recibos de la cuenta.*/
    private String entidadesRecibos;
    
    public CuentaCorriente(Persona titular,float saldo, String iban,String recibos){
        
        super(titular,saldo,iban); //Llamamos al constructor de la clase padre.
        entidadesRecibos=recibos;
    }
    //Métodos Getter y Setter, específicos de la clase CuentaCorriente.
    public String getEntidadesRecibos() {
        return entidadesRecibos;
    }

    public void setEntidadesRecibos(String recibos) {
        entidadesRecibos = recibos;
    }
    //Devuelve la información de la cuenta como una cadena de caracteres.
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString() +", Entidades autorizadas= "+ entidadesRecibos;
    }
    
}
