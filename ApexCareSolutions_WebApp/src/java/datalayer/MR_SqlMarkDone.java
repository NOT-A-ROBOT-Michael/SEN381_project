/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import businesslogiclayer.object.MR_ClientQuery;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author iyesme
 */
public class MR_SqlMarkDone {
    
    private Connection conn;
    
    public MR_SqlMarkDone() {
        
        try {
            dbConnection connection = new dbConnection();
        
            this.conn = connection.getConnection();
        } catch (Exception e) {
            System.err.println("Connection error: "+e.getMessage());
        }
        
    }
    
    // this will only change the Priotity to complete
    
    public void updatePriorityComplete(Integer serviceID){
        String updateSQL = "UPDATE public.\"Services\"" +
        "SET \"Priority\" = ?" +
        "WHERE \"ServiceID\" = ?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {         
            
            statement.setString(1, "Completed");
            statement.setInt(2, serviceID);
            
            int rowsAffected = statement.executeUpdate();
            System.out.println("Success, updated rows: " + rowsAffected);
            
            
        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
        }
    }
    
}
