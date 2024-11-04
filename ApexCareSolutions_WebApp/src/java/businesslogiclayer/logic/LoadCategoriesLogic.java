// The following handles the logic for the LoadCategoriesServlet
package businesslogiclayer.logic;

import java.util.ArrayList;
import businesslogiclayer.object.Address;
import datalayer.ConnectionProvider;


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
