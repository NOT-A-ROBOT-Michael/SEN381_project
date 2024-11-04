/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;

//import sql
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import array list
import java.util.ArrayList;

//import objects
import businesslogiclayer.object.MR_ClientService;
import businesslogiclayer.object.MR_ClientServiceListItem;

//import dbConnection
/**
 *
 * @author iyesme
 */
public class MR_SqlClientServices {
    private Connection conn;
    
    public MR_SqlClientServices(){
        try {
            dbConnection connection = new dbConnection();
            this.conn = connection.getConnection();
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
    
    //function that returns an array list of customer services using customerID
    public ArrayList<MR_ClientServiceListItem> GetClientServicesList(Integer clientID){
        ArrayList<MR_ClientServiceListItem> ClientServiceList = new ArrayList<>();
        String query = "SELECT \"ServiceID\", \"Status\", \"Tech_Name\", \"Service_Title\" " +
                "FROM public.\"ClientDetailsView\" where \"ClientID\" = ?";
        
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            //replace ?
            statement.setInt(1, clientID);
            
            //execute statement
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                 MR_ClientServiceListItem item = new MR_ClientServiceListItem();
                 item.SetClientID(clientID)
                         .SetServiceID(result.getInt("ServiceID"))
                         .SetStatus(result.getString("Status"))
                         .SetTechnicianName(result.getString("Tech_Name"))
                         .SetServiceTitle(result.getString("Service_Title"));
                         
                //add to list
                ClientServiceList.add(item);
                
            }
            //return list
            return ClientServiceList;
            
        } catch (SQLException e) {
            System.out.println("Select error: "+e.getMessage());
        }
        return ClientServiceList;        
    }
    
    
    //function that will return a client service details
    public MR_ClientService GetClientServiceDetails(Integer serviceID){
        //Initailize
        String query = "SELECT \"First_Name\", \"Last_Name\", \"Phone_Number\", \"Email\", "+
                "\"Description\", \"Country\", \"State\", \"City\", \"Street_Name\", "+
                "\"Requested_Date\", \"Service_Title\", \"Priority\", \"Status\"" +
                "FROM public.\"ClientDetailsView\" where \"ServiceID\" = ? ";
        
             
        //try
        try (PreparedStatement statement = conn.prepareStatement(query)){
            MR_ClientService details;
            
            //change ?
            statement.setInt(1, serviceID);
            
            //excecute statement
            ResultSet result = statement.executeQuery();
            result.next();
            
            //assign variables
            String name = result.getString("First_Name");
            String surname = result.getString("Last_Name");
            String phone = result.getString("Phone_Number");
            String email = result.getString("Email");
            String description = result.getString("Description");
            String country = result.getString("Country"); 
            String state = result.getString("State");
            String city = result.getString("City");
            String street = result.getString("Street_Name");
            java.sql.Date requestedDate = result.getDate("Requested_Date");
            String serviceTitle = result.getString("Service_Title");
            String priority = result.getString("Priority");
            String status = result.getString("Status");
            
            //assign object
            details  = new MR_ClientService(serviceID, name, surname, phone, email, 
                    description, country, state, city, street, requestedDate, 
                    serviceTitle, priority, status);
            
            //return object
            return details;
        } catch (SQLException e) {
            System.out.println("Select error: "+ e.getMessage());
        }
        
        return null;
        
    }
    
}
