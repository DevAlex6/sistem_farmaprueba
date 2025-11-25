package Modelo;

import Util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    ConexionBD conectar = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //METODO PARA EL BOTON LISTAR
    public List<Usuarios> listar() {
        List<Usuarios>datos = new ArrayList<>();
        String sql = "select * from usuarios";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Usuarios u = new Usuarios ();
                u.setId(rs.getInt(1));
                u.setUsuario(rs.getString(2));
                u.setContraseña(rs.getString(3));
                u.setCorreo(rs.getString(4));
                // para rol
                u.setRol(rs.getString(5));
                datos.add(u);           
            }
        } catch (Exception e){
            e.printStackTrace(); 
        }finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return datos;
    }
    
    //METODO PARA EL BOTON AGREGAR
    public int agregarDAO (Usuarios u){
        String sql = "insert into Usuarios (usuario, contraseña, correo, rol) values (?,?,?,?) ";
        try{
            
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContraseña());
            ps.setString(3, u.getCorreo());
            //para rol
            ps.setString(4, u.getRol());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace(); 
        }finally {
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return 1;
    }
    //METODO PARA EL BOTON ACTUALIZAR
    public int Actualizar (Usuarios u){
        String sql ="update usuarios set usuario=?, contraseña=?, correo=?, rol=? Where id=?";
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1,u.getUsuario());
            ps.setString(2,u.getContraseña());
            ps.setString(3,u.getCorreo());
            //para rol
            ps.setString(4,u.getRol());
            ps.setInt(5,u.getId());
            
            ps.executeUpdate();
        }catch(Exception e){    
        }
        return 1;
    }
    //METODO PARA ELIMINAR
    public void eliminar (int id){
        String sql = " delete from usuarios where id="+id;
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}