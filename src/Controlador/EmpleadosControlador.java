/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleados;
import Modelo.EmpleadosDAO;
import Util.Validaciones;
import Vista.VistaEmpleados;
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
public class EmpleadosControlador implements ActionListener{
    
    VistaEmpleados vista = new VistaEmpleados();
    Empleados p = new Empleados();
    EmpleadosDAO control = new EmpleadosDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public EmpleadosControlador (VistaEmpleados v){
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
        String cargo = vista.cmbCargo.getSelectedItem().toString();
        //para rol
        
          //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(nombre,"nombre solo letras")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        }  
         if(!Validaciones.validarCampoVacio(apellido,"El apellido no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(apellido,"apellido solo letras")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(apellido,3,"Minimo tres caracteres en el apellido")){
            return;
        }  
         if(!Validaciones.validarComboBox2(cargo,"Seleccione el cargo")){
            return;
        } 
        
         
        
        p.setId(id);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setCargo(cargo);
        
        
        int r = control.Actualizar(p);
        if (r==1){
            JOptionPane.showMessageDialog(vista,"Empleado actualizado");
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");
        }
    }
        
    public void agregar(){
        
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        String cargo = vista.cmbCargo.getSelectedItem().toString();
        
        //Validaciones
        if(!Validaciones.validarCampoVacio(nombre,"El nombre no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(nombre,"nombre solo letras")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(nombre,3,"Minimo tres caracteres en el nombre")){
            return;
        }  
         if(!Validaciones.validarCampoVacio(apellido,"El apellido no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarSoloLetras(apellido,"apellido solo letras")){
            return;
        }
        if(!Validaciones.validarLongitudCampo(apellido,3,"Minimo tres caracteres en el apellido")){
            return;
        }  
         if(!Validaciones.validarComboBox2(cargo,"Seleccione el cargo")){
            return;
        } 
        
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setCargo(cargo);
        
        
        int r=control.agregarDAO(p);
        if (r==1){
            
            JOptionPane.showMessageDialog(vista, "Empleado Agregado Correctamente");
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
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String nombre=(String)vista.tabla.getValueAt(fila,1);
                String apellido=(String)vista.tabla.getValueAt(fila,2);
                //para rol
                 String cargo=vista.tabla.getValueAt(fila,3).toString();
                vista.txtId.setText(""+id);
                vista.txtNombre.setText(nombre);
                vista.txtApellido.setText(apellido);
                vista.cmbCargo.setSelectedItem(cargo);
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
                JOptionPane.showMessageDialog(vista, " Empleado eliminado corectamente");
            }
    }
    
    
    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        List<Empleados> lista = control.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellido();
            object[3] = lista.get(i).getCargo();
            
            
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
    
}
