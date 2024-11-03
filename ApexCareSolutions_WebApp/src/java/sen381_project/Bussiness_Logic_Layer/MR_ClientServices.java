/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;


import sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientServiceListItem;
import sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientService;
import sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientServiceListItem;
import sen381_project.Data_Layer.MR_SqlClientServices;

/**
 *
 * @author iyesme
 */
import java.util.ArrayList;


public class MR_ClientServices {
    MR_SqlClientServices cs = new MR_SqlClientServices();
    
    
    //tis will display all the services linked to the client
    public ArrayList<MR_ClientServiceListItem> getDisplayClientContracts(Integer clientID){
        //Initialize
        ArrayList<MR_ClientServiceListItem> serviceList;
        
        //run the get function from the data acces layer to get an arraylist of client contrats
        serviceList = cs.GetClientServicesList(clientID);
        
        //return the arraylist
        return serviceList;
    } 
    
    
    //this will retrieve the contract details using the service ID
    //, this will only display what the Client can see
    public MR_ClientService getClientContractDetails(Integer ServiceID){
        //Initialize
        MR_ClientService details;
        
        //run the get function from the data acces layer to get an arraylist of client service details
        details = cs.GetClientServiceDetails(ServiceID);
        
        //return client Service details
        return details;
    }
    
    
}
