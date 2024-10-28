/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.ContactPage;
import sen381_project.Data_Layer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class ContactPageThing {
    ConnectionProvider cpt = new ConnectionProvider();
    ContactPage contact = new ContactPage();
    public void/*ContactPage*/ CreateNoteObject(Integer serviceID, String Query)
    {
        
        
        contact.setServiceID(serviceID).setQueryText(Query);
        
      //  return contact;
    }
    public void ExecuteContactPageDetails(Integer serviceID, String Query)
    {
        try
        {
            CreateNoteObject(serviceID,Query);
            cpt.ContactPageProvideQuery(contact.getServiceID(), contact.getQueryText());
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong while trying to execute contact page details: " + e.getMessage());
        }
        
    }
}
