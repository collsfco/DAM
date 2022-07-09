package com.prog11.bbdd;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author FColls
 */
public class PropietariosDAO {
    
    private static String dniPropietario;
    private static String nombrePropietario;
    private static int idPropietario;

    public static String getDniPropietario() {
        return dniPropietario;
    }

    public static void setDniPropietario(String dniPropietario) {
        PropietariosDAO.dniPropietario = dniPropietario;
    }

    public static String getNombrePropietario() {
        return nombrePropietario;
    }

    public static void setNombrePropietario(String nombrePropietario) {
        PropietariosDAO.nombrePropietario = nombrePropietario;
    }

    public static int getIdPropietario() {
        return idPropietario;
    }

    public static void setIdPropietario(int idPropietario) {
        PropietariosDAO.idPropietario = idPropietario;
    }
    
    
    /**
     * Insertar un nuevo propietario: Recibe por parámetro los datos de un propietario a insertar, 
     * trata de insertarlo en la BBDD y devuelve 0 si la operación se realizó con éxito o -1 en caso contrario.
     * @return 
     */
    public static int insertarPropietario(String nombrePropietario,String dniPropietario,Connection conexion){
    
        int operacion = -1;

        try {
            String sentenciaSql = "INSERT INTO propietarios (id_prop, nombre_prop, dni_prop) VALUES (?, ?, ?)";
            PreparedStatement sentencia = null;

            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            
            /*Mejoras*/
            /*Se comprueba si existe un usuario con el dni indicado, en caso de existir un usuario con el mismo
            dni, no realiza el proceso de inserción*/
            ResultSet rs = s.executeQuery("SELECT COUNT(*) as count FROM propietarios WHERE dni_prop='" + dniPropietario + "'");
            if (rs.next() && (rs.getInt("count") == 0)) {

                sentencia = conexion.prepareStatement(sentenciaSql);
                    sentencia.setString(1, null);
                    sentencia.setString(2, nombrePropietario.toUpperCase());
                    sentencia.setString(3, dniPropietario.toUpperCase());
                    sentencia.executeUpdate();

                    operacion = 0;
                }
             else {
                operacion = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return operacion;
        
    }
    /**LISTA
     * Recuperar vehículos de un propietario: Recibe por parámetro el DNI de un propietario y devuelve una lista 
     * con la matrícula, marca, número de kms y precio de todo sus vehículos. Retornará null si hubo problemas.
     * @return 
     */
    
    public static ArrayList <String> vehiculosDeUnPropietario(String dniPropietario, Connection conexion){
        
        ArrayList<String> vehiculos = new ArrayList<String>();
        
        try {
            //Connection conexion = objConexion.openConnection();
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            ResultSet rs = s.executeQuery("SELECT * FROM vehiculos v JOIN propietarios p ON (v.id_prop=p.id_prop)where dni_prop='" + dniPropietario + "'");
            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                //vehiculos.add("Propietario: " + rs.getString("nombre_prop"));
                vehiculos.add(" Matricula: " + rs.getString(1) + ", Marca: " + rs.getString("marca_veh")
                        + ", Kilometros: " + rs.getString(3) + ", Precio: " + rs.getString(4)+", Propietario: " 
                        + rs.getString("nombre_prop"));
                
            }
            return vehiculos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    /**
     * Eliminar propietario: Recibe por parámetro el DNI de un propietario y trata de eliminarlo de la BBDD. 
     * Devuelve el número de registros eliminados.
     * @return 
     */
    
    public static int eliminarPropietario(String dniPropietario,Connection conexion){
        int operacion = 0;

        try {
            //Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            ResultSet rs = s.executeQuery("SELECT COUNT(*) as count FROM propietarios WHERE dni_prop='" + dniPropietario + "'");
            //operacion = rs.getInt("count");
            if (rs.next() && (rs.getInt("count") != 0)) {
                operacion = rs.getInt("count");
                rs = s.executeQuery("DELETE FROM propietarios WHERE dni_prop='" + dniPropietario + "'");
            } else {
                operacion = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operacion;
    }
    
    
}
