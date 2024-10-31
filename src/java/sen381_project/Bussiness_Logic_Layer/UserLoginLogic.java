// The following handles the logic when user log into the web application
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Data_Layer.ConnectionProvider;

public class UserLoginLogic
{
    // Creates an object of the ConnectionProvider class
    ConnectionProvider cp = new ConnectionProvider();
    
    public String[] getLoginInformation(String email, String pass) throws Exception
    {
        
        // Get the user details if they exist
        String[] user = cp.checkUserLoginDetails(email, pass);
        
        // Checks if the user details exists
        if(user != null)
        {
            // If the user has details they will be given access to the account
            System.out.println("!Info!----- Give the user access to the account. -----!Info!");
            return user;

        }
        else
        {
            // Indication that the
            System.out.println("!Info!----- Incorrect Email or Password. -----!Info!");

        }
        
        return null;
    }
}
