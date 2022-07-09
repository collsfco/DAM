package prog08_tarea2;

import java.util.Scanner;

/**
 *
 * @author FColls
 */
public class Principal {

    static Scanner read = new Scanner(System.in);

    //Metodo para dibujar el menú del programa
    public static int menu() {
        System.out.println("\n###############################");
        System.out.println("<<<< Gestión de Concesionario >>>>");
        System.out.println("###############################");

        System.out.println("[1] Nuevo Vehículo.           #");
        System.out.println("[2] Listar Vehículos.         #");
        System.out.println("[3] Buscar Vehículo.          #");
        System.out.println("[4] Modificar Kms Vehículo    #");
        System.out.println("[5] Eliminar Vehículo         #");
        System.out.println("[6] Salir                     #");
        System.out.println("###############################");
        System.out.println(">> Elige una opción...");

        int opcion = read.nextInt();
        read.nextLine();
        return opcion;

    }

    public static void main(String[] args) {

        /*Atributos necesarios para crear un nuevo vehículo*/
        String marca, matricula = null, descripcion, propietario = null;
        int kmsCoche = 0;
        float precio;
        boolean dniOk = true, kmsOk = true, matriOk = true, nombreOk = true; //Booleanos creados para romper ciclo While cuando los datos sean correctos.
        String nifLeido = null;
        boolean exitP = true;
        Concesionario objConce = new Concesionario();
        int opcion;

        do {//Menú de opciones.
            opcion = menu();
            switch (opcion) {

                case 1: //Nuevo Vehiculo.
                    System.out.println("\n#######################");
                    System.out.println("<<<< Nuevo Vehículo>>>>");
                    System.out.println("#######################");

                    System.out.println(">>> Marca del vehículo:");
                    marca = read.nextLine();

                    System.out.println(">>> Matrícula del vehículo:");
                    System.out.println("Debe incluir 4 números seguidos de 3 letras mayusculas");

                    do {
                        matricula = read.nextLine();
                        if (matricula.matches("[0-9]{4}[A-Z /z]{3}")) { //Mediante expresiones regulares comprobamos el formato indicado
                            matriOk = false;
                        } else {
                            System.out.println("Matricula invalida debe incluir 4 numeros seguidos de 3 letras mayusculas");
                            System.out.println("Vuelve a introducir matricula");
                            matriOk = true;
                        }
                    } while (matriOk);

                    System.out.println(">>> Kms del vehículo:");
                    do {
                        kmsCoche = read.nextInt();
                        read.nextLine(); //Después de leer un entero lee una nueva línea para evitar errores de salto de línea.
                        if (kmsCoche > 0) {
                            kmsOk = false;
                        } else {
                            System.out.println("--Ingrese una cantidad de Kms mayor a 0");
                            kmsOk = true;
                        }
                    } while (kmsOk);

                    System.out.println(">>> Precio del vehículo:");
                    precio = read.nextFloat();
                    read.nextLine();

                    System.out.println(">>> Descripcion del vehículo:");
                    descripcion = read.nextLine();

                    System.out.println(">>> Nombre del propietario del vehículo:");
                    do {
                        propietario = read.nextLine();

                        // El contador de espacios
                        int cantidadDeEspacios = 0;
                        // Recorremos la cadena:
                        for (int i = 0; i < propietario.length(); i++) {
                            // Si el carácter en [i] es un espacio (' ') aumentamos el contador 
                            if (propietario.charAt(i) == ' ') {
                                cantidadDeEspacios++;
                            }
                        }

                        if (propietario.length() <= 40) { //La longitud del nombre debe ser menor de 40 caracteres.

                            if (cantidadDeEspacios == 2) {
                                nombreOk = false;
                            } else {
                                System.out.println("Ingrese Un nombre y 2 Apellidos");
                            }
                        } else {
                            System.out.println("La longitud de los caracteres no debe ser superior a 40");

                        }
                    } while (nombreOk);

                    System.out.println(">>> DNI del propietario del Vehículo (ej:000000000X):");
                    System.out.println("--La letra debe ser en Mayúsculas");
                    do {
                        nifLeido = read.nextLine();
                        if (nifLeido.matches("[XYxy]?[0-9]{1,9}[A-Z /z]")) { //Mediante expresiones regulares comprobamos el formato indicado
                            dniOk = false;
                        } else {
                            System.out.println("DNI invalido debe incluir 8 numeros seguidos de 1 letra mayuscula");
                            System.out.println("Vuelve a introducir el DNI");
                            dniOk = true;
                        }
                    } while (dniOk);

                    //Llamamos al metodo insertarVehiculo y le pasamos los datos recopilados.
                    int insertar2 = objConce.insertarVehiculo(marca, matricula, descripcion, propietario, kmsCoche, precio, nifLeido);

                    switch (insertar2) { //Según el numero devuelto ingresa a cada caso.
                        case 0:
                            System.out.println(">>>Vehículo Creado");
                            break;

                        case -2:
                            System.out.println(">>>Ya existe esa Matricula");
                            break;
                    }
                    break;

                case 2: //Listar Vehículos.
                    if (!objConce.almacenCoches.isEmpty()) { //isEmpty devuelve true si el TreeSet esta vacio. 
                        objConce.listarVehiculo();

                    } else {
                        System.out.println("Es necesario registrar un vehículo en la opción [1].");
                    }
                    break;

                case 3: //Buscar Vehículo. 
                    if (!objConce.almacenCoches.isEmpty()) {
                        System.out.println(">>> Matrícula del vehículo:");
                        String buscaMatricula = objConce.buscaVehiculo(read.nextLine());

                        if (buscaMatricula == null) {
                            System.out.println("No existe vehículo con la matrícula introducida");
                        } else {
                            System.out.println(buscaMatricula);
                        }

                    } else {
                        System.out.println("Es necesario registrar un vehículo en la opción [1].");
                    }

                    break;
                    
                case 4: //Modificar Kms Vehículo 
                    if (!objConce.almacenCoches.isEmpty()) {
                        System.out.println(">>> Ingrese Matrícula del vehículo:");
                        matricula = read.nextLine();
                        System.out.println(">>> Kilometros del vehículo:");
                        int nuevoKm = read.nextInt();
                        read.nextLine();
                        boolean kmsModificados = objConce.actualizaKms(nuevoKm, matricula);

                        if (kmsModificados) {
                            System.out.println("Kilometros actualizados");
                        } else {
                            System.out.println("No existe vehículo con la matrícula introducida");
                        }

                    } else {
                        System.out.println("Es necesario registrar un vehículo en la opción [1].");
                    }

                    break;

                case 5://Eliminar vehículo.
                    if (!objConce.almacenCoches.isEmpty()) {
                        System.out.println(">>> Matrícula del vehículo:");
                        boolean buscaMatricula = objConce.eliminarVehiculo(read.nextLine()); 
                        //En la variable buscaMatricula almacenamos el resultado devuelto por 
                        //el metodo eliminarVehiculo.

                        if (buscaMatricula) { 
                            System.out.println("Vehículo eliminado correctamente");
                        } else {
                            System.out.println("No existe vehículo con la matrícula introducida");
                        }

                    } else {
                        System.out.println("Es necesario registrar un vehículo en la opción [1].");
                    }

                    break;

            }
        } while (opcion != 6);

    }

}
