/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */

public class User_Admin {
    private static final String ARCHIVO_USUARIOS = "usuarios.adhv";

    public static void registrarUsuario(User usuario) {
        List<User> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    public static boolean iniciarSesion(String nombreUsuario, String contrasena) {
        List<User> usuarios = cargarUsuarios();
        for (User usuario : usuarios) {
            if (usuario.getUsername().equals(nombreUsuario) && usuario.getPassword().equals(contrasena)) {
                return true; // Si se pudo muchachossss
            }
        }
        return false; // Ya no :(
    }

    private static List<User> cargarUsuarios() {
        List<User> usuarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            usuarios = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Error 404 - No leyó nada
        }
        return usuarios;
    }

    private static void guardarUsuarios(List<User> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
