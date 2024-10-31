
package sen381_project.Data_Layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;

public class AddressDAO{
    private ConnectionProvider cp;
    
    public AddressDAO(){
        this.cp = new ConnectionProvider();
    }
    
    // The following is used to add a new address and return its ID
    public Integer setAndGetAddressID(Address address) throws ClassNotFoundException
    {
        // The query inserts a new address into the database and returns its ID
        String query = "INSERT INTO \"Address\" (\"Country\", \"State\", \"City\", \"Street_Name\") VALUES(?,?,?,?) RETURNING \"AddressID\"";
        
        try(Connection conn = cp.getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            String[] addressInfo = address.getAddressNoID();
            
            // Used to store the ID of the created address
            Integer addressID;
            
            psmt.setString(1, addressInfo[0]);
            psmt.setString(2, addressInfo[1]);
            psmt.setString(3, addressInfo[2]);
            psmt.setString(4, addressInfo[3]);
            
            ResultSet rs = psmt.executeQuery();
            
            System.out.println("!Info!----- Successfully added new address. -----!Info!");
            
            while(rs.next())
            {
                addressID = rs.getInt("AddressID");
                
                System.out.println("!Info!----- Successfully got the added address ID. -----!Info!");
                
                return addressID;
            }
            
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> setAndGetAddressID) Error, while trying to add a new address: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
}
