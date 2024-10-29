/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.ClientDetails;
import sen381_project.Data_Layer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class ClientDetailsThing {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public ClientDetails CreateNoteObject(Integer serviceID)
    {
        ClientDetails details = new ClientDetails();
        
        details.setServiceID(serviceID);
        
        return details;
    }
}
