/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.PreparedStatement;
import Util.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
/**
 *
 * @author SISTEMA 8
 */
public class LoginDAO {
    
    ConexionBD mysql = new ConexionBD();
    Connection con = mysql.conectar();
    
    public Usuarios login(String usuario, String contraseña){
        Usuarios u = null;
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND contraseña=?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1,usuario);
            ps.setString(2,contraseña);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                u = new Usuarios();
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("Contraseña"));
                u.setRol(rs.getString("rol"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return u;
    }
    
}
