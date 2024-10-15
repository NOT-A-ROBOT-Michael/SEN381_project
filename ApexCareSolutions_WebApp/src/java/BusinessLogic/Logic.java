// The following page includes the bussiness logic of the website
package BusinessLogic;

import DataAccess.ConnectionProvider;


public class Logic {
    
    // The following determines if a user will be allowed to log into the account
    public String[] setLoginStatus(String userEmail, String userPass)
    {
        try
        {
            // Creates an object of the ConnectionProvider class
            ConnectionProvider connProv = new ConnectionProvider();
            
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
    
}
