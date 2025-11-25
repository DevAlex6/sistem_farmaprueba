/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ReportesDAO;
import Vista.VistaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author SISTEMA 8
 */
public class ReportesControlador implements ActionListener {

    VistaReportes vista;
    ReportesDAO modelo = new ReportesDAO();

    public ReportesControlador(VistaReportes v) {
        this.vista = v;
        this.vista.btnUsuarios.addActionListener(this);
        this.vista.btnEmpresas.addActionListener(this);
        this.vista.btnMedicamentos.addActionListener(this);
        this.vista.btnEmpleados.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnUsuarios) {
            modelo.generarReporte("ReporteUsuarios");
        } else if (e.getSource() == vista.btnEmpresas) {
            modelo.generarReporte("ReporteEmpresas");
        } else if (e.getSource() == vista.btnMedicamentos) {
            modelo.generarReporte("ReporteMedicamentos");
        }else if (e.getSource() == vista.btnEmpleados) {
            modelo.generarReporte("ReporteEmpleados");
        
    }
        }
}
