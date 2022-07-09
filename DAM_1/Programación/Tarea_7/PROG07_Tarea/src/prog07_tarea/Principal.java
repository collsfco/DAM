package prog07_tarea;

import java.util.Scanner;
import prog07_tarea.util.Validar;

/**
 *
 * @author FColls
 */
public class Principal {

    static Scanner read = new Scanner(System.in);
    
    //Clase para dibujar el menú del programa
    private static int menu() {
        System.out.println("\n###############################");
        System.out.println("<<<< GESTIÓN DEL BANCO >>>>");
        System.out.println("###############################");

        System.out.println("[1] Abrir una nueva cuenta.");
        System.out.println("[2] Ver un listado de las cuentas disponibles.");
        System.out.println("[3] Obtener datos de una cuenta / Realizar un ingreso.");
        System.out.println("[4] Retirar efectivo de una cuenta.");
        System.out.println("[5] Consultar el saldo actual de una cuenta.");
        System.out.println("[6] Salir de la aplicación.");
        System.out.println("###############################");
        System.out.println(">> Elige una opción...");
        
        int opcion = read.nextInt();
        read.nextLine();
        return opcion;
    }
    
    //Clase para obtener los datos del titular y la cuenta.
    public static CuentaBancaria creaCuenta() {
        
        //Atributos para almacenar los datos del titular y cuenta.
        String nombre, apellidos, dni, iban;
        float saldo;
        String entidadesAutorizadas;
        int comisionMant, interesDescubierto, maxDescubierto;
        int tipoInteres;
        int opcCuenta;
        CuentaBancaria cuentaCreada = null;

        System.out.println("Ingrese el nombre del titular");
        nombre = read.nextLine().toUpperCase();
        System.out.println("Ingrese los apellidos del titular");
        apellidos = read.nextLine().toUpperCase();
        System.out.println("Ingrese el DNI del titular de la cuenta");
        dni = read.nextLine();
        do { //Usamos la clase Validar para comprobar que el DNI cumple con el formato correcto.
            if (!Validar.validaDNI(dni)) {
                System.out.println("El formato del DNI no es correcto. Debe ser NNNNNNNNL. Introdúcelo otra vez.");
                dni = read.nextLine();
            }
        } while (!Validar.validaDNI(dni));
        System.out.println("Ingrese el Numero de IBAN");
        iban = read.nextLine();
        
        do { //Usamos la clase Validar para comprobar que el IBAN cumple con el formato correcto.
            if(!Validar.validaIBAN(iban)){
                System.out.println("El formato del IBAN no es correcto. Debe ser ESNNNNNNNNNNNNNNNNNNNNNN. Introdúcelo otra vez.");
                iban = read.nextLine();
            }   
        } while (!Validar.validaIBAN(iban));
        //Creamos un objeto tipo Persona llamado titular para establecer los datos del titular.
        Persona titular=new Persona(nombre, apellidos, dni); 
        
        System.out.println("Ingrese el Saldo inicial de la cuenta");
        saldo = read.nextFloat();
        System.out.println("Seleccione el Tipo de cuenta");
        System.out.println("Ahorro[1] - Corriente[2]");
        opcCuenta = read.nextInt();
        read.nextLine();
        switch (opcCuenta) {
            case 1:
                System.out.println("Ingrese el tipo de interés");
                tipoInteres = read.nextInt();
                read.nextLine();
                // Polimorfismo.
                CuentaBancaria cuentaA = new CuentaAhorro(titular, saldo, iban, tipoInteres);
                cuentaCreada = cuentaA;
                break;
            case 2:
                System.out.println(">>>Desea autorizadar cobros de recibos en la cuenta");
                System.out.println("Si[1] - NO[2]");
                String opcRecibo = read.nextLine();
                if (opcRecibo.equalsIgnoreCase("1")) {
                    System.out.println(">>>Ingrese el nombre de la entidad");
                    entidadesAutorizadas = read.nextLine();
                } else {
                    entidadesAutorizadas = "No.";
                }
                System.out.println(">>Tipo de cuenta corriente<<<");
                System.out.println("Personal[1] - Empresa[2]");
                opcCuenta = read.nextInt();
                read.nextLine();

                switch (opcCuenta) {
                    case 1:
                        System.out.println("Cuenta Corriente Personal");
                        System.out.println("Comisión de mantenimiento");
                        comisionMant = read.nextInt();
                        read.nextLine();
                        // Polimorfismo.
                        CuentaBancaria cuentaCP = new CuentaCorrientePersonal(titular, saldo, iban, entidadesAutorizadas, comisionMant);
                        cuentaCreada = cuentaCP;
                        break;
                    case 2:
                        System.out.println("Cuenta Corriente Empresa");
                        System.out.println("Tipo de interés por descubierto");
                        interesDescubierto = read.nextInt();
                        read.nextLine();
                        System.out.println("Máximo descubierto permitido");
                        maxDescubierto = read.nextInt();
                        read.nextLine();
                        // Polimorfismo.
                        CuentaBancaria cuentaCE = new CuentaCorrienteEmpresa(titular, saldo, iban, entidadesAutorizadas, interesDescubierto, maxDescubierto);
                        cuentaCreada = cuentaCE;
                        break;
                }
                break;
        }

        return cuentaCreada;// Devolvemos los datos de la cuenta para almacenarlos en el banco.

    }

