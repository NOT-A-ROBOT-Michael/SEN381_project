/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import businesslogiclayer.object.AR_ClientDetails;
import businesslogiclayer.object.Client_Details;
import datalayer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class ClientDetailLogic {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public Client_Details CreateNoteObject(Integer serviceID)
    {
        try
        {
            Client_Details details;
            details= cpt.getClientDetails(serviceID);
            //details.setServiceID(serviceID);

            return details;
        }
        catch (Exception e)
        {
            System.out.println("Error, while trying to get client details");
        }
        
        return null;
    }
    public AR_ClientDetails CreateNoteObject2(Integer serviceID)
    {
        try{
        
        AR_ClientDetails details;
        details= cpt.ViewClientDetails(serviceID);
        
        return details;
        }catch(Exception e){
            System.out.println("error: " +  e.getMessage());
        }
       

        
        return null;
    }
}
