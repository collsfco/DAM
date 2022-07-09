package com.prog11.bbdd;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FColls
 */
public class ConnectionDB {
    Connection conexion=null;
    
    public Connection openConnection(){
        try
        {
            // Se registra el Driver de MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
            conexion = DriverManager.getConnection ( "jdbc:mariadb://localhost/concesionario","root","root");
                   
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());
        }
        return conexion;
    }
    
    public void closeConnection(){
        try {
            conexion.close();
            //System.out.println("Conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
