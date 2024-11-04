/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class AR_Status 
{
    private Integer serviceID;
    private String StatusUpdate;
    
    public AR_Status(){}
    
    public AR_Status(Integer serviceID, String txtStatus)
    {
        this.serviceID = serviceID;
        this.StatusUpdate = txtStatus;
    }
    
     public String getStatusUpdate(){
        return this.StatusUpdate;
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public AR_Status setStatusUpdate(String noteText){
        this.StatusUpdate = noteText;
        return this;
    }
    
    public AR_Status setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
}
