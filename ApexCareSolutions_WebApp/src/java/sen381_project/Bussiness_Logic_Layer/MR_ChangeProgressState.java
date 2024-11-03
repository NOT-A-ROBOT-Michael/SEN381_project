/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;
import sen381_project.Data_Layer.MR_SqlMarkDone;

/**
 *
 * @author iyesme
 */
public class MR_ChangeProgressState {
    //change the state of the progress
    // this can only change the state from "completed by technician" to completed
    MR_SqlMarkDone sqlmd = new MR_SqlMarkDone();
    
    public void markDone(Integer ServiceID){
        //call method in data access layer to update progress to done
        sqlmd.updatePriorityComplete(ServiceID);
    }
}
