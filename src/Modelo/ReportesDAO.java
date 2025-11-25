/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Util.ConexionBD;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author SISTEMA 8
 */
public class ReportesDAO {
    
    public void generarReporte(String nombreReporte){
        try{
        //Conectar a la base de datos
        ConexionBD mysql = new ConexionBD();
        Connection con = mysql.conectar();
        //Cargar el articulo desde el paquete reportes
        
        InputStream reporte = getClass().getResourceAsStream("/reportes/" + nombreReporte + ".jasper");
        if(reporte == null){
            JOptionPane.showMessageDialog(null,"No se encontro el archivo del reporte");
            return;
        }
        //Parametros del reporte
        HashMap<String, Object>parametros = new HashMap<>();
        parametros.put("TITULO",nombreReporte);
        
        //Llenar el reporte
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, con);
        
        //Mostrar reporte en ventana
        JasperViewer.viewReport(print,false);
        
        //Eligir ruta para guardar PDF
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar reporte como PDF");
        chooser.setSelectedFile(new java.io.File(nombreReporte + ".pdf"));
        
        int seleccion = chooser.showSaveDialog(null);
        if(seleccion == JFileChooser.APPROVE_OPTION){
            String rutaPDF = chooser.getSelectedFile().getAbsolutePath();
            
            //Exponer a PDF usando JasperExportManager
            JasperExportManager.exportReportToPdfFile(print,rutaPDF);
            
            JOptionPane.showMessageDialog(null, "PDF guardado en:" + rutaPDF);
        }
        
  } catch(JRException e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error a generar el reporte:" + e.getMessage());
        
  }   
 } 
}   

