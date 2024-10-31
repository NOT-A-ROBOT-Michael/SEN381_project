/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Data_Layer;

import java.sql.*;
import sen381_project.Bussiness_Logic_Layer.Objects.Client;

/**
 *
 * @author morne
 */
public class ClientDAO {
    private ConnectionProvider cp;
    
    public ClientDAO(){
        this.cp = new ConnectionProvider();
    }    
    
    public boolean clientExists(String email) throws SQLException, ClassNotFoundException{
        //checks if a client exists - returns true/false
        String query = "SELECT 1 FROM \"Client\" WHERE \"Email\" = ?";
        
        try (Connection con = cp.getCon();
             PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            return rs.next(); //returns true if record is found
        }  
    }
    
    public Client registerClient(Client client) throws ClassNotFoundException {
        String query = "INSERT INTO \"Client\" (\"AddressID\", \"First_Name\", \"Last_Name\", \"Phone_Number\", \"Email\", \"Password\") VALUES (?, ?, ?, ?, ?, ?) RETURNING \"ClientID\", \"AddressID\", \"First_Name\", \"Last_Name\", \"Phone_Number\", \"Email\""; //Change the addressID to take a specified value

        try (Connection con = cp.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, client.getAddressID());
            ps.setString(2, client.getName());
            ps.setString(3, client.getSurname());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getPassword()); //consider hashing password

            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Integer clientID = rs.getInt("ClientID");
                Integer addressID = rs.getInt("AddressID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String phoneNumber = rs.getString("Phone_Number");
                String email = rs.getString("Email");
                
                return new Client(clientID, addressID, firstName, lastName, phoneNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public String retreivePassword(String email) throws ClassNotFoundException{
        String query = "SELECT \"Password\" FROM \"Client\" WHERE \"Email\" = ?";
        
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
