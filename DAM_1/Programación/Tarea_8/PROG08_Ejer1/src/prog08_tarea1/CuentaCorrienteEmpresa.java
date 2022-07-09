package prog08_tarea1;

/**
 *
 * @author FColls
 */
//Clase CuentaCorrienteEmpresa herada de la Clase CuentaCorriente.
public class CuentaCorrienteEmpresa extends CuentaCorriente {

   
    /*Tipo de interés por descubierto (si es una cuenta corriente de empresa).
    Máximo descubierto permitido (si se trata de una cuenta corriente de empresa)*/
    private int interesDescubierto;
    private int maxDescubierto;
    
    public CuentaCorrienteEmpresa(Persona titular, float saldo, String iban,String entidades, int interesD,int maxDescubierto) {
        super(titular, saldo, iban, entidades);//Llamamos al constructor de la clase padre.
        interesDescubierto=interesD;
        this.maxDescubierto=maxDescubierto;
    }
    
    //Métodos Getter y Setter, específicos de la clase CuentaCorrienteEmpresa.
    public int getInteresDescubierto() {
        return interesDescubierto;
    }

    public void setInteresDescubierto(int interesDescubierto) {
        this.interesDescubierto = interesDescubierto;
    }

    public int getMaxDescubierto() {
        return maxDescubierto;
    }

    public void setMaxDescubierto(int maxDescubierto) {
        this.maxDescubierto = maxDescubierto;
    }
    
    //Devuelve la información de la cuenta como una cadena de caracteres.
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString()+",\n   Cuenta:[Corriente Empresa}, Interés por descubierto: "+ 
                getInteresDescubierto()+"%, Máximo descubierto permitido: " + getMaxDescubierto();
    }
 
    
}
