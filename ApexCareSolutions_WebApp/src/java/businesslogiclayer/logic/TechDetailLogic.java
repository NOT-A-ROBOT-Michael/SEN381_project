/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import datalayer.ConnectionProvider;
import businesslogiclayer.object.AR_TechnicianDetails;
/**
 *
 * @author arlow
 */
public class TechDetailLogic {
     final ConnectionProvider cpt = new ConnectionProvider();
    
     
     public AR_TechnicianDetails getTechnicianDetails(String techEmail){
         
         try
         {
             //Initialize
            AR_TechnicianDetails TechDetails;

            //run sql code and get technician tasks from database
            TechDetails = cpt.ViewTechDetails(techEmail);

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


