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
public class ClientDetails {
    private Integer serviceID;
    private String Country;
    private String State;
    private String City;
    private String Street;
    private Date date;
    private String Name;
    private String Surname;
    private String Description;
    private String Number;
    
    public ClientDetails(){}
    
    public ClientDetails (Integer serviceID,String Country, String State,String City,String Street,Date date, String Name,String Surname,String Description, String Number )
    {
        this.serviceID = serviceID;
        this.Country = Country;
        this.State = State;
        this.City = City;
        this.Street = Street;
        this.date = date;
        this.Name = Name;
        this.Surname = Surname;
        this.Description = Description;
        this.Number = Number;
        
    }
    public Integer getServiceID(){
        return this.serviceID;
    }
    public ClientDetails setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
    public String getCountry(){
        return this.Country;
    }
    public ClientDetails setCountry(String Country){
        this.Country = Country;
        return this;
    }
    public String getState(){
        return this.State;
    }
    public ClientDetails setState(String State){
        this.State = State;
        return this;
    }
    public String getCity(){
        return this.City;
    }
    public ClientDetails setCity(String City){
        this.City = City;
        return this;
    }
    public String getStreet(){
        return this.Street;
    }
    public ClientDetails setStreet(String Street){
        this.Street = Street;
        return this;
    }
    public Date getdate(){
        return this.date;
    }
    public ClientDetails setdate(Date date){
        this.date = date;
        return this;
    }
    public String getName(){
        return this.Name;
    }
    public ClientDetails setName(String Name){
        this.Name = Name;
        return this;
    }
    public String getSurname(){
        return this.Surname;
    }
    public ClientDetails setSurname(String Surname){
        this.Surname = Surname;
        return this;
    }
    public String getDescription(){
        return this.Description;
    }
    public ClientDetails setDescription(String Description){
        this.Description = Description;
        return this;
    }
    public String getNumber(){
        return this.Number;
    }
    public ClientDetails setNumber(String Number){
        this.Number = Number;
        return this;
    }
}
