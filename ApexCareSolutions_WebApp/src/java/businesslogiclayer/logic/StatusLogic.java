/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import datalayer.ConnectionProvider;
import businesslogiclayer.object.AR_Status;
/**
 *
 * @author arlow
 */
public class StatusLogic {
    ConnectionProvider cpt = new ConnectionProvider();
    AR_Status status = new AR_Status();
    public AR_Status CreateStatusObject(Integer serviceID, String StatusUpdate)
    {
    
    status.setServiceID(serviceID).setStatusUpdate(StatusUpdate);
    
    
    return status;
    }
    public void updateStatus(Integer ServiceID, String StatusUpdate)
    {
        try
        {
            CreateStatusObject(ServiceID,StatusUpdate);
            cpt.UpdateStatus(status.getStatusUpdate(),status.getServiceID());
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to update the techniciam's status: " + e.getMessage());
        }
        
    }
}
