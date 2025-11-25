/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.LoginDAO;
import Modelo.Usuarios;
import Util.Validaciones;
import Vista.VistaLogin;
import Vista.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author SISTEMA 8
 */
public class LoginControlador implements ActionListener{
    
    VistaLogin vista = new VistaLogin();
    LoginDAO dao = new LoginDAO();
    
    public LoginControlador(VistaLogin vista){
        this.vista = vista;
        this.dao = new LoginDAO();
        this.vista.btnLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e ){
        if(e.getSource() == vista.btnLogin){
        String usuario = vista.txtUsuario.getText();
        String contraseña = new String(vista.txtContraseña.getText());
        
        //Validaciones
        if(!Validaciones.validarCampoVacio(usuario,"El usuario no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloLetras(usuario,"usuario")){
            return;
        }
        if(!Validaciones.validarCampoVacio(contraseña,"La contraseña no puede estar vacio")){
            return;
        }
        
        
        
        
        Usuarios u = dao.login(usuario,contraseña);
        
        if(u!=null){
            VistaMenu v = new VistaMenu();
            v.setVisible(true);
            v.setLocationRelativeTo(v);
            vista.dispose();
        }else{
            JOptionPane.showMessageDialog(vista, "Usuario o Contraseña incorrrectos");
        }
    }
  }
}
