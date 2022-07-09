package prog06_tarea;

import java.util.Scanner;

/**
 *
 * @author FColls
 */
public class Principal {

    public static void creaVehiculo(Concesionario nuevoVehiculo) {

        Scanner read = new Scanner(System.in); //Creamos el objeto read, que será el encargado de 
        //leer los datos introducidos por teclado.

        /*Atributos necesarios para crear un nuevo vehículo*/
        String marca, matricula = null, descripcion, propietario = null;
        int kmsCoche = 0;
        float precio;
        boolean dniOk = true, kmsOk = true, matriOk = true, nombreOk = true; //Booleanos creados para romper ciclo While cuando los datos sean correctos.
        String nifLeido = null;

        System.out.println(">>> Marca del vehículo:");
        marca = read.nextLine();
        
        

        System.out.println(">>> Matrícula del vehículo:");
        System.out.println("Debe incluir 4 números seguidos de 3 letras mayusculas");

        while (matriOk) {

            matricula = read.nextLine();
            if (matricula.matches("[0-9]{4}[A-Z /z]{3}")) {
                matriOk = false;
            } else {
                System.out.println("Matricula invalida debe incluir 4 numeros seguidos de 3 letras mayusculas");
                System.out.println("Vuelve a introducir matricula");
            }
        }

        System.out.println(">>> Kms del vehículo:");

        while (kmsOk) {
            kmsCoche = read.nextInt();
            read.nextLine(); //Después de leer un entero lee una nueva línea para evitar errores de salto de línea.

            if (kmsCoche > 0) {
                kmsOk = false;
            } else {
                System.out.println("--Ingrese una cantidad de Kms mayor a 0");
            }

        }
        System.out.println(">>> Precio del vehículo:");
        precio = read.nextFloat();
        read.nextLine();
        
        System.out.println(">>> Descripcion del vehículo:"); 
        descripcion=read.nextLine();

        System.out.println(">>> Nombre del propietario del vehículo:");
        while (nombreOk) {
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

            if (propietario.length() <= 40) {

                if (cantidadDeEspacios == 2) {
                    nombreOk = false;
                } else {
                    System.out.println("Ingrese Un nombre y 2 Apellidos");
                }
            } else {
                System.out.println("La longitud de los caracteres no debe ser superior a 40");
            }

        }

        System.out.println(">>> DNI del propietario del Vehículo (ej:000000000X):");
        System.out.println("--La letra debe ser en Mayúsculas");

        while (dniOk) {
            nifLeido = read.nextLine();
            if (nifLeido.matches("[XYxy]?[0-9]{1,9}[A-Z /z]")) {
                dniOk = false;
            } else {
                System.out.println("DNI invalido debe incluir 8 numeros seguidos de 1 letra mayuscula");
                System.out.println("Vuelve a introducir el DNI");
            }

        }
        

        nuevoVehiculo.setMarca(marca);
        nuevoVehiculo.setMatricula(matricula);
        nuevoVehiculo.setDescripcion(descripcion);
        nuevoVehiculo.setPropietario(propietario);
        nuevoVehiculo.setKms(kmsCoche);
        nuevoVehiculo.setPrecio(precio);
        nuevoVehiculo.setDniPropietario(nifLeido);
    }

    public static void main(String[] args) {
        boolean exitP = true;
        Concesionario objConce = new Concesionario();
        
           //Recorrer el string consecionario en principal
        //String[] arrayConce = objConce.listarVehiculo();
        int insertar = 0;

        while (exitP) {//Menú de opciones.
            System.out.println("\n###############################");
            System.out.println("<<<< Gestión de Concesionario >>>>");
            System.out.println("###############################");

            System.out.println("[1] Nuevo Vehículo.           #");
            System.out.println("[2] Listar Vehículos.         #");
            System.out.println("[3] Buscar Vehículo.          #");
            System.out.println("[4] Modificar Kms Vehículo    #");
            System.out.println("[5] Salir                     #");
            System.out.println("###############################");
            System.out.println(">> Elige una opción...");

            Scanner read = new Scanner(System.in);
            int opcion = read.nextInt();
            read.nextLine();

            switch (opcion) {

                case 1: //Nuevo Vehiculo.
                    System.out.println("\n#######################");
                    System.out.println("<<<< Nuevo Vehículo>>>>");
                    System.out.println("#######################");

                    try {
                        creaVehiculo(objConce); //Llamamos al método creaVehiculo para obtener los datos del vehiculo.
                        insertar = objConce.insertarVehiculo();// Luego de verificar los datos insertamos el nuevo vehículo.

                        switch (insertar) {
                            case 0:
                                System.out.println(">>>Vehículo Creado");
                                break;
                            case -1:
                                System.out.println(">>>Consecionario LLeno");

                                break;
                            case -2:
                                System.out.println(">>>Ya existe esa Matricula");
                                break;
                        }
                    } catch (Exception ex) {
                        System.out.println("ERROR!! Ingrese los datos del vehiculo correctamente.");
                    }
                    break;

                case 2: //Listar Vehículos.
                    if (objConce.contVehiculos > 0) {
                        objConce.listarVehiculo();
                        
                        //Recorrer el string consecionario en principal
                        /*
                        for (int i = 0; i < objConce.contVehiculos; i++) { // Usamos como limite del for el contVehiculos de la clase
                            //concesionario que es el encargador de llevar el registros de los coches creados.

                            System.out.println(arrayConce[i]);
                            
                        }*/
                    } else {
                        System.out.println("Es necesario registrar un vehículo en la opción [1].");
                    }
                    break;

                case 3: //Buscar Vehículo. 

                    if (objConce.contVehiculos > 0) {
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

                    if (objConce.contVehiculos > 0) {
                        System.out.println(">>> Ingrese Matrícula del vehículo:");
                        String matricula = read.nextLine();
                        System.out.println(">>> Kilometros del vehículo:");
                        String nuevoKm = read.nextLine();
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

                case 5: //Salir.
                    exitP = false;
                    break;
                    
                
            }
        }

    }

}
