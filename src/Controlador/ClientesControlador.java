/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Clientes;
import Modelo.ClientesDAO;
import Util.Validaciones;
import Vista.VistaClientes;
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
public class ClientesControlador implements ActionListener{
    
    VistaClientes vista = new  VistaClientes();
   Clientes p = new Clientes();
    ClientesDAO control = new ClientesDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ClientesControlador (VistaClientes v){
        this.vista=v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        //PARA EL EDITAR
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
        int id=Integer.parseInt(vista.txtId.getText());
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        String telefono = vista.txtTelefono.getText();
        //para rol
        
         //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(nombre,"nombre")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        }  
        if(!Validaciones.validarCampoVacio(apellido,"El apellido no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(apellido,"apellido")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(apellido,3,"Minimo tres caracteres en el apellido")){
            return;
        }  
         if(!Validaciones.validarSoloNumeros(telefono,"El telefono no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarOchoNumeros(telefono,"El telefono es con 8 caracteres")){
            return;
        } 
        
        p.setId(id);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setTelefono(telefono);
        
        
        int r = control.Actualizar(p);
        if (r==1){
            JOptionPane.showMessageDialog(vista,"Cliente actualizado");
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");
        }
    }
    
    public void agregar(){
        
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        String telefono = vista.txtTelefono.getText();
        
        //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(nombre,"nombre")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        }  
        if(!Validaciones.validarCampoVacio(apellido,"El apellido no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(apellido,"apellido")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(apellido,3,"Minimo tres caracteres en el apellido")){
            return;
        }  
         if(!Validaciones.validarSoloNumeros(telefono,"El telefono no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarOchoNumeros(telefono,"El telefono es con 8 caracteres")){
            return;
        }  
       
        
        p.setNombre(nombre);
        p.setApellido(apellido);
         p.setTelefono(telefono);
        
        
        int r=control.agregarDAO(p);
        if (r==1){
            
            JOptionPane.showMessageDialog(vista, "Cliente Agregado Correctamente");
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");

        }
    }
    
    public void editar(){
        int fila = vista.tabla.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista,"Tiene que seleccionar una Fila");
            }else{
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nombre=(String)vista.tabla.getValueAt(fila,1);
                String apellido=(String)vista.tabla.getValueAt(fila,2);
                String telefono=(String)vista.tabla.getValueAt(fila,3);
                vista.txtId.setText(""+id);
                vista.txtNombre.setText(nombre);
                vista.txtApellido.setText(apellido);
                vista.txtTelefono.setText(apellido);
                //para rol
                 
                
            }
    }
    
    public void eliminar(){
        int fila = vista.tabla.getSelectedRow();
            
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, " Debe seleccionar una fila");
                
            }else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                control.eliminar(id);
                JOptionPane.showMessageDialog(vista, " Cliente eliminado corectamente");
            }
    }
    
     public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        List<Clientes> lista = control.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellido();
            object[3] = lista.get(i).getTelefono();
            
            
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
}
