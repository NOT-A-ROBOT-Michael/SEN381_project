/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Data_Layer;

import java.sql.*;
import sen381_project.Bussiness_Logic_Layer.Objects.ServiceAgent;

/**
 *
 * @author morne
 */
public class ServiceAgentDAO {
    private ConnectionProvider cp;
    
    public ServiceAgentDAO(){
        this.cp = new ConnectionProvider();
    }
    
    public boolean serviceAgentExists(String email) throws SQLException, ClassNotFoundException{
        //checks weather the service agent exists with the email
        String query = "SELECT 1 FROM \"Call Service Agent\" WHERE \"Email\" = ?";
        
        try (Connection con = cp.getCon();
             PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            return rs.next(); //returns true if the record is found
        }
    }
    
    public ServiceAgent registerServiceAgent(ServiceAgent serviceAgent) throws ClassNotFoundException{
        String query = "INSERT INTO \"Call Service Agent\" (\"First_Name\", \"Last_Name\", \"Email\", \"Phone_Number\", \"Password\") VALUES (?, ?, ?, ?, ?) RETURNING \"CallServiceAgentID\", \"First_Name\", \"Last_Name\", \"Email\", \"Phone_Number\"";
        
        try (Connection con = cp.getCon();
             PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setString(1, serviceAgent.getName());
            ps.setString(2, serviceAgent.getSurname());
            ps.setString(3, serviceAgent.getEmail());
            ps.setString(4, serviceAgent.getPhoneNumber());
            ps.setString(5, serviceAgent.getPassword());

            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                // \"CallServiceAgentID\", \"First_Name\", \"Last_Name\", \"Email\", \"Phone_Number\"
                Integer serviceAgentID = rs.getInt("CallServiceAgentID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("Phone_Number");
                
                return new ServiceAgent(serviceAgentID, firstName, lastName, email, phoneNumber);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public String retreivePassword(String email) throws ClassNotFoundException{
        String query = "SELECT \"Password\" FROM \"Call Service Agent\" WHERE \"Email\" = ?";
        
        try (Connection con = cp.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("Password");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
