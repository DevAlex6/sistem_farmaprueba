package Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexionBD {
    Connection conectar = null;
    String BaseDeDatos = "bd_sistema";
    String Usuario = "root";
    String Contraseña = "";
    public Connection conectar() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ 
                BaseDeDatos + "?useUnicode=true&useJDBCCompliantTimezoneShift="
                        + "true&useLegacyDatetimeCode=false&serverTimezone=UTC", 
                Usuario, Contraseña);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Sin Conexion" + e, "conexxion",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return conectar;
    }
}
