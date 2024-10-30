// The following is used to handle the logic for the client profile page and other pages that revolve around it
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;


public class ClientProfileLogic
{
    ConnectionProvider cp = new ConnectionProvider();
    
    // Gets the client's details
    public String[] clientDetails(Integer clientID) throws Exception
    {
        Client_Details cd = cp.getClientDetails(clientID);
        return cd.getClientInfo();
    }
    
    // Gets the client's password
    public String clientPass(Integer clientID) throws Exception
    {
        Client_Details cd = cp.getClientDetails(clientID);
        return cd.getClientPass();
    }
    
    // Gets the client's address information
    public String[] clientAddress(Integer addressID) throws Exception
    {
        Address a = cp.getAddress(addressID);
        
        return a.getAddress();
    }
    
    // Update client details
    public void updateClientDetails(Integer clientIDNum, String firstName, String lastName, String phoneNumber, String email) throws Exception
    {
        cp.updateClientInfo(clientIDNum, firstName, lastName, phoneNumber, email);
    }
    
    // Update client address
    public void updateClientAddress(Integer addressID, String country, String state, String city, String streetName) throws Exception
    {
        cp.updateClientInfo(addressID, country, state, city, streetName);
    }
    
    // The following detemines if the client's password should be updated or not
    public String changePass(Integer clientID, String enteredOldPass, String currentPass, String enteredNewPass) throws Exception
    {
        // The following check if the values that the user entered are empty, correct or incorrect
        if(enteredOldPass.isBlank() || enteredNewPass.isBlank())
        {
            // Empty Password
            System.out.println("!Info!----- Password inputs are empty -----!Info!");
            return "Empty";
        }
        else if(enteredOldPass.equals(currentPass))
        {
            // Correct Password
            System.out.println("!Info!----- User entered the correct password -----!Info!");
            cp.updateClientPass(enteredNewPass, clientID);
            return "Valid";
        }
        else
        {
            // Incorrect Password
            System.out.println("!Info!----- User entered the incorrect password -----!Info!");
            return "Invalid";
        }
    }
}
