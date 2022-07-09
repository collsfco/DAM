package prog09_tarea1;

import java.io.*;
import java.util.LinkedList;


/**
 *
 * @author FColls
 */
/*Clase Banco almacena las Cuentas*/
public class Banco {

    /**
     * La estructura seleccionada para almacenar la información de las cuentas
     * bancarias es LinkedList ya que es recomendada si se realizan muchas
     * operaciones de borrado e inserción en la lista, y ya que es un banco me
     * imagino que realiza muchas opereciones de registro de clientes. Además su
     * funcionamiento es rápido al estar basado en nodos.
     */
    //LinkedList tipo CuentaBancaria.
    LinkedList<CuentaBancaria> cuentasBanco;

    //Constructor de la clase Banco.
    public Banco() {
        cuentasBanco = new LinkedList<CuentaBancaria>();
    }

    /**
     * abrirCuenta: Metodo para almacenar una cuenta en el banco.
     *
     * @param cuentaCreada Recibe un objeto CuentaBancaria, que contiene los
     * datos de la cuenta.
     * @return Devuelve true o false indicando si la operación se realizó con
     * éxito.
     */
    public boolean abrirCuenta(CuentaBancaria cuentaCreada) {

        if (informacionCuenta(cuentaCreada.getIban()) == null) {
            cuentasBanco.add(cuentaCreada);
            return true;
        }
        return false;
    }

    /**
     * listadoCuenta.
     *
     * @return Devuelve la lista donde cada elemento representa la informacion
     * de una cuenta.
     */
    public LinkedList<CuentaBancaria> listadoCuenta() {
        LinkedList<CuentaBancaria> cuentasBancoCopia;
        cuentasBancoCopia = (LinkedList<CuentaBancaria>) cuentasBanco.clone(); //Clonamos la lista en otra para devolver la copia.
        return cuentasBancoCopia;
    }

    /**
     * informacionCuenta.
     *
     * @param iban Iban de la cuenta de la cual queremos obtener información.
     * @return devuelve una cadena con la información de la cuenta o null si la
     * cuenta no existe.
     */
    public String informacionCuenta(String iban) {
        for (CuentaBancaria info : cuentasBanco) {
            if (info.getIban().equals(iban)) {
                return info.devolverInfoString();
            }
        }
        return null;
    }

    /**
     * ingresoCuenta.
     *
     * @param iban Iban de la cuenta en la cual queremos realizar el ingreso.
     * @param saldo Saldo que queremos sumar a la cuenta.
     * @return Devuelve true o false indicando si la operación se realizó con
     * éxito.
     */
    public boolean ingresoCuenta(String iban, float saldo) {
        for (CuentaBancaria ingreso : cuentasBanco) {
            if (ingreso.getIban().equals(iban)) {
                float saldoActual = ingreso.getSaldo();
                ingreso.setSaldo(saldoActual + saldo);
                return true;
            }
        }
        return false;
    }

    /**
     * retiradaCuenta.
     *
     * @param iban Iban de la cuenta en la cual queremos realizar el retiro.
     * @param saldo Saldo que queremos sumar a la cuenta.
     * @return Devuelve true o false indicando si la operación se realizó con
     * éxito.
     */
    public boolean retiradaCuenta(String iban, float saldo) {
        for (CuentaBancaria retiro : cuentasBanco) {
            if (retiro.getIban().equals(iban)) {
                float saldoActual = retiro.getSaldo();
                if (saldoActual >= saldo) {
                    retiro.setSaldo(saldoActual - saldo);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * obtenerSaldo.
     *
     * @param iban Iban de la cuenta de la cual queremos obtener el saldo.
     * @return Devuelve el saldo de la cuenta si existe, en caso contrario
     * devuelve -1.
     */
    public float obtenerSaldo(String iban) {
        for (CuentaBancaria saldo : cuentasBanco) {
            if (saldo.getIban().equals(iban)) {
                float saldoActual = saldo.getSaldo();
                return saldoActual;
            }
        }
        return -1;
    }

    /**
     * eliminarCuenta
     *
     * @param iban Iban de la cuenta que queremos eliminar
     * @return Devuelve true si la cuenta fue eliminada, false en caso
     * contrario.
     */
    public boolean eliminarCuenta(String iban) {
        for (CuentaBancaria eliminar : cuentasBanco) {
            if (eliminar.getIban().equals(iban)) {

                if (eliminar.getSaldo() == 0) {
                    cuentasBanco.remove(eliminar);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     ***** Modificaciones Tarea 9.*****
     * Creamos 3 métodos para poder agregar las nuevas
     * funcionalidades exigidas en esta actividad. También se implementó la
     * interfaz Serializable en las clases Persona y CuentaBancaria, para poder
     * serializar los datos en el archivo .dat.
     */
    
    /**
     * volcarDatos. Escribe en un fichero .dat el contenido del LinkedList donde
     * se encuentran almacenadas las cuentas.
     */
    public void volcarDatos() {

        ObjectOutputStream escribirFichero = null;

        try {
            //Object(archivo tipo file, contiene la ruta del archivo) 
            escribirFichero = new ObjectOutputStream(new FileOutputStream("./src/prog09_tarea1/ficheros/datoscuentasbancarias.dat"));
            escribirFichero.writeObject(cuentasBanco);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        //Es recomendable cerrar siempre el fichero dentro de un finally.
        } finally {
            try {
                if (null != escribirFichero) {
                    escribirFichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    /**
     * recuperarDatos: Lee un fichero en la ruta indicada, luego se realiza un casting ya que devuelve
     * un tipo Object y es necesario convertirlo a Linkedlist. Asignamos el valor leido a la LinkedList
     * existente.
     */

    public void recuperarDatos() {
        ObjectInputStream recuperarFichero = null;
        try {
            recuperarFichero = new ObjectInputStream(new FileInputStream("./src/prog09_tarea1/ficheros/datoscuentasbancarias.dat"));
            this.cuentasBanco = (LinkedList) recuperarFichero.readObject();//Casting necesario porque devuelve Object
  
        } catch (ClassNotFoundException e) {
            System.out.println("Error el tipo de objeto no es compatible");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != recuperarFichero) {
                    recuperarFichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    /**
     * generarTxt: genera un archivo txt con los datos de las cuentas existentes en el banco.
     * @return devuelve un true si se generó el archivo correctamente, y un false en case de 
     * que no lo pueda genera.
     */

    public boolean generarTxt() {

        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean txtOk=false;
        try {
            fichero = new FileWriter("./src/prog09_tarea1/ficheros/ListadoClientesCCC.txt");
            pw = new PrintWriter(fichero);
            pw.println("\n\t\t<<<< Listado de clientes >>>>");
            pw.println("--------------------------------------------------------\n");
            for (CuentaBancaria info : cuentasBanco) {
                pw.println("IBAN: " + info.getIban() + ", Titular: " + info.getTitular().getNombre() + " " + info.getTitular().getApellidos());
                pw.println("--------------------------------------------------------");
            }
            pw.println(">>>> Cuentas existentes en el Banco: " + "[" + cuentasBanco.size() + "]");
            txtOk= true;
        } catch (Exception e) {
            e.printStackTrace();
            txtOk= false;
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return txtOk;
    }

}
