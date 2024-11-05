/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import businesslogiclayer.object.AR_Profile;
import datalayer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class ProfileLogic {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public AR_Profile CreateNoteObject(Integer serviceID,String Phone,String Password)
    {
        AR_Profile profile = new AR_Profile();
        
        profile.setServiceID(serviceID).setPhone(Phone).setPassword(Password);
        
        return profile;
    }
    public void ExecuteProfile(Integer serviceID,String Phone,String Password)
    {
        try
        {
            AR_Profile profile = CreateNoteObject( serviceID, Phone,  Password);
            cpt.UpdateProfile(profile.getserviceID(), profile.getPhone(), profile.getPassword());
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to create a profile: " + e.getMessage());
        }
     
    }
}
