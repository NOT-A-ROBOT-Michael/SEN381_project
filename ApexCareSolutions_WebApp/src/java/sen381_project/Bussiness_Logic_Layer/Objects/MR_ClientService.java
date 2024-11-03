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
public class MR_ClientService {
    public Integer serviceID;
    public String name, surname, phone, email, description;
    protected MR_ClientAddress address;
    //String country, state, city, street;
    private Date requestedDate;
    public String serviceTitle;
    public String priority;
    public String status;
    
    
    public MR_ClientService(){}
    
    public MR_ClientService(Integer serviceID, String name, String surname, String phone,
            String email, String description, String country, String state, String city,
            String street, java.sql.Date requestedDate, String serviceTitle, String priority, String status){
        
        this.serviceID = serviceID;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.address = new MR_ClientAddress(country, state, city, street);
        this.requestedDate = (Date)requestedDate;
        this.serviceTitle = serviceTitle;
        this.priority = priority;
        this.status = status;
        
    }
    
    public MR_ClientService setClientAddress(MR_ClientAddress address){
        this.address = address;
        return this;
    }
    
    public MR_ClientService setClientAddress(String country, String state, String city, 
            String street){
        this.address = new MR_ClientAddress(country, state, city, street);
        return this;
    }
    
    public MR_ClientAddress getClientAddress(){
        return this.address;
    }
    
    public java.sql.Date getReqDateSQL(){
        return (java.sql.Date) requestedDate;
    }
    
    public Date getReqDate(){
        return requestedDate;
    }
    
    public MR_ClientService setReqDate(java.sql.Date requestedDate){
        this.requestedDate = (Date) requestedDate;
        return this;
    }
    
    public MR_ClientService setReqDate(Date requestedDate){
        this.requestedDate = requestedDate;
        return this;
    }
    
}
