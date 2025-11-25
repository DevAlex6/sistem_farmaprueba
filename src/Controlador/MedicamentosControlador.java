/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Medicamentos;
import Modelo.MedicamentosDAO;
import Util.Validaciones;
import Vista.VistaMedicamentos;
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
public class MedicamentosControlador implements ActionListener{
    
    VistaMedicamentos vista = new VistaMedicamentos();
    Medicamentos p = new Medicamentos();
    MedicamentosDAO control = new MedicamentosDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public  MedicamentosControlador (VistaMedicamentos v){
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
        String medicamento = vista.txtMedicamento.getText();
        String precio = vista.txtPrecio.getText();
        String vencimiento = vista.txtVencimiento.getText();
        String elaboracion = vista.txtElaboracion.getText();
        String procedencia = vista.txtProcedencia.getText();
        
        //Validaciones
        if(!Validaciones.validarCampoVacio(medicamento,"El medicamento no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(precio,"El precio no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(vencimiento,"El vencimiento no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(elaboracion,"La elaboracion no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(procedencia,"La procedencia no puede estar vacio")){
            return;
        }
        
        p.setId(id);
        p.setMedicamento(medicamento);
        p.setPrecio(precio);
        p.setVencimiento(vencimiento);
        p.setElaboracion(elaboracion);
        p.setProcedencia(procedencia);
        
        int r = control.Actualizar(p);
        if (r==1){
            JOptionPane.showMessageDialog(vista,"Medicamento actualizado");
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");
        }
    }
    public void agregar(){
        String medicamento = vista.txtMedicamento.getText();
        String precio = vista.txtPrecio.getText();
        String vencimiento = vista.txtVencimiento.getText();
        String elaboracion = vista.txtElaboracion.getText();
        String procedencia = vista.txtProcedencia.getText();
        
        //Validaciones
        if(!Validaciones.validarCampoVacio(medicamento,"El medicamento no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(precio,"El precio no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(vencimiento,"El vencimiento no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(elaboracion,"La elaboracion no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarCampoVacio(procedencia,"La procedencia no puede estar vacio")){
            return;
        }
        
        p.setMedicamento(medicamento);
        p.setPrecio(precio);
        p.setVencimiento(vencimiento);
        p.setElaboracion(elaboracion);
        p.setProcedencia(procedencia);
        
        int r=control.agregarDAO(p);
        if (r==1){
            
            JOptionPane.showMessageDialog(vista, "Medicamento Agregado Correctamente");
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
                String medicamento=(String)vista.tabla.getValueAt(fila,1);
                String precio=(String)vista.tabla.getValueAt(fila,2);
                String vencimiento=(String)vista.tabla.getValueAt(fila,3);
                String elaboracion=(String)vista.tabla.getValueAt(fila,4);
                String procedencia=(String)vista.tabla.getValueAt(fila,5);
                
               
                 String rol=vista.tabla.getValueAt(fila,4).toString();
                vista.txtId.setText(""+id);
                vista.txtMedicamento.setText(medicamento);
                vista.txtPrecio.setText(precio);
                vista.txtVencimiento.setText(vencimiento);
                vista.txtElaboracion.setText(elaboracion);
                vista.txtProcedencia.setText(procedencia);
            }
    }
    public void eliminar(){
        int fila = vista.tabla.getSelectedRow();
            
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, " Debe seleccionar una fila");
                
            }else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                control.eliminar(id);
                JOptionPane.showMessageDialog(vista, " Medicamento eliminado corectamente");
            }
    }
    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        List<Medicamentos> lista = control.listar();
        Object[] object = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getMedicamento();
            object[2] = lista.get(i).getPrecio();
            object[3] = lista.get(i).getVencimiento();
            object[4] = lista.get(i).getElaboracion();
            object[5] = lista.get(i).getProcedencia();
            
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
}
