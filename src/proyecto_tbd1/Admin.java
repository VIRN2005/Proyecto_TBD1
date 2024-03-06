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
    
    public Admin() {
        url = "jdbc:mariadb://localhost:3306/agencia_bienes_raices";
        user = "root";
        password = "Emilio2606";
        try{
            connect = DriverManager.getConnection(url, user, password);
            setup();
        }catch(Exception e){
            
        }
                
    }
    
    public void setup(){
        String path = "./setupDB.sql";
        try{
            FileReader rd = new FileReader(new File(path));
            BufferedReader bf = new BufferedReader(rd);
            ScriptRunner sc = new ScriptRunner(connect);
            sc.runScript(bf);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
