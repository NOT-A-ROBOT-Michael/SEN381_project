
package businesslogiclayer.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import businesslogiclayer.object.Service;
import datalayer.ConnectionProvider;

public class CSA_HomePage_Logic {
    
    ConnectionProvider cp = new ConnectionProvider();
    
    public void setTechToService(Integer serviceID) throws Exception
    {
        // Get service info
        Service s = cp.getServiceForAddingATech(serviceID);
        
        // Get specialisation type ID
        Integer specialisationID = cp.getSpecial(s.getContractID());

        // Get technician IDs with the specialisation
        
        ArrayList<Integer> technicianID_List = cp.getSpecialTech(specialisationID);

        // Get the number of services that each technician has
        ArrayList<Integer[]> techWithNumOfServices = cp.numOfServicesForTech(technicianID_List);

        // Compare the technicians and add the technician to the service that has the least amount of services
        
        Integer lowestTech = techWithNumOfServices.get(0)[0];
        Integer lowestNumOfServices = techWithNumOfServices.get(0)[1];
        
        for(var selectedTech : techWithNumOfServices)
        {
            if(lowestNumOfServices > selectedTech[1])
            {
                lowestTech = selectedTech[0];
                lowestNumOfServices = selectedTech[1];
            }
        }
        
        // Update service status
        
        LocalDate currentDate = LocalDate.now();
        
        cp.addTechnicianToService(serviceID, lowestTech, currentDate);
        
        cp.updateServiceToOngoing(serviceID);
        
        
        
    }
    
    public void declineService(Integer serviceID) throws Exception
    {
        cp.updateServiceToDeclined(serviceID);
    }
    
}
