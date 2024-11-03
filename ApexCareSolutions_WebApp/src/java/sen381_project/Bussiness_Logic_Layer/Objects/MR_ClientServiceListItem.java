/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author iyesme
 */
public class MR_ClientServiceListItem {
    // declare all the fields on the Client service list categories
    
    protected Integer clientID;
    protected Integer serviceID;
    //protected Integer technicianID;
    protected String technicianName;
    protected String status;
    protected String serviceTitle;
    
    
    //constructors
    public MR_ClientServiceListItem(){}
    
    public MR_ClientServiceListItem(Integer clientID, Integer serviceID, String technicianName, String status, String serviceTitle){
        this.clientID = clientID;
        this.serviceID = serviceID;
        this.technicianName = technicianName;
        this.status = status;
        this.serviceTitle = serviceTitle;
    }
    
    //set builder functions    
    public MR_ClientServiceListItem SetClientID(Integer clientID){
        this.clientID = clientID;
        return this;
    }
    
    public MR_ClientServiceListItem SetServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
    public MR_ClientServiceListItem SetTechnicianName(String technicianName){
        this.technicianName = technicianName;
        return this;
    }
    
    public MR_ClientServiceListItem SetStatus(String status){
        this.status = status;
        return this;
    }
    
    public MR_ClientServiceListItem SetServiceTitle(String serviceTitle){
        this.serviceTitle = serviceTitle;
        return this;
    }
    
    public Integer GetServiceID(){
        return this.serviceID;
    }
    
    public Integer GetClientID(){
        return this.clientID;
    }
    
    public String GetTechnicianName(){
        return this.technicianName;
    }
    
    public String GetStatus(){
        return this.status;
    }
    
    public String getServiceTitle(){
        return this.serviceTitle;
    }
    
}
