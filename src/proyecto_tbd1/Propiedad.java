/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

/**
 *
 * @author skxka
 */
public class Propiedad {
    protected String nombre, ciudad, direccion, caracteristicas, idPropiedad;
    protected double precio;
    protected int cantHabitaciones;

    public Propiedad() {
    }

    public Propiedad(String nombre, String ciudad, String direccion, String caracteristicas, String idPropiedad, double precio, int cantHabitaciones) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.caracteristicas = caracteristicas;
        this.idPropiedad = idPropiedad;
        this.precio = precio;
        this.cantHabitaciones = cantHabitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(String idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantHabitaciones() {
        return cantHabitaciones;
    }

    public void setCantHabitaciones(int cantHabitaciones) {
        this.cantHabitaciones = cantHabitaciones;
    }

    @Override
    public String toString() {
        return "Propiedad" 
                + "\nNombre: " + nombre 
                + "\nCiudad: " + ciudad 
                + "\nDireccion: " + direccion 
                + "\nCaracteristicas: " + caracteristicas 
                + "\nID Propiedad: " + idPropiedad 
                + "\nPrecio: " + precio 
                + "\nCantidad de Habitaciones: " + cantHabitaciones + '}';
    }
    
    
}
