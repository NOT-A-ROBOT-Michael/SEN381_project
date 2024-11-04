/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.AR_ClientDetails;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class AR_ClientDetailsThing {
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