    public static void main(String[] args) {

        int opcion;
        Banco objBanco = new Banco(); //Creamos un objeto tipo Banco.
        String infoIBAN;
        float saldoC;

        do {
            opcion = menu(); //Llamamos a la clase menu();
            switch (opcion) {
                case 1: //Abrir Cuenta.
                    System.out.println("1.Abrir una nueva cuenta.");
                    if (objBanco.abrirCuenta(creaCuenta())) {
                        System.out.println("[Cuenta creada correctamente]");
                    } else {
                        //Si no se pudo crear la cuenta comprobamos el motivo por el cual no se pudo crear.
                        if (objBanco.contadorArrayC >= objBanco.maxCuentasC) {
                            System.out.println("[ERROR!! Se ha alcanzado el limite de cuentas en el banco]");
                        } else {
                            System.out.println("[Ya existe ese IBAN]");
                        }
                    }

                    break;
                    /* Si no hay una cuenta alamacenada las opciones desde 2-5 nos indican que debemos crear una cuenta
                     * para poder realizar una consulta.
                    */
                case 2: //Listado Cuentas.
                    if (objBanco.contadorArrayC > 0) {
                        System.out.println("2.Ver un listado de las cuentas disponibles.\n");
                        CuentaBancaria[] arrayCuentas = objBanco.listadoCuenta();//Creamos un nuevo array para asignar el array del Banco.
                        for (int i = 0; i < objBanco.contadorArrayC; i++) {
                            
                            //Ligadura dinámica al momento de llamar el método devolverInfoString.
                            System.out.println(arrayCuentas[i].devolverInfoString());
                            System.out.println("--------------------------------------------------------");
                        }
                    } else {
                        System.out.println("No existen cuentas. Debe crear una cuenta en la Opción [1]");
                    }
                    break;

                case 3://Datos cuenta-ingreso.
                    if (objBanco.contadorArrayC > 0) {
                        System.out.println("3.Obtener los datos de una cuenta concreta.");
                        System.out.println(">>>Ingrese IBAN");
                        infoIBAN = read.nextLine().toUpperCase();
                        if (objBanco.informacionCuenta(infoIBAN) != null) { //Si existe la cuenta, mostramos la información.
                            System.out.println(objBanco.informacionCuenta(infoIBAN));
                            //Luego preguntamos si queremos realizar un ingreso en la cuenta indicada.
                            System.out.println(">Desea realizar un ingreso en esta cuenta");
                            System.out.println("Si[1] - NO[2]");
                            String opcIngreso = read.next();
                            if (opcIngreso.equalsIgnoreCase("1")) {
                                System.out.println(">>>Indique la cantidad a ingresar.");
                                float nuevoSaldo = read.nextFloat();
                                read.nextLine();
                                if (objBanco.ingresoCuenta(infoIBAN, nuevoSaldo)) {
                                    System.out.println("[Ingreso Realizado Correctamente]");
                                    System.out.println("[Saldo Actual: " + objBanco.obtenerSaldo(infoIBAN) + "€]");
                                }
                            }

                        } else {
                            System.out.println("[IBAN no encontrado]");
                        }

                    } else {
                        System.out.println("No existen cuentas. Debe crear una cuenta en la Opción [1]");
                    }

                    break;

                case 4://Retirar efectivo.
                    if (objBanco.contadorArrayC > 0) {
                        System.out.println("4.Retirar efectivo de una cuenta.");
                        System.out.println(">>>Ingrese IBAN");
                        infoIBAN = read.nextLine().toUpperCase();
                        System.out.println(">>>Cantidad a retirar");
                        float retirarSaldo = read.nextFloat();
                        if (objBanco.retiradaCuenta(infoIBAN, retirarSaldo)) {
                            System.out.println("[Retiro realizado correctamente]");
                            System.out.println("[Saldo Actual: " + objBanco.obtenerSaldo(infoIBAN) + "€]");
                        } else {
                            System.out.println("No, se ha realizado el retiro.");
                            //Si la cuenta existe y no se pudo realizar el retiro, es porque no hay suficiente saldo.
                            if (objBanco.informacionCuenta(infoIBAN) != null) {
                                System.out.println("[Saldo insuficiente]");
                                System.out.println("[Saldo actual: " + objBanco.obtenerSaldo(infoIBAN) + "€]");
                            } else {
                                System.out.println("[No existe el IBAN indicado]");
                            }

                        }

                    } else {
                        System.out.println("No existen cuentas. Debe crear una cuenta en la Opción [1]");
                    }
                    break;

                case 5://Consultar Saldo.
                    if (objBanco.contadorArrayC > 0) {
                        System.out.println("5.Consultar el saldo actual de una cuenta.");
                        System.out.println(">>>Ingrese IBAN");
                        infoIBAN = read.nextLine().toUpperCase();
                        saldoC = objBanco.obtenerSaldo(infoIBAN);
                        if (saldoC == -1) {
                            System.out.println("[Cuenta no existe]");
                        } else {
                            System.out.println("[El saldo actual es: "+objBanco.obtenerSaldo(infoIBAN) + "€]");
                        }
                    } else {
                        System.out.println("No existen cuentas. Debe crear una cuenta en la Opción [1]");
                    }
                    break;

                case 6:
                    System.out.println("6.Salir de la aplicación");
                    break;
            }
        } while (opcion != 6);
    }

}
