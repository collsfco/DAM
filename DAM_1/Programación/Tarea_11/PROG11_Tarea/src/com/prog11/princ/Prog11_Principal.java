package com.prog11.princ;

import com.prog11.bbdd.ConnectionDB;
import com.prog11.bbdd.PropietariosDAO;
import com.prog11.bbdd.VehiculosDAO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author FColls
 */
public class Prog11_Principal {

    /**
     * Método creado para imprimir por pantalla si se realizó correctamente la inserción del vehículo.
     * @param matricula Matricula del vehículo
     * @param marca Marca del vehículo
     * @param kms Kilometros del vehículo
     * @param precio Precio del vehículo
     * @param descripcion Descripción del vehículo
     * @param idProp Id del propietario del vehículo
     * @param conexion Conexión con la base de datos
     */
    private static void insertVehiculo(String matricula, String marca, int kms, float precio, String descripcion, int idProp,Connection conexion){
        if (VehiculosDAO.insertarVehiculo(matricula, marca, kms, precio, descripcion, idProp, conexion) == 0) {
            System.out.println("Vehiculo insertado");
        } else {
            System.out.println("Vehiculo No insertado");
        }
    }
    /**
     * Método creado para imprimir por pantalla si se realizó correctamente la inserción del usuario.
     * @param nombre Nombre completo del Propietario del vehículo
     * @param dni DNI del Propietario del vehículo
     * @param conexion Conexión con la base de datos
     */
    public static void insertUsuario(String nombre, String dni, Connection conexion) {

        if (PropietariosDAO.insertarPropietario(nombre, dni, conexion) == 0) {
            System.out.println("Usuario insertado");
        } else {
            System.out.println("Usuario No insertado");
        }

    }

    public static void main(String[] args) {
        
        String dni,matricula;
        ConnectionDB objConexion = new ConnectionDB();
        Connection conexion = objConexion.openConnection(); //Se envia conexion para abrir la conexion

        System.out.println("1.- Insertar varios vehículos y propietarios.");
        System.out.println("----------------------------------------------");
        //matricula, marca, kms, precio, descripcion, idProp
        
        insertUsuario("Maria Diaz","14785236M",conexion);
        insertUsuario("Ana Perez","01234567A",conexion);
        insertUsuario("Miguel Molero","78654321M",conexion);
        insertVehiculo("4321OGH","Renault",20000,2500,"Laguna azul",4,conexion);
        insertVehiculo("5221OPK","Ford",520000,1700,"Fiesta verde",2,conexion);
        insertVehiculo("7569LHO","Renault",320000,1900,"Scenic Gris",6,conexion);

      
        System.out.println("\n2.- Listar todos los vehículos.");
        System.out.println("----------------------------------------------");

        ArrayList<String> vehiculos = VehiculosDAO.recuperarTodosVehiculos(conexion);
        for (String lista : vehiculos) {
            System.out.println(lista);
        }

        System.out.println("\n3.- Actualizar propietario de un vehículo.");
        System.out.println("----------------------------------------------");

        matricula = "0102ppp"; //Vehiculo Creado en el archivo sql.
        int idProp = 3;

        if (VehiculosDAO.actualizarPropietario(matricula, idProp,conexion) == 0) {
            System.out.println("Vehiculo actualizado");
        } else {
            System.out.println("No actualizado");
        }

        System.out.println("\n4.- Listar todos los vehículos.");
        System.out.println("----------------------------------------------");

        vehiculos = VehiculosDAO.recuperarTodosVehiculos(conexion);
        for (String lista : vehiculos) {
            System.out.println(lista);
        }

        System.out.println("\n5.- Eliminar un vehículo que exista.");
        System.out.println("----------------------------------------------");

        matricula = "1234KKK";//Vehiculo Creado en el archivo sql.

        if (VehiculosDAO.eliminarVehiculo(matricula,conexion) == 0) {
            System.out.println("Vehiculo eliminado");
        } else {
            System.out.println("No existe el vehiculo indicado");
        }

        System.out.println("\n6.- Eliminar un vehículo que no exista.");
        System.out.println("----------------------------------------------");
        matricula = "5102ppp";

        if (VehiculosDAO.eliminarVehiculo(matricula,conexion) == 0) {
            System.out.println("Vehiculo eliminado");
        } else {
            System.out.println("No existe el vehiculo indicado");
        }

        System.out.println("\n7.- Listar todos los vehículos.");
        System.out.println("----------------------------------------------");
        vehiculos = VehiculosDAO.recuperarTodosVehiculos(conexion);
        for (String lista : vehiculos) {
            System.out.println(lista);
        }

        System.out.println("\n8.- Listar los vehículos de una marca.");
        System.out.println("----------------------------------------------");

        String marca = "renault";

        ArrayList<String> recuperaV = VehiculosDAO.recuperarVehiculoPorMarca(marca,conexion);
        if (recuperaV.isEmpty()) {
            System.out.println("Marca no existente");
        } else {
            for (String recorre : recuperaV) {

                System.out.println(recorre);
            }
        }

        System.out.println("\n9.- Listar todos los vehículos de un propietario.");
        System.out.println("----------------------------------------------");

        dni = "87654321M";

        ArrayList<String> recuperaV2 = PropietariosDAO.vehiculosDeUnPropietario(dni, conexion);

        if (recuperaV.isEmpty()) {
            System.out.println("No existen vehiculos registrados con ese dni");
        } else {
            for (String recorre : recuperaV2) {

                System.out.println(recorre);
            }
        }

        System.out.println("\n10.- Eliminar un propietario con vehículos.");
        System.out.println("----------------------------------------------");

        dni = "87654321M";//Propietario Creado en el archivo sql
        int eliminados = PropietariosDAO.eliminarPropietario(dni,conexion);
        if (eliminados != 0) {
            System.out.println("Propietarios eliminados: " + eliminados);
        } else {
            System.out.println("No Encontrado");
        }

        System.out.println("\n10.- Eliminar un propietario sin vehículos.");
        System.out.println("----------------------------------------------");
        
        dni="01234567A";
        eliminados = PropietariosDAO.eliminarPropietario(dni,conexion);
        if (eliminados != 0) {
            System.out.println("Propietarios eliminados: " + eliminados);
        } else {
            System.out.println("No Encontrado");
        }
        
        objConexion.closeConnection();

    }
}
