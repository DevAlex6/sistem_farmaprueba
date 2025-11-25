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
public class EmpresasDAO {
     ConexionBD conectar = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //METODO PARA EL BOTON LISTAR
    public List<Empresas> listar() {
        List<Empresas>datos = new ArrayList<>();
        String sql = "select * from empresas";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Empresas u = new Empresas ();
                u.setIdempresa(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setNit(rs.getString(3));
                u.setDireccion(rs.getString(4));
                u.setTelefono(rs.getString(5));
                u.setCorreo(rs.getString(6));
                u.setFecharegistro(rs.getString(7));
                // para rol
                u.setEstado(rs.getString(8));
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
    public int agregarDAO (Empresas u){
        String sql = "insert into Empresas (nombre, nit, direccion, telefono, correo, fecharegistro, estado) values (?,?,?,?,?,?,?) ";
        try{
            
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getNit());
            ps.setString(3, u.getDireccion());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getFecharegistro());
            //para rol
            ps.setString(7, u.getEstado());
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
    public int Actualizar (Empresas u){
        String sql ="update empresas set nombre=?, nit=?, direccion=?, telefono=?, correo=?, fecharegistro=?, estado=? Where idempresa=?";
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getNit());
            ps.setString(3, u.getDireccion());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getFecharegistro());
            //para rol
            ps.setString(7, u.getEstado());
            ps.setInt(8,u.getIdempresa());
            
            ps.executeUpdate();
        }catch(Exception e){    
        }
        return 1;
    }
    //METODO PARA ELIMINAR
    public void eliminar (int idempresa){
        String sql = " delete from empresas where idempresa="+idempresa;
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}
