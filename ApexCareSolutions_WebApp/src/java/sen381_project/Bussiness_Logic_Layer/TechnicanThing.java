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
     
     public ArrayList<TechnicianTask> getTechnicians(String email){
         try
         {
             //Initialize
            ArrayList<TechnicianTask> tasks;

            //run sql code and get technician tasks from database
            tasks = cpt.GetTechTasks(email);

            //return the object
            return tasks;
         }
         catch (Exception e)
         {
             System.out.println("Something went wrong while trying to get the technician's information: " + e.getMessage());
         }
         
         return null;
    }
}
