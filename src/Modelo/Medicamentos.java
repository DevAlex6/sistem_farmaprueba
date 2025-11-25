/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author SISTEMA 8
 */
public class Medicamentos {
    int id;
    String medicamento;
    String precio;
    String vencimiento;
    String elaboracion;
    String procedencia;
  
    public Medicamentos (){
        
    } 
    
    public Medicamentos(int id, String medicamento, String precio, String vencimiento, String elaboracion, String procedencia) {
        this.id = id;
        this.medicamento = medicamento;
        this.precio = precio;
        this.vencimiento = vencimiento;
        this.elaboracion = elaboracion;
        this.procedencia = procedencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
    
    
}
 
