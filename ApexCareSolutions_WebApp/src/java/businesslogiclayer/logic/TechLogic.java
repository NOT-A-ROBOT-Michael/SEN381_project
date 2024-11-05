/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;
import java.util.ArrayList;
import businesslogiclayer.object.AR_TechnicianTask;
import datalayer.ConnectionProvider;
/**
 *
 * @author arlow
 */
public class TechLogic {
   ConnectionProvider cpt = new ConnectionProvider();
     /// Die een het nie n class nodig nie. Hoe kan die file dan die connection provider roep????
     
     public ArrayList<String[]> getTechnicians(String email){
         try
         {
             //Initialize
            ArrayList<AR_TechnicianTask> tasks;
            
            ArrayList<String[]> returnedTask = new ArrayList<>();

            //run sql code and get technician tasks from database
            tasks = cpt.GetTechTasks(email);
            
            for(var item : tasks)
            {
                returnedTask.add(item.getTechTask());
            }

            //return the object
            return returnedTask;
         }
         catch (Exception e)
         {
             System.out.println("Something went wrong while trying to get the technician's information: " + e.getMessage());
         }
         
         return null;
    }
}
