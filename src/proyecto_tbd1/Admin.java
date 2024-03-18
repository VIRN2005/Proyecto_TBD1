/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_tbd1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author darie
 */
public class Admin {

    private String url;
    private String user;
    private String password;
    private Connection connect;
    private ResultSet results;

    public Admin() {
        //url = "jdbc:mariadb://localhost:3306/agencia_bienes_raices";
        url = "jdbc:mariadb://agencia-bienes-raices.cz8wuy8yk8e9.us-east-1.rds.amazonaws.com/agencia_bienes_raices";
        user = "admin";
        password = "23456789";

        try {
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setup() {
        String path = "./setup2.sql";
        String path2 = "./initial_state.sql";
        try {
            FileReader rd = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(rd);
            ScriptRunner sc = new ScriptRunner(connect);
            sc.runScript(bf);

            FileReader rd2 = new FileReader(new File(path2));
            BufferedReader bf2 = new BufferedReader(rd2);
            ScriptRunner sc2 = new ScriptRunner(connect);
            sc2.runScript(bf2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean crearagente(int id, String nombre, String direccion, int celular, int teleOfi) {
        try {
            CallableStatement call = connect.prepareCall("{call crearagente(?,?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.setInt(5, teleOfi);
            call.execute();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }

    public boolean crearcomprador(int id, String nombre, String direccion, int celular) {

        CallableStatement call;
        try {
            call = connect.prepareCall("{call crearcomprador(?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.execute();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }

    public boolean crearvendedor(int id, String nombre, String direccion, int celular) {

        CallableStatement call;
        try {
            call = connect.prepareCall("{call crearvendedor(?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.execute();
            return true;
        } catch (SQLException ex) {
        
        }
        return false;
    }

    public boolean modificaragente(int id, String nombre, String direccion, int celular, int teleOfi) {
        try {
            CallableStatement call = connect.prepareCall("{call modificaragente(?,?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.setInt(5, teleOfi);
            call.execute();
            return true;
        } catch (SQLException ex) {
           
        }
        return false;
    }

    public boolean modificarvendedor(int id, String nombre, String direccion, int celular) {

        CallableStatement call;
        try {
            call = connect.prepareCall("{call modificarvendedor(?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.execute();
            return true;
        } catch (SQLException ex) {
        
        }
        return false;
    }

    public boolean modificarcomprador(int id, String nombre, String direccion, int celular) {

        CallableStatement call;
        try {
            call = connect.prepareCall("{call modificarcomprador(?,?,?,?)}");
            call.setInt(1, id);
            call.setString(2, nombre);
            call.setString(3, direccion);
            call.setInt(4, celular);
            call.execute();
            return true;
        } catch (SQLException ex) {
           
        }
        return false;
    }

    public boolean borraragente(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borraragente(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean borrarcomprador(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borrarcomprador(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean borrarvendedor(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borrarvendedor(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean borraradmin(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borrarusuario(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    public boolean crearpropiedadm(String nombre, String ciudad, String direccion, int cantidadDormitorios, String caracteristicas, double precio, Date fechaPublicada, int noIdentidad_A, int noIdentidad_V, String path) {
        try {
            CallableStatement call = connect.prepareCall("{call crearpropiedadm(?,?,?,?,?,?,?,?,?,?)}");

            call.setString(1, nombre);
            call.setString(2, ciudad);
            call.setString(3, direccion);
            call.setInt(4, cantidadDormitorios);
            call.setString(5, caracteristicas);
            call.setDouble(6, precio);
            call.setDate(7, fechaPublicada);
            call.setInt(8, noIdentidad_A);
            call.setInt(9, noIdentidad_V);
            call.setString(10, path);
            call.execute();
            return true;
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
    }

    public boolean crearpropiedadv(String nombre, String ciudad, String direccion, int cantidadDormitorios, String caracteristicas, double precio, Date fechaPublicada, Date fechaVendida, int noIdentidad_A, int noIdentidad_V, int noIdentidad_C, double comision, String path) {
        try {
            CallableStatement call = connect.prepareCall("{call crearpropiedadv(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            call.setString(1, nombre);
            call.setString(2, ciudad);
            call.setString(3, direccion);
            call.setInt(4, cantidadDormitorios);
            call.setString(5, caracteristicas);
            call.setDouble(6, precio);
            call.setDate(7, fechaPublicada);
            call.setDate(8, fechaVendida);
            call.setInt(9, noIdentidad_A);
            call.setInt(10, noIdentidad_V);
            call.setInt(11, noIdentidad_C);
            call.setDouble(12, comision);
            call.setString(13, path);
            call.execute();
            return true;
        } catch (SQLException ex) {

        }
        return false;
    }

    public boolean modificarpropiedadv(int idPropiedad, String nombre, String ciudad, String direccion, int cantidadDormitorios, String caracteristicas, double precio, Date fechaPublicada, Date fechaVendida, int noIdentidad_A, int noIdentidad_V, int noIdentidad_C, double comision, String path) {
        try {
            CallableStatement call = connect.prepareCall("{call modificarpropiedadv(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, idPropiedad);
            call.setString(2, nombre);
            call.setString(3, ciudad);
            call.setString(4, direccion);
            call.setInt(5, cantidadDormitorios);
            call.setString(6, caracteristicas);
            call.setDouble(7, precio);
            call.setDate(8, fechaPublicada);
            call.setDate(9, fechaVendida);
            call.setInt(10, noIdentidad_A);
            call.setInt(11, noIdentidad_V);
            call.setInt(12, noIdentidad_C);
            call.setDouble(13, comision);
            call.setString(14, path);
            call.execute();
            return true;
        } catch (SQLException ex) {
           
        }
        return false;
    }

    public boolean modificarpropiedadm(int idPropiedad, String nombre, String ciudad, String direccion, int cantidadDormitorios, String caracteristicas, double precio, Date fechaPublicada, int noIdentidad_A, int noIdentidad_V, String path) {
        try {
            CallableStatement call = connect.prepareCall("{call modificarpropiedadm(?,?,?,?,?,?,?,?,?,?,?)}");
            call.setInt(1, idPropiedad);
            call.setString(2, nombre);
            call.setString(3, ciudad);
            call.setString(4, direccion);
            call.setInt(5, cantidadDormitorios);
            call.setString(6, caracteristicas);
            call.setDouble(7, precio);
            call.setDate(8, fechaPublicada);
            call.setInt(9, noIdentidad_A);
            call.setInt(10, noIdentidad_V);
            call.setString(11, path);
            call.execute();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }

    public boolean borrarpropiedadm(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borrarpropiedadm(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean borrarpropiedadv(int id) {
        CallableStatement call;
        try {
            call = connect.prepareCall("{call borrarpropiedadv(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean crearusuario(String username, int id, String passwrd, String rol) {

        try {
            CallableStatement call = connect.prepareCall("{call crearusuario(?,?,?,?)}");
            call.setString(1, username);
            call.setInt(2, id);
            call.setString(3, passwrd);
            call.setString(4, rol);
            call.execute();
            return true;
        } catch (SQLException ex) {
         
        }
        return false;
    }

    public boolean modificarusuario(String username, String passwrd) {

        try {
            CallableStatement call = connect.prepareCall("{call changepassword(?,?)}");
            call.setString(1, username);
            call.setString(2, passwrd);
            call.execute();
            return true;
        } catch (SQLException ex) {
           
        }
        return false;
    }

    public boolean eliminarusuario(int id) {

        try {
            CallableStatement call = connect.prepareCall("{call borrarusuario(?)}");
            call.setInt(1, id);
            call.execute();
            return true;
        } catch (SQLException ex) {
            
        }
        return false;
    }

    public String[] login(String username, String password) {
        String[] strArr = new String[2];
        strArr[0] = "error";
        strArr[1] = "error";
        try {
            CallableStatement call = connect.prepareCall("{call login(?,?,?,?)}");
            call.setString(1, username);
            call.setString(2, password);
            call.setString(3, strArr[0]);
            call.setString(4, strArr[1]);
            call.registerOutParameter(3, java.sql.Types.VARCHAR);
            call.registerOutParameter(4, java.sql.Types.VARCHAR);
            call.execute();
            strArr[0] = call.getString(3);
            strArr[1] = call.getString(4);
            return strArr;
        } catch (SQLException ex) {
           
        }
        return strArr;

    }
    
    public String getimgpathm(int id){
        try {
            String answer = "";
            CallableStatement call = connect.prepareCall("{call getimgpathm(?,?)}");
            call.setInt(1, id);
            call.setString(2, answer);
            call.registerOutParameter(2, java.sql.Types.VARCHAR);
            call.execute();
            return call.getString(2);
        } catch (SQLException ex) {
           
        }
        return null;
    }
    public String getimgpathv(int id){
        try {
            String answer = "";
            CallableStatement call = connect.prepareCall("{call getimgpathv(?,?)}");
            call.setInt(1, id);
            call.setString(2, answer);
            call.registerOutParameter(2, java.sql.Types.VARCHAR);
            call.execute();
            return call.getString(2);
        } catch (SQLException ex) {
           
        }
        return null;
    }
    public int getId(String username){
        try{
             int id = 0;
             CallableStatement call = connect.prepareCall("{call getID(?,?)}");
             call.setString(1, username);
             call.setInt(2, id);
             call.registerOutParameter(2, java.sql.Types.NUMERIC);
             call.execute();
             return call.getInt(2);
        }catch(Exception e){
            return -1;        }
    }

    public void logout() {
        try {
            CallableStatement call = connect.prepareCall("{call logout}");
            call.execute();
        } catch (SQLException ex) {
           
        }

    }

    public ResultSet getAdmins() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getadmins()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }
    public ResultSet getpropiedadesmagente(int id){
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesmagente(?)}");
            call.setInt(1, id);
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }
    
    public ResultSet getpropiedadesvagente(int id){
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesvagentes(?)}");
            call.setInt(1, id);
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }
    
    public ResultSet getpropiedadesvvendedor(int id){
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesvvendedor(?)}");
            call.setInt(1, id);
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }
    
    public ResultSet getpropiedadesmvendedor(int id){
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesmvendedor(?)}");
            call.setInt(1, id);
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }
    
    public ResultSet getAgentes() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getagentes()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }

    public ResultSet getVendedores() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getvendedores()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
           
        }
        return rs;
    }

    public ResultSet getCompradores() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getcompradores()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
            
        }
        return rs;
    }
    
    public ResultSet getBitacora() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getbitacora()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
          
        }
        return rs;
    }

    public ResultSet getPropiedadesM() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesm()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getPropiedadesV() {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadesv()}");
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet getpropiedadescomprador(int id) {
        ResultSet rs = null;
        try {
            CallableStatement call = connect.prepareCall("{call getpropiedadescomprador(?)}");
            call.setInt(1, id);
            call.executeQuery();
            rs = call.getResultSet();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void ventasAgente(JTable table) {

        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("Numero de Identidad");
        model.addColumn("Nombre");
        model.addColumn("Cantidad Vendida");

        table.setModel(model);

        String[] data = new String[3];
        String sql = "SELECT * FROM vw_ventasxagente";

        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                model.addRow(data);
            }

            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }

    }

    public void ventasVendedor(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("Numero de Identidad");
        model.addColumn("Nombre");
        model.addColumn("Cantidad Vendida");

        table.setModel(model);
        String[] data = new String[3];
        String sql = "SELECT * FROM vw_ventasxvendedor";

        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                model.addRow(data);
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }

    }

    public void comprasComprador(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("Numero de Identidad");
        model.addColumn("Nombre");
        model.addColumn("Cantidad Comprada");

        table.setModel(model);

        String[] data = new String[3];
        String sql = "SELECT * FROM vw_comprasxcomprador";

        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);

                model.addRow(data);
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }
    }

    public void ventasUbicacion(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("Ciudad");
        model.addColumn("Cantidad Vendida");

        table.setModel(model);

        String[] data = new String[2];
        String sql = "SELECT * FROM vw_ventasxubicacion";

        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);

                model.addRow(data);
            }

            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error");
        }

    }

    public void ventasPrecio(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("Precio");
        model.addColumn("Cantidad");

        table.setModel(model);

        String[] data = new String[2];
        String sql = "SELECT * FROM vw_ventasxprecio";
        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);

                model.addRow(data);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }

    public void ventasCaracteristicas(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.addColumn("ID de Propiedad");
        model.addColumn("Nombre");
        model.addColumn("Cantidad de Dormitorios");
        model.addColumn("Tiene Piscina");
        table.setModel(model);

        String[] data = new String[5];
        String sql = "SELECT * FROM vw_ventasxcaracteristicas";
        try {
            //connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                model.addRow(data);
            }
            table.setModel(model);
        } catch (Exception e) {
        }
    }

    public boolean agentePromedioAnual(JTable table, int year) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        try {
            CallableStatement call = connect.prepareCall("{call vwp_agentexpromedioAnual(?)}");
            call.setInt(1,year);
            ResultSet rs = call.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                modelo.addRow(row);
            }
            table.setModel(modelo);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean agenteValorVenta(JTable table, int year) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        try {
            CallableStatement call = connect.prepareCall("{call vwp_agentexvalorVenta(?)}");
            call.setInt(1,year);
            ResultSet rs = call.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDouble(3);
                row[3] = rs.getInt(4);
                modelo.addRow(row);
                
            }
            
            table.setModel(modelo);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

}
