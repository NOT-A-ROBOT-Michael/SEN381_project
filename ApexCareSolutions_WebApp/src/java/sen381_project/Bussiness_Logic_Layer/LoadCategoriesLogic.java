// The following handles the logic for the LoadCategoriesServlet
package sen381_project.Bussiness_Logic_Layer;

import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Data_Layer.ConnectionProvider;


public class LoadCategoriesLogic
{
    ConnectionProvider cp = new ConnectionProvider();
    
    // Returns a String array of the client's address
    public String[] clientAddress(Integer addressID) throws Exception
    {
        Address a = cp.getAddress(addressID);
        
        return a.getAddress();
    }
    
    // Returns a list of all the service types offered by the company
    public ArrayList<String> getServiceTypes() throws Exception
    {
        return cp.getTypeOfServices();
    }
}
