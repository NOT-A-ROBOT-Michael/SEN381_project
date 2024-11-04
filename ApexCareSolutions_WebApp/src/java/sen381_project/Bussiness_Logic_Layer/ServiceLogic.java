// The following handles the logic for the UserServlet, to display the basic details on load of the client home page
package sen381_project.Bussiness_Logic_Layer;

import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.CSA_Service;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Service;
import sen381_project.Data_Layer.ConnectionProvider;


public class ServiceLogic
{
    
    ConnectionProvider cp = new ConnectionProvider();
    
    // The following gets the service details and returns the basic details that will be displayed on the client's home page
    public ArrayList<String[]> serviceDetails(Integer clientID) throws Exception
    {
        // Use to get access to Client_Service methods
        Client_Service cs = new Client_Service();
        // Retrieves the services from the database
        ArrayList<Client_Service> clientServices = cp.getClientService(clientID);
        
        return cs.getBasicDetails(clientServices);
    }
    
    // The following gets the service details and returns the basic details that will be displayed on the client's home page
    public ArrayList<String[]> serviceDetails_CSA(Integer csaID) throws Exception
    {
        // Use to get access to Client_Service methods
        CSA_Service csas = new CSA_Service();
        // Retrieves the services from the database
        ArrayList<CSA_Service> csaServices = cp.getCSAService(csaID);
        
        return csas.getBasicDetails(csaServices);
    }
    
}
