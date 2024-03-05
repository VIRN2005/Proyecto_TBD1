/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

/**
 *
 * @author skxka
 */
public class Persona {
    protected String nombre, direccion, noIdentidad, celular, telefonoOficina;

    public Persona(String nombre, String direccion, String noIdentidad, String celular, String telefonoOficina) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.noIdentidad = noIdentidad;
        this.celular = celular;
        this.telefonoOficina = telefonoOficina;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNoIdentidad() {
        return noIdentidad;
    }

    public void setNoIdentidad(String noIdentidad) {
        this.noIdentidad = noIdentidad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    @Override
    public String toString() {
        return "Persona" 
                + "\nNombre: " + nombre 
                + "\nDireccion: " + direccion 
                + "\nNumero de Identidad: " + noIdentidad 
                + "\nCelular: " + celular 
                + "\nTelefonoOficina: " + telefonoOficina + '}';
    }
    
    
}
