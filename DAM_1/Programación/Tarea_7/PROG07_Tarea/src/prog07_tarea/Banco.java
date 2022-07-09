package prog07_tarea;

/**
 *
 * @author FColls
 */
/*Clase Banco almacena las Cuentas*/
public class Banco {

    final static int maxCuentas = 100;
    CuentaBancaria cuentasBanco[]; //Array tipo CuentaBancaria.
    private int contadorArray; //Contador privado para utilizar en los métodos de la clase banco
    int contadorArrayC; //Contador público para utilizar en clase principal.
    int maxCuentasC = maxCuentas; //Máximo de cuentas para utilizar en clase principal.
    
    //Constructor de la clase Banco.
    public Banco() {
        cuentasBanco = new CuentaBancaria[maxCuentas];
        contadorArray = 0;
        contadorArrayC = 0;
    }
    /*
    * Recibe por parámetro un objeto CuentaBancaria y lo almacena en la estructura. 
    * Devuelve true o false indicando si la operación se realizó con éxito.
    */
    public boolean abrirCuenta(CuentaBancaria cuentaCreada) {

        if (contadorArray < maxCuentas) {
            if (informacionCuenta(cuentaCreada.getIban()) == null) {
                cuentasBanco[contadorArray] = cuentaCreada;
                contadorArray++;
                contadorArrayC++;
                return true;
            }
        }
        return false;
    }
    
    /*
    * No recibe parámetro y devuelve un array donde cada elemento es una cadena 
    * que representa la información de una cuenta.
    */
      
    public CuentaBancaria[] listadoCuenta(){
        CuentaBancaria[] cuentasBancoCopia;
        cuentasBancoCopia=cuentasBanco;
        return cuentasBancoCopia;
    }
    
    /*
    *Recibe un iban por parámetro y devuelve una cadena con la información de la cuenta 
    *o null si la cuenta no existe.
    */
    public String informacionCuenta(String iban) {
        for (int i = 0; i < contadorArray; i++) {
            if (cuentasBanco[i].getIban().equals(iban)) {
                return cuentasBanco[i].devolverInfoString();
            }
        }
        return null;
    }

    /*
    * Recibe un iban por parámetro y una cantidad e ingresa la cantidad en la cuenta. 
    * Devuelve true o false indicando si la operación se realizó con éxito.
    */
    public boolean ingresoCuenta(String iban, float saldo) {
        for (int i = 0; i < contadorArray; i++) {
            if (cuentasBanco[i].getIban().equals(iban)) {
                float saldoActual = cuentasBanco[i].getSaldo();
                cuentasBanco[i].setSaldo(saldoActual + saldo);
                return true;
            }
        }
        return false;
    }
    
    /*
    * Recibe un iban por parámetro y una cantidad y trata de retirar la cantidad de la cuenta. 
    * Devuelve true o false indicando si la operación se realizó con éxito.
    */
    public boolean retiradaCuenta(String iban, float saldo) {
        for (int i = 0; i < contadorArray; i++) {
            if (cuentasBanco[i].getIban().equals(iban)) {
                float saldoActual = cuentasBanco[i].getSaldo();
                if (saldoActual >= saldo) {
                    cuentasBanco[i].setSaldo(saldoActual - saldo);
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
    * Recibe un iban por parámetro y devuelve el saldo de la cuenta si existe. 
    * En caso contrario devuelve -1.
    */
    public float obtenerSaldo(String iban) {
        for (int i = 0; i < contadorArray; i++) {
            if (this.cuentasBanco[i].getIban().equals(iban)) {
                float saldoActual = this.cuentasBanco[i].getSaldo();
                return saldoActual;
            }
        }
        return -1;
    }
}
