/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import java.util.ArrayList;

/**
 *
 * @author skxka
 */
public class Agente extends Persona {
    ArrayList<PropiedadMercado> propiedadesAsignadas = new ArrayList<>();

    public Agente(String nombre, String direccion, String noIdentidad, String celular, String telefonoOficina) {
        super(nombre, direccion, noIdentidad, celular, telefonoOficina);
    }

    public Agente() {
    }

    public ArrayList<PropiedadMercado> getPropiedadesAsignadas() {
        return propiedadesAsignadas;
    }

    public void setPropiedadesAsignadas(ArrayList<PropiedadMercado> propiedadesAsignadas) {
        this.propiedadesAsignadas = propiedadesAsignadas;
    }
    
    
}
