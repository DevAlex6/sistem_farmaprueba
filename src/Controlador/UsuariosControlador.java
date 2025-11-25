package Controlador;

import Modelo.Usuarios;
import Modelo.UsuariosDAO;
import Util.Validaciones;
import Vista.VistaUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CristianTv
 */
public class UsuariosControlador implements ActionListener {
    
    VistaUsuarios vista = new VistaUsuarios();
    Usuarios p = new Usuarios();
    UsuariosDAO control = new UsuariosDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    
    
    public UsuariosControlador (VistaUsuarios v){
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
        String usuario = vista.txtUsuario.getText();
        String contraseña = vista.txtContraseña.getText();
        String correo = vista.txtCorreo.getText();
        //para rol
        String rol = vista.cmbRol.getSelectedItem().toString();
         
        //Validaciones
        if(!Validaciones.validarCampoVacio(usuario,"El usuario no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloLetras(usuario,"usuario")){
            return;
        }
       if(!Validaciones.validarLongitudCampo(usuario,3,"Minimo tres caracteres en el usuario")){
            return;
        }
        if(!Validaciones.validarCampoVacio(contraseña,"La contraseña no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarLongitudCampo(contraseña,8,"Minimo ocho caracteres en la contraseña")){
            return;
        }
        if(!Validaciones.validarCampoVacio(correo,"El correo no puede estar vacio")){
            return;
        } 
         if(!Validaciones.validarCorreo(correo)){
            return;
        }
         if(!Validaciones.validarComboBox(rol,"Seleccione el rol")){
            return;
        }
         
        
        p.setId(id);
        p.setUsuario(usuario);
        p.setContraseña(contraseña);
        p.setCorreo(correo);
        p.setRol(rol);
        
        int r = control.Actualizar(p);
        if (r==1){
            JOptionPane.showMessageDialog(vista,"Usuario actualizado");
            
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");
        }
    }
        
    public void agregar(){
        String usuario = vista.txtUsuario.getText();
        String contraseña = vista.txtContraseña.getText();
        String correo = vista.txtCorreo.getText();
        String rol = vista.cmbRol.getSelectedItem().toString();
        
       //Validaciones
        if(!Validaciones.validarCampoVacio(usuario,"El usuario no puede estar vacio")){
            return;
        }
        if(!Validaciones.validarSoloLetras(usuario,"usuario")){
            return;
        }
       if(!Validaciones.validarLongitudCampo(usuario,3,"Minimo tres caracteres en el usuario")){
            return;
        }
        if(!Validaciones.validarCampoVacio(contraseña,"La contraseña no puede estar vacio")){
            return;
        }
         if(!Validaciones.validarLongitudCampo(contraseña,8,"Minimo ocho caracteres en la contraseña")){
            return;
        }
        if(!Validaciones.validarCampoVacio(correo,"El correo no puede estar vacio")){
            return;
        } 
         if(!Validaciones.validarCorreo(correo)){
            return;
        }
         if(!Validaciones.validarComboBox(rol,"Seleccione el rol")){
            return;
        }
        
        p.setUsuario(usuario);
        p.setContraseña(contraseña);
        p.setCorreo(correo);
        p.setRol(rol);
        
        int r=control.agregarDAO(p);
        if (r==1){
            
            JOptionPane.showMessageDialog(vista, "Usuario Agregado Correctamente");
        }else{
            JOptionPane.showMessageDialog(vista, "ERROR GIL!!!");

        }
        if (rol==null){
            JOptionPane.showMessageDialog(vista, "Seleccione el Rol GIL");
            
        }
    }
//PARA EL BOTON EDITAR
    public void editar(){
        int fila = vista.tabla.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista,"Tiene que seleccionar una Fila");
            }else{
                int id=Integer.parseInt((String)vista.tabla.getValueAt(fila,0).toString());
                String usuario=(String)vista.tabla.getValueAt(fila,1);
                String contraseña=(String)vista.tabla.getValueAt(fila,2);
                String correo=(String)vista.tabla.getValueAt(fila,3);
                //para rol
                 String rol=vista.tabla.getValueAt(fila,4).toString();
                vista.txtId.setText(""+id);
                vista.txtUsuario.setText(usuario);
                vista.txtContraseña.setText(contraseña);
                vista.txtCorreo.setText(correo);
                //para rol
                 vista.cmbRol.setSelectedItem(rol);
                
            }
    }
    public void eliminar(){
        int fila = vista.tabla.getSelectedRow();
            
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, " Debe seleccionar una fila");
                
            }else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                control.eliminar(id);
                JOptionPane.showMessageDialog(vista, " Usuario eliminado corectamente");
            }
    }
    
    
    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        List<Usuarios> lista = control.listar();
        Object[] object = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getUsuario();
            object[2] = lista.get(i).getContraseña();
            object[3] = lista.get(i).getCorreo();
            object[4] = lista.get(i).getRol();
            
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
}
