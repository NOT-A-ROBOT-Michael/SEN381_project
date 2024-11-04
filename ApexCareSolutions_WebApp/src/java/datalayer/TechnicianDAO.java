/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;

import java.sql.*;
import java.util.ArrayList;
import businesslogiclayer.object.Specialisation;
import businesslogiclayer.object.Technician;
import businesslogiclayer.object.TechnicianLocation;

/**
 *
 * @author morne
 */
public class TechnicianDAO {
    private ConnectionProvider cp;
    
    public TechnicianDAO(){
        this.cp = new ConnectionProvider();
    }
    
    public boolean technicianExists(String email) throws SQLException, ClassNotFoundException{
        //checks if  technician exists with their email
        String query = "SELECT 1 FROM \"Technician\" WHERE \"Email\" = ?";
        
        try (Connection con = cp.getCon();
            PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            return rs.next(); //returns true if a value is found
        }
    }
    
    public Technician registerTechnician (Technician technician) throws ClassNotFoundException{
        String query = "INSERT INTO \"Technician\" (\"AddressID\", \"First_Name\", \"Last_Name\", \"Email\", \"Password\", \"SpecialisationID\", \"LocationID\", \"Phone_Number\") VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING \"TechnicianID\", \"AddressID\", \"First_Name\", \"Last_Name\", \"Phone_Number\", \"Email\", \"SpecialisationID\", \"LocationID\" ";
        
        //SpecialisationID is named in database with a space in the end
        
        //Query for adding employeeID manually, to be used if database is adapted:
        //String query = "INSERT INTO \"Technician\" (\"TechnicianID\", \"AddressID\", \"First_Name\", \"Phone_Number\", \"Email\", \"Password\", \"SpecialisationID \") VALUES (?, 18, ?, ?, ?, ?, ?)";
        

        System.out.println("Register technician executed");
        
        try (Connection con = cp.getCon();
            PreparedStatement ps = con.prepareStatement(query)){
            
            
            
            ps.setInt(1, technician.getAddressID());
            ps.setString(2, technician.getName());
            ps.setString(3, technician.getSurname());
            ps.setString(4, technician.getEmail());
            ps.setString(5, technician.getPassword());
            ps.setInt(6, technician.getSpecialization());
            ps.setInt(7, technician.getLocationID());
            ps.setString(8, technician.getPhoneNumber());
            
            //No indication for LocationID, so add default value:
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Integer techID = rs.getInt("TechnicianID");
                Integer addressID = rs.getInt("AddressID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String phoneNumber = rs.getString("Phone_Number");
                String email = rs.getString("Email");
                Integer specialID = rs.getInt("SpecialisationID");
                Integer locationID = rs.getInt("LocationID");
                
                return new Technician (techID, addressID, firstName, lastName, phoneNumber, email, specialID, locationID);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return  null;
    }
    
    public String retreivePassword(String email) throws ClassNotFoundException{
        String query = "SELECT \"Password\" FROM \"Technician\" WHERE \"Email\" = ?";
        
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
    
    public ArrayList<Specialisation> getSpecialisations() throws ClassNotFoundException
    {
        String query = "SELECT * FROM \"Specialisation\"";
        
        ArrayList<Specialisation> sp_List = new ArrayList<>();
        
        try(Connection con = cp.getCon(); PreparedStatement psmt = con.prepareStatement(query))
        {
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("!Info!----- Successfully got service type for registration -----!Info!");
                sp_List.add(new Specialisation(rs.getInt("SpecialisationID"), rs.getString("Category")));
            }
            
            return sp_List;
        }
        catch(SQLException e)
        {
            System.out.println("!E!-----(TEchnicianDAO -> getSpecialisations) Error, while trying to get service types: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following is used to add a new address and return its ID
    public Integer setTechLocation(TechnicianLocation location) throws ClassNotFoundException
    {
        // The query inserts a new address into the database and returns its ID
        String query = "INSERT INTO \"Location\" (\"Lat\", \"Lon\") VALUES(?,?) RETURNING \"LocationID\"";
        
        try(Connection conn = cp.getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            float[] locationInfo = location.getLocationNoID();
            
            // Used to store the ID of the created address
            Integer locationID;
            
            psmt.setFloat(1, locationInfo[0]);
            psmt.setFloat(2, locationInfo[1]);
            
            ResultSet rs = psmt.executeQuery();
            
            System.out.println("!Info!----- Successfully added new technician location. -----!Info!");
            
            while(rs.next())
            {
                locationID = rs.getInt("LocationID");
                
                System.out.println("!Info!----- Successfully got the added location ID. -----!Info!");
                
                return locationID;
            }
            
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> setAndGetAddressID) Error, while trying to add a new technician location: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
}
