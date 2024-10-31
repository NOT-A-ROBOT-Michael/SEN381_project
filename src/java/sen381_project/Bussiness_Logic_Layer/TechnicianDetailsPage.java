/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import java.util.ArrayList;
import sen381_project.Data_Layer.ConnectionProvider;
import sen381_project.Bussiness_Logic_Layer.Objects.TechnicianDetails;
/**
 *
 * @author arlow
 */
public class TechnicianDetailsPage {
     final ConnectionProvider cpt = new ConnectionProvider();
    
     
     public TechnicianDetails getTechnicianDetails(){
         
         try
         {
             //Initialize
            TechnicianDetails TechDetails;

            //run sql code and get technician tasks from database
            TechDetails = cpt.ViewTechDetails();

            //return the object
            return TechDetails;
         }
         catch (Exception e)
         {
             System.out.println("Something went wrong while trying get the technician's details: " + e.getMessage());
         }
         
         return null;
     }
     
         
     
    
     
     
     
}


