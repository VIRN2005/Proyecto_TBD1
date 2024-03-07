/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author Victor
 */
public class User_Admin {

    /*private static final String ARCHIVO_USUARIOS = "usuarios.adhv";

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
    }*/
    private static String url;
    private static String user;
    private static String password;
    private Connection connect;

    public User_Admin() {
        url = "jdbc:mariadb://localhost:3306/agencia_bienes_raices";
        user = "root";
        password = "Emilio2606";
        try {
            connect = DriverManager.getConnection(url, user, password);
            setup();
        } catch (Exception e) {

        }

    }

    public static void registrarUsuario(User usuario) {
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement ps = connection.prepareStatement("INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)")) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean iniciarSesion(String nombreUsuario, String contrasena) {
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?")) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            try (ResultSet resultSet = ps.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setup() {
        String path = "./setupDB.sql";
        try {
            FileReader rd = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(rd);
            ScriptRunner sc = new ScriptRunner(connect);
            sc.runScript(bf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
