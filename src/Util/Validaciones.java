/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.JOptionPane;

/**
 *
 * @author SISTEMA 8
 */
public class Validaciones {
    //Invalidar un solo vacio
    public static boolean validarCampoVacio(String valor, String mensaje){
        if(valor==null || valor.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,mensaje);
            return false;
        }
        return true;
    }
    //Validar longitud de un campo
    public static boolean validarLongitudCampo(String valor,int min,String mensaje){
        if(valor==null || valor.length()<min){
            JOptionPane.showMessageDialog(null,mensaje);
            return false;
        }
        return true;
    }
    //Valida todos los campos de un formulario
    public static boolean validarCamposVacios(String[] valores,String mensaje){
        for(String valor: valores){
            if(valor==null || valor.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, mensaje);
                return false;
            }
        }
        return true;
    }
    //Validar si es un correo electronico
    public static boolean validarCorreo(String correo){
        String regex= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
                if(!correo.matches(regex)){
                    JOptionPane.showMessageDialog(null,"Correo electronico no valido.");
                    return false;
                }
                return true;
    }
    //Validar que sea solos letras
    public static boolean validarSoloLetras(String texto, String campo){
        if(!texto.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(null, campo + " solo puede contener letras");
            return false;
        }
        return true;
    }
    //Validad que solo sean numeros
    public static boolean validarSoloNumeros(String texto, String campo){
        if(!texto.matches("\\d+")){
            JOptionPane.showMessageDialog(null, campo + " solo puede contener numeros");
            return false;
        }
        return true;
    }
    public static boolean validarOchoNumeros(String texto, String campo) {
    if (!texto.matches("\\d{8}")) {
        JOptionPane.showMessageDialog(null, campo + " debe contener exactamente 8 números");
        return false;
    }
    return true;
}
    //validar el combobox
    public static boolean validarComboBox(String rol, String mensaje){
        if(rol==null || rol.equals("Seleccione un rol:")){
        JOptionPane.showMessageDialog(null, mensaje);
        return false;
    }
      return true;
   }
   
    public static boolean validarComboBox2(String cargo, String mensaje){
        if(cargo==null || cargo.equals("Selecciona un Cargo:")){
        JOptionPane.showMessageDialog(null, mensaje);
        return false;
    }
      return true;
   }
    
    public static boolean validarComboBox3(String estado, String mensaje){
        if(estado==null || estado.equals("Selecciona el Estado:")){
        JOptionPane.showMessageDialog(null, mensaje);
        return false;
    }
      return true;
   } 
}
