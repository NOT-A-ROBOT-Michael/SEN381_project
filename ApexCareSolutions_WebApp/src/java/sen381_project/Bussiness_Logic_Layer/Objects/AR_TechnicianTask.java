/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

import java.util.Date;

/**
 *
 * @author arlow
 */
public class AR_TechnicianTask {
    private Integer serviceID;
    private String priority;
    private String status;
    private Date requestedDate;   
    private String Email;
    
    public AR_TechnicianTask(){}
    
    public AR_TechnicianTask(String email)
    {
        this.Email = email;
    }
    
    public AR_TechnicianTask(Integer serviceID, String priority, String status, 
            Date requestedDate, String email)
    {
        
        this.Email = email;
        this.priority = priority;
        this.status = status;
        this.requestedDate = requestedDate;
        this.serviceID = serviceID;
    }
    
    public String[] getTechTask()
    {
        return new String[] {this.priority, this.serviceID.toString(), this.status, this.requestedDate.toString()};
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public AR_TechnicianTask setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
    public String getPriority()
    {
        return this.priority;
    }
    
    public AR_TechnicianTask setPriority(String priority){
        this.priority = priority;
        return this;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    public AR_TechnicianTask setStatus(String status){
        this.status = status;
        return this;
    }
    
    public Date getRequestedDate()
    {
        return this.requestedDate;
    }
    
    public AR_TechnicianTask setRequestedDate(Date requestedDate){
        this.requestedDate = requestedDate;
        return this;
    }
    
    public String getEmail()
    {
        return this.Email;
    }
    
    public AR_TechnicianTask setEmail(String email){
        this.Email = email;
        return this;
    }
        
    
    @Override
    public String toString()
    {
        return "--------------Service ID: " + serviceID;
    }
    
    
}
