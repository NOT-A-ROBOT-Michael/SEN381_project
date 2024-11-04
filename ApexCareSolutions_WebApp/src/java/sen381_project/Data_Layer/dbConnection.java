/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Data_Layer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author iyesme
 */
public class dbConnection {
    
    final String conUrl = "jdbc:postgresql://localhost:5432/ApexCareDB";
    final String conDriver = "org.postgresql.Driver";
    final String conUser = "postgres";
    final String conPassword = System.getenv("post-pwd");
    
    public Connection getConnection(){
        Connection conn = null;
        try {
            // Register the PostgreSQL JDBC driver
            Class.forName(conDriver);
            
            // Establish the connection
            conn = DriverManager.getConnection(conUrl, conUser, conPassword);
            
            System.out.println("Connection to PostgreSQL database established successfully!");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
        }
        
        return conn;
    }
}
