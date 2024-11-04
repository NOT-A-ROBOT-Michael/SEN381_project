/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import businesslogiclayer.object.AR_ContactPage;
import datalayer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class AR_ContactPageThing {
    ConnectionProvider cpt = new ConnectionProvider();
    AR_ContactPage contact = new AR_ContactPage();
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
    public void InsertIntoServiceQuery(Integer ServiceID,Integer TechID, String Query)
    {
        try{
        cpt.InsertContactPageQuery(ServiceID, TechID, Query);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
