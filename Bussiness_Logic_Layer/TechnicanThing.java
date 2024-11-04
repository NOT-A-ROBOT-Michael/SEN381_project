/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.TechnicianTask;
import sen381_project.Data_Layer.ConnectionProvider;
/**
 *
 * @author arlow
 */
public class TechnicanThing {
   ConnectionProvider cpt = new ConnectionProvider();
     /// Die een het nie n class nodig nie. Hoe kan die file dan die connection provider roep????
     
     public ArrayList<String[]> getTechnicians(String email){
         try
         {
             //Initialize
            ArrayList<TechnicianTask> tasks;
            
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
