// The following page includes the bussiness logic of the website
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Service;


public class Logic {
    
    // The following determines if a user will be allowed to log into the account
    public String[] setLoginStatus(String userEmail, String userPass)
    {
        try
        {
            // Creates an object of the ConnectionProvider class
            ConnectionProvider connProv = new ConnectionProvider();
            
            Client_Details cd = new Client_Details();
            
            // Get the user details if they exist
            String[] user = connProv.checkUserLoginDetails(userEmail, userPass);
            
            // Checks if the user details exists
            if(user != null)
            {
                // If the user has details they will be given access to the account
                System.out.println("Give the user access to the account.");
                return user;
                
            }
            else
            {
                // Indication that the
                System.out.println("Incorrect Email or Password");
                
            }
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to verify user: " + e.getMessage());
        }
        
        return null;
    }
    
    public ArrayList<String[]> getServices(Integer cID)
    {
        try
        {
            // Creates an object of the ConnectionProvider class
            ConnectionProvider connProv = new ConnectionProvider();
            
            ArrayList<Client_Service> services = connProv.getClientService(cID);
            
            Client_Service cs = new Client_Service();
            
            ArrayList<String[]> basicServices = cs.getBasicDetails(services);
            
            if(basicServices != null)
            {
                return basicServices;
            }
            else
            {
                System.out.println("No services found for the user");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: Getting services, logic: " + e.getMessage());
        }
        
        return null;
        
    }
    
}
