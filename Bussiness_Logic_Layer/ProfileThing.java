/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import sen381_project.Bussiness_Logic_Layer.Objects.Profile;
import sen381_project.Data_Layer.ConnectionProvider;

/**
 *
 * @author arlow
 */
public class ProfileThing {
    ConnectionProvider cpt = new ConnectionProvider();
    
    public Profile CreateNoteObject(Integer serviceID,String Phone,String Password)
    {
        Profile profile = new Profile();
        
        profile.setServiceID(serviceID).setPhone(Phone).setPassword(Password);
        
        return profile;
    }
    public void ExecuteProfile(Integer serviceID,String Phone,String Password)
    {
        try
        {
            Profile profile = CreateNoteObject( serviceID, Phone,  Password);
            cpt.UpdateProfile(profile.getserviceID(), profile.getPhone(), profile.getPassword());
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to create a profile: " + e.getMessage());
        }
     
    }
}
