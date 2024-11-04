/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import businesslogiclayer.object.MR_ClientQuery;

/**
 *
 * @author iyesme
 */
public class MR_SqlClientQuery {

    private Connection conn;
    public MR_SqlClientQuery(){
        
        try {
            dbConnection connection = new dbConnection();
        
            this.conn = connection.getConnection();
        } catch (Exception e) {
            System.err.println("Connection error: "+e.getMessage());
        }
        
    }
    
    public void insertClientQuery(MR_ClientQuery clientQuery){
        String updateSQL = "INSERT INTO public.\"Service Query\"(" +
        "\"ServiceID\", \"ClientID\", \"Description\", \"Requested_Date\")" +
        "VALUES (?, (Select \"ClientID\" from  \"ClientDetailsView\" where \"ServiceID\" = ? Limit 1 ), ?, ?);";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {         
            
            statement.setInt(1, clientQuery.serviceID());
            statement.setInt(2, clientQuery.serviceID());
            //statement.setInt(0, clientQuery.clientID());
            statement.setString(3, clientQuery.description());
            statement.setDate(4, (java.sql.Date)clientQuery.requestedDate());
            
            statement.executeUpdate();
            
            System.out.println("Success, inserted rows");
            
            
        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
        }
    }
    
}
