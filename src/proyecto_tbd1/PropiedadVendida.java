/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import java.util.Date;

/**
 *
 * @author skxka
 */
public class PropiedadVendida extends Propiedad {
    private Date fechaVenta;
    private double comisionVenta;

    public PropiedadVendida(String nombre, String ciudad, String direccion, String caracteristicas, String idPropiedad, double precio, int cantHabitaciones) {
        super(nombre, ciudad, direccion, caracteristicas, idPropiedad, precio, cantHabitaciones);
    }
    
    
    public PropiedadVendida(Date fechaVenta, double comisionVenta, String nombre, String ciudad, String direccion, String caracteristicas, String idPropiedad, double precio, int cantHabitaciones) {
        super(nombre, ciudad, direccion, caracteristicas, idPropiedad, precio, cantHabitaciones);
        this.fechaVenta = fechaVenta;
        this.comisionVenta = comisionVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getComisionVenta() {
        return comisionVenta;
    }

    public void setComisionVenta(double comisionVenta) {
        this.comisionVenta = comisionVenta;
    }
    
    
}
