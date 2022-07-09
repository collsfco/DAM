package prog08_tarea1;

/**
 *
 * @author FColls
 */
//Clase CuentaCorrientePersonal herada de la Clase CuentaCorriente.
public class CuentaCorrientePersonal extends CuentaCorriente {
    
    private int comisionMantenimiento;
    
    public CuentaCorrientePersonal (Persona titular,float saldo, String iban,String entidades, int comisionM){
        
        super(titular,saldo,iban,entidades);//Llamamos al constructor de la clase padre.
        comisionMantenimiento=comisionM;
    
    }
    //Métodos Getter y Setter, específicos de la clase CuentaCorrientePersonal.
    public void setComisionMante(int comisionM){
        comisionMantenimiento=comisionM;
    }
    
    public float getComisionMante(){
        return comisionMantenimiento;
    }
    //Devuelve la información de la cuenta como una cadena de caracteres.
    @Override
    public String devolverInfoString(){
        return super.devolverInfoString()+", \n   Cuenta:[Corriente Personal], Comision Mantenimiento "+ comisionMantenimiento;
    }
}
