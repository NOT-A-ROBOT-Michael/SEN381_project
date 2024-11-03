/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

import java.util.Date;

/**
 *
 * @author iyesme
 */
public class MR_ClientQuery {
    //private Integer clientID;
    private Integer serviceID;
    private String description;
    private Date requestedDate;
    
    public MR_ClientQuery(){}
    
    
    public MR_ClientQuery(Integer serviceID, String description, Date requestedDate){
        //this.clientID = clientID;
        this.serviceID = serviceID;
        this.description = description;
        this.requestedDate = requestedDate;
    }
        
    
    /*public MR_ClientQuery clientID(Integer clientID){
        this.clientID = clientID;
        return this;
    }
    
    public Integer clientID(){
        return this.clientID;
    }*/
    
    public MR_ClientQuery serviceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
    public Integer serviceID(){
        return this.serviceID;
    }
    
    public MR_ClientQuery description(String description){
        this.description = description;
        return this;
    }
    
    public String description(){
        return this.description;
    }
    
    public MR_ClientQuery requestedDate(Date requestedDate){
        this.requestedDate = requestedDate;
        return this;
    }
    
    public Date requestedDate(){
        return convertDate(this.requestedDate);
    }
    
    private java.sql.Date convertDate(Date input){
        return new java.sql.Date(input.getTime());
    }
    
}
