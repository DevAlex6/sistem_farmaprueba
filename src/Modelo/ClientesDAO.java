/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SISTEMA 8
 */
public class ClientesDAO {
    
     ConexionBD conectar = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Clientes> listar() {
        List<Clientes>datos = new ArrayList<>();
        String sql = "select * from clientes";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Clientes u = new Clientes ();
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setTelefono(rs.getString(4));
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
    
    public int agregarDAO (Clientes u){
        String sql = "insert into Clientes (nombre, apellido, telefono) values (?,?,?) ";
        try{
            
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTelefono());
            //para rol
            
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace(); 
        }finally {
        try { if (ps != null) ps.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return 1;
    }
    
    public int Actualizar (Clientes u){
        String sql ="update clientes set nombre=?, apellido=?, telefono=?, Where id=?";
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getApellido());
            ps.setString(3,u.getTelefono());
            //para rol
            ps.setInt(4,u.getId());
            
            ps.executeUpdate();
        }catch(Exception e){    
        }
        return 1;
    }
    
    public void eliminar (int id){
        String sql = " delete from clientes where id="+id;
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}
