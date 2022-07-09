package com.prog11.bbdd;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author FColls
 */
public class VehiculosDAO {

   
    private static String matricula;
    private static int idPropietario;
    private static String marca;
    private static int kms;
    private static float precio;
    private static String descripcion;

    public static String getMatricula() {
        return matricula;
    }

    public static void setMatricula(String matricula) {
        VehiculosDAO.matricula = matricula;
    }

    public static int getIdPropietario() {
        return idPropietario;
    }

    public static void setIdPropietario(int idPropietario) {
        VehiculosDAO.idPropietario = idPropietario;
    }

    public static String getMarca() {
        return marca;
    }

    public static void setMarca(String marca) {
        VehiculosDAO.marca = marca;
    }

    public static int getKms() {
        return kms;
    }

    public static void setKms(int kms) {
        VehiculosDAO.kms = kms;
    }

    public static float getPrecio() {
        return precio;
    }

    public static void setPrecio(int precio) {
        VehiculosDAO.precio = precio;
    }

    public static String getDescripcion() {
        return descripcion;
    }

    public static void setDescripcion(String descripcion) {
        VehiculosDAO.descripcion = descripcion;
    }

    /**
     * Insertar un nuevo vehículo: Recibe por parámetro los datos del vehículo a
     * insertar, trata de insertarlo en la BBDD y devuelve 0 si la operación se
     * realizó con éxito o -1 en caso contrario.
     *
     * @return
     */
    public static int insertarVehiculo(String matricula, String marca, int kms, float precio, String descripcion, int idPropietario,Connection conexion) {
        int operacion = -1;

        try {

            String sentenciaSql = "INSERT INTO vehiculos (mat_veh, marca_veh, kms_veh, precio_veh, desc_veh, id_prop ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = null;

            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
           
            /*Mejoras*/
            /*Comprobamos que no exista un vehiculo con la misma matrícula*/
            ResultSet rs = s.executeQuery("SELECT COUNT(*) as count FROM vehiculos WHERE mat_veh='" + matricula + "'");
            if (rs.next() && (rs.getInt("count") == 0)) { //Comprobamos que no exista un vehiculo con la misma matrícula
                rs = s.executeQuery("SELECT COUNT(*) as count FROM propietarios WHERE id_prop='" + idPropietario + "'");
                /*Comprobamos que existe un propietario con el ID indicado,si no existe ese propietario no se crea el vehículo.
                Ya que el Id de Propietario es asignado por el AUTO_INCREMENT, no es logico asignar a un vehiculo un id_prop
                de un propietario que no existe*/
                if (rs.next() && (rs.getInt("count") != 0)) {
                    sentencia = conexion.prepareStatement(sentenciaSql);
                    sentencia.setString(1, matricula.toUpperCase());
                    sentencia.setString(2, marca.toUpperCase());
                    sentencia.setInt(3, kms);
                    sentencia.setFloat(4, precio);
                    sentencia.setString(5, descripcion.toUpperCase());
                    sentencia.setInt(6, idPropietario);
                    sentencia.executeUpdate();

                    operacion = 0;
                }
            } else {
                operacion = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operacion;
    }

    /**
     * Actualizar propietario de vehículo: Recibe por parámetro la matrícula de
     * un vehículo junto al identificador de un propietario y trata de
     * actualizar el vehículo en la BBDD. Devuelve 0 si la operación se realizó
     * con éxito o -1 si el vehículo no existe.
     *
     * @return
     */
    public static int actualizarPropietario(String matricula, int idPropietario,Connection conexion) {

        //ConnectionDB objConexion = new ConnectionDB();
        int operacion = -1;

        try {
            
            String sentenciaSql ="UPDATE vehiculos set id_prop=? WHERE mat_veh=?";
            PreparedStatement sentencia = null;
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
           
            
            /*Mejoras*/
            /*Comprobamos que exista el vehículo al cual se le quiere actualizar el propietario.*/
            ResultSet rs = s.executeQuery("SELECT COUNT(*) as count FROM vehiculos WHERE mat_veh='" + matricula + "'");
            if (rs.next() && (rs.getInt("count") != 0)) {
                /*Comprobamos que exista el propietario al cual se le quiere asignar el vehículo, ya que si no existe
                el nuevo propietario no tiene sentido realizar el cambio de propietario*/
                rs = s.executeQuery("SELECT COUNT(*) as count FROM propietarios WHERE id_prop='" + idPropietario + "'");
                if (rs.next() && (rs.getInt("count") != 0)) {
                    sentencia = conexion.prepareStatement(sentenciaSql);
                    sentencia.setString(2, matricula.toUpperCase());
                    sentencia.setInt(1, idPropietario);
                    sentencia.executeUpdate();
                    
                    operacion = 0;
                }
            } else {
                operacion = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operacion;
    }

    /**
     * Eliminar vehículo: Recibe por parámetro la matrícula de un vehículo y
     * trata de eliminarlo de la BBDD. Devuelve 0 si la operación se realizó con
     * éxito o -1 si el vehículo no existe.
     *
     * @return
     */
    public static int eliminarVehiculo(String matricula,Connection conexion) {

        //ConnectionDB objConexion = new ConnectionDB();
        int operacion = 0;

        try {
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            ResultSet rs = s.executeQuery("SELECT COUNT(*) as count FROM vehiculos WHERE mat_veh='" + matricula + "'");

            if (rs.next() && (rs.getInt("count") != 0)) {

                rs = s.executeQuery("DELETE FROM vehiculos WHERE mat_veh='" + matricula + "'");
                operacion = 0;
            } else {
                operacion = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operacion;
    }

    /**
     * LISTA Recuperar todos los vehículos: No recibe parámetros y devuelve una
     * lista con todos los vehículos del concesionario. Para cada vehículo, la
     * lista contendrá una cadena de caracteres con los datos del mismo,
     * incluido el nombre del propietario
     */
    public static ArrayList<String> recuperarTodosVehiculos(Connection conexion) {

        ArrayList<String> vehiculos = new ArrayList<String>();
       //ConnectionDB objConexion = new ConnectionDB();

        try {
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            ResultSet rs = s.executeQuery("SELECT * FROM vehiculos v JOIN propietarios p ON (v.id_prop=p.id_prop)");
            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                vehiculos.add(" Matricula: " + rs.getString(1) + ", Marca: " + rs.getString("marca_veh")
                        + ", Kilometros: " + rs.getString(3) + ", Precio: " + rs.getString(4)
                        + ", Descripción: " + rs.getString(5) + ", Propietario: " + rs.getString("nombre_prop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    /**
     * LISTA Recuperar vehículos por marca: Recibe una marca por parámetro y
     * devuelve una lista con el listado de vehículos de la citada marca. Para
     * cada vehículo, la lista contendrá una cadena de caracteres con los datos
     * del mismo, incluido el nombre del propietario. Si no existen vehículos,
     * devuelve el ArrayList vacío.
     *
     * @return
     */
    public static ArrayList<String> recuperarVehiculoPorMarca(String marca,Connection conexion) {
        ArrayList<String> vehiculos = new ArrayList<String>();
        //ConnectionDB objConexion = new ConnectionDB();

        try {
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            ResultSet rs = s.executeQuery("SELECT * FROM vehiculos v JOIN propietarios p ON (v.id_prop=p.id_prop)where marca_veh='" + marca + "'");
            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                vehiculos.add(" Matricula: " + rs.getString(1) + ", Marca: " + rs.getString("marca_veh")
                        + ", Kilometros: " + rs.getString(3) + ", Precio: " + rs.getString(4)
                        + ", Descripción: " + rs.getString(5) + ", Propietario: " + rs.getString("nombre_prop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    /**
     * LISTA Recuperar vehículos: No recibe parámetros (solo la coneción con la
     * BBDD) y retorna una lista con la matrícula, marca, kilómetros y precio de
     * cada vehículo.
     *
     * @return
     */
    public static ArrayList<String> recuperarVehiculos(Connection conexion) {
        ArrayList<String> vehiculos = new ArrayList<String>();
       
        try {
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            ResultSet rs = s.executeQuery("select * from vehiculos");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                vehiculos.add(" Matricula: " + rs.getString(1) + ", Marca: " + rs.getString("marca_veh")
                        + ", Kilometros: " + rs.getString(3) + ", Precio: " + rs.getFloat("precio_veh"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
}
