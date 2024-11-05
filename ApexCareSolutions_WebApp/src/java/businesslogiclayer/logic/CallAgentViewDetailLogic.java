
package businesslogiclayer.logic;

import datalayer.ConnectionProvider;
import businesslogiclayer.object.CSA_Service;

public class CallAgentViewDetailLogic {
    
    public String[] getCSA_ServiceDetails(Integer serviceID) throws Exception
    {
        ConnectionProvider cp = new ConnectionProvider();
        
        CSA_Service csa_Service = cp.getServiceForCSA_Details(serviceID);
        
        return csa_Service.getServiceDetails();
    }
    
}
