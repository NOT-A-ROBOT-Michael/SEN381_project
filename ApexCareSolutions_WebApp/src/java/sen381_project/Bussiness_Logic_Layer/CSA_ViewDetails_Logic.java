
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Data_Layer.ConnectionProvider;
import sen381_project.Bussiness_Logic_Layer.Objects.CSA_Service;

public class CSA_ViewDetails_Logic {
    
    public String[] getCSA_ServiceDetails(Integer serviceID) throws Exception
    {
        ConnectionProvider cp = new ConnectionProvider();
        
        CSA_Service csa_Service = cp.getServiceForCSA_Details(serviceID);
        
        return csa_Service.getServiceDetails();
    }
    
}
