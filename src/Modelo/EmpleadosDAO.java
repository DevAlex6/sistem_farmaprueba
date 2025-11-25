/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class EmpleadosDAO {
    ConexionBD conectar = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //METODO PARA EL BOTON LISTAR
    public List<Empleados> listar() {
        List<Empleados>datos = new ArrayList<>();
        String sql = "select * from empleados";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Empleados u = new Empleados ();
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setCargo(rs.getString(4));
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
    public int agregarDAO (Empleados u){
        String sql = "insert into empleados (nombre, apellido, cargo) values (?,?,?) ";
        try{
            
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCargo());
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
    //METODO PARA EL BOTON ACTUALIZAR
    public int Actualizar (Empleados u){
        String sql ="update empleados set nombre=?, apellido=?, cargo=?, Where idempleado=?";
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getApellido());
            ps.setString(3,u.getCargo());
            //para rol
            ps.setInt(4,u.getId());
            
            ps.executeUpdate();
        }catch(Exception e){    
        }
        return 1;
    }
    //METODO PARA ELIMINAR
    public void eliminar (int id){
        String sql = " delete from empleados where idempleado="+id;
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}
