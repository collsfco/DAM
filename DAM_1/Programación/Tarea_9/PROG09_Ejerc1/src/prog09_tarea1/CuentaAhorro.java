package prog09_tarea1;

/**
 *
 * @author FColls
 */
//Clase CuentaAhorro herada de la Clase CuentaBancaria.
public class CuentaAhorro extends CuentaBancaria {
    //Declaramos el atributo especifico de las cuentas tipo ahorro.
    
    /*Tipo de interés de remuneración*/
    private int tipoInteres;
   
     //Constructor de la clase CuentaAhorro.
    public CuentaAhorro(Persona titular,float saldo, String iban, int tipoInteres){
        
        super(titular,saldo,iban); //Llamamos al constructor de la clase padre.
        this.tipoInteres=tipoInteres; 
    }
    //Métodos Getter y Setter, específicos de la clase CuentaAhorro.
    public void setTipoInteres(int tipoInteres){
        
        this.tipoInteres=tipoInteres;
    }
    
    public float getTipoInteres(){
        return tipoInteres;
    }
    //Devuelve la información de la cuenta como una cadena de caracteres.
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString()+", \n   Cuenta:[Ahorro], Tipo de Interes: "+ tipoInteres;
    }
}
