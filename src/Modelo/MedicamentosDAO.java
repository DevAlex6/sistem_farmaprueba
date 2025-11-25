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
public class MedicamentosDAO {
    
    ConexionBD conectar = new ConexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //METODO PARA EL BOTON LISTAR
    public List<Medicamentos> listar() {
        List<Medicamentos>datos = new ArrayList<>();
        String sql = "select * from medicamentos";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while (rs.next()){
                Medicamentos u = new Medicamentos ();
                u.setId(rs.getInt(1));
                u.setMedicamento(rs.getString(2));
                u.setPrecio(rs.getString(3));
                u.setVencimiento(rs.getString(4));
                u.setElaboracion(rs.getString(5));
                u.setProcedencia(rs.getString(6));
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
    public int agregarDAO (Medicamentos u){
        String sql = "insert into Medicamentos (medicamento, precio, vencimiento, elaboracion, procedencia) values (?,?,?,?,?) ";
        try{
            
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, u.getMedicamento());
            ps.setString(2, u.getPrecio());
            ps.setString(3, u.getVencimiento());
            ps.setString(4, u.getElaboracion());
            ps.setString(5, u.getProcedencia());
            
            
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
    public int Actualizar (Medicamentos u){
        String sql ="update medicamentos set medicamento=?, precio=?, vencimiento=?, elaboracion=?, procedencia=?, Where id=?";
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.setString(1, u.getMedicamento());
            ps.setString(2, u.getPrecio());
            ps.setString(3, u.getVencimiento());
            ps.setString(4, u.getElaboracion());
            ps.setString(5, u.getProcedencia());
            
            
            ps.setInt(6,u.getId());
            
            ps.executeUpdate();
        }catch(Exception e){    
        }
        return 1;
    }
    //METODO PARA ELIMINAR
    public void eliminar (int id){
        String sql = " delete from medicamentos where id="+id;
        try{
            con=conectar.conectar();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}
