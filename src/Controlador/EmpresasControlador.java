/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empresas;
import Modelo.EmpresasDAO;

import Util.Validaciones;
import Vista.VistaEmpresas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SISTEMA 8
 */
public class EmpresasControlador implements ActionListener{
    
    VistaEmpresas vista = new  VistaEmpresas();
    Empresas p = new Empresas();
    EmpresasDAO control = new EmpresasDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public EmpresasControlador (VistaEmpresas v){
        this.vista=v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        //ACCION DE ELIMINAR
        this.vista.btnEliminar.addActionListener(this);

    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        //para listar
        if (e.getSource() == vista.btnListar) {
            listar(vista.tabla);
        }
        //para agregar
        if (e.getSource() == vista.btnGuardar) {
            agregar();
            listar(vista.tabla);
        }
        //para editar
        if (e.getSource() == vista.btnEditar) {
            editar();
        }
        //para actualizar
        if (e.getSource() == vista.btnActualizar) {
            actualizar();
            listar(vista.tabla);
        }
        //para eliminar
        if (e.getSource() == vista.btnEliminar) {
            eliminar();
            listar(vista.tabla);
        }      
    }
    
     public void actualizar(){
        int idempresa=Integer.parseInt(vista.txtIdEmpresa.getText());
        String nombre = vista.txtNombre.getText();
        String nit = vista.txtNit.getText();
        String direccion = vista.txtDireccion.getText();
        String telefono = vista.txtTelefono.getText();
        String correo = vista.txtCorreo.getText();
        String fecharegistro = vista.txtFechaRegistro.getText();
        //para rol
        String estado = vista.cmbEstado.getSelectedItem().toString();
         
        //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloLetras(nombre,"nombre debe ser letras")){
            return;
        }
         if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(nit,"El nit no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloNumeros(nit,"El nit es solo numeros")){
            return;
        }
        if(!Validaciones.validarOchoNumeros(nit,"El nit es con 8 caracteres")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(direccion,"La direccion no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(telefono,"El telefono no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloNumeros(telefono,"El telefono es solo numeros")){
            return;
        }
          if(!Validaciones.validarOchoNumeros(telefono,"El telefono es con 8 caracteres")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(correo,"El correo no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCorreo(correo)){
            return;
        }
        if(!Validaciones.validarCampoVacio(fecharegistro,"La fecharegistro no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarComboBox3(estado,"Seleccione el estado")){
            return;
        }
         
        
        p.setIdempresa(idempresa);
        p.setNombre(nombre);
        p.setNit(nit);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setCorreo(correo);
        p.setFecharegistro(fecharegistro);
        p.setEstado(estado);
        
        int r = control.Actualizar(p);
        if (r==1){
            JOptionPane.showMessageDialog(vista,"Empresa actualizado");
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");
        }
    }
     public void agregar(){
        String nombre = vista.txtNombre.getText();
        String nit = vista.txtNit.getText();
        String direccion = vista.txtDireccion.getText();
        String telefono = vista.txtTelefono.getText();
        String correo = vista.txtCorreo.getText();
        String fecharegistro = vista.txtFechaRegistro.getText();
        String estado = vista.cmbEstado.getSelectedItem().toString();
        
         //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloLetras(nombre,"nombre debe ser letras")){
            return;
        }
         if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(nit,"El nit no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloNumeros(nit,"El nit es solo numeros")){
            return;
        }
        if(!Validaciones.validarOchoNumeros(nit,"El nit es con 8 caracteres")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(direccion,"La direccion no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(telefono,"El telefono no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloNumeros(telefono,"El telefono es solo numeros")){
            return;
        }
          if(!Validaciones.validarOchoNumeros(telefono,"El telefono es con 8 caracteres")){
            return;
        } 
        if(!Validaciones.validarCampoVacio(correo,"El correo no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCorreo(correo)){
            return;
        }
        if(!Validaciones.validarCampoVacio(fecharegistro,"La fecharegistro no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarComboBox3(estado,"Seleccione el estado")){
            return;
        }
        
        p.setNombre(nombre);
        p.setNit(nit);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setCorreo(correo);
        p.setFecharegistro(fecharegistro);
        p.setEstado(estado);
        
        int r=control.agregarDAO(p);
        if (r==1){
            
            JOptionPane.showMessageDialog(vista, "Empresa Agregado Correctamente");
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");

        }
        
    }
    //PARA EL BOTON EDITAR
    public void editar(){
        int fila = vista.tabla.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista,"Tiene que seleccionar una Fila");
            }else{
                int idempresa=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nombre=(String)vista.tabla.getValueAt(fila,1);
                String nit=(String)vista.tabla.getValueAt(fila,2);
                String direccion=(String)vista.tabla.getValueAt(fila,3);
                String telefono=(String)vista.tabla.getValueAt(fila,4);
                String correo=(String)vista.tabla.getValueAt(fila,5);
                String fecharegistro=(String)vista.tabla.getValueAt(fila,6);
                //para rol
                 String estado=vista.tabla.getValueAt(fila,7).toString();
                 
                vista.txtIdEmpresa.setText(""+idempresa);
                vista.txtNombre.setText(nombre);
                vista.txtNit.setText(nit);
                vista.txtDireccion.setText(direccion);
                vista.txtTelefono.setText(telefono);
                vista.txtCorreo.setText(correo);
                vista.txtFechaRegistro.setText(fecharegistro);
                //para rol
                 vista.cmbEstado.setSelectedItem(estado);
                
            }
    } 
    public void eliminar(){
        int fila = vista.tabla.getSelectedRow();
            
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, " Debe seleccionar una fila");
                
            }else{
                int idempresa = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                control.eliminar(idempresa);
                JOptionPane.showMessageDialog(vista, " Empresa eliminado corectamente");
            }
    }
     public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        List<Empresas> lista = control.listar();
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getIdempresa();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getNit();
            object[3] = lista.get(i).getDireccion();
            object[4] = lista.get(i).getTelefono();
            object[5] = lista.get(i).getCorreo();
            object[6] = lista.get(i).getFecharegistro();
            object[7] = lista.get(i).getEstado();
            
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
}
