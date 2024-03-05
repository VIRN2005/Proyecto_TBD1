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
public class PropiedadMercado extends Propiedad {
    private Date fechaPublicacion;

    public PropiedadMercado(String nombre, String ciudad, String direccion, String caracteristicas, String idPropiedad, double precio, int cantHabitaciones) {
        super(nombre, ciudad, direccion, caracteristicas, idPropiedad, precio, cantHabitaciones);
    }
    
    
    public PropiedadMercado(Date fechaPublicacion, String nombre, String ciudad, String direccion, String caracteristicas, String idPropiedad, double precio, int cantHabitaciones) {
        super(nombre, ciudad, direccion, caracteristicas, idPropiedad, precio, cantHabitaciones);
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    
}
