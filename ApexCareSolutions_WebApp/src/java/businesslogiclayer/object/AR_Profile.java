/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author arlow
 */
public class AR_Profile {
    private Integer serviceID;
    private String Name;
    private String Surname;
    private String Phone;
    private String Email;
    private String Password;
    
    public Integer getserviceID(){
        return this.serviceID;
    }
    
    public String getName(){
        return this.Name;
    }
    
    public String getSurname(){
        return this.Surname;
    }
    public String getPhone(){
        return this.Phone;
    }
    
    public String getEmail(){
        return this.Email;
    }
    public String getPassword(){
        return this.Password;
    }
    
    public AR_Profile setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    public AR_Profile setName(String Name){
        this.Name = Name;
        return this;
    }
    public AR_Profile setSurname(String Surname){
        this.Surname = Surname;
        return this;
    }
    public AR_Profile setPhone(String Phone){
        this.Phone = Phone;
        return this;
    }
    public AR_Profile setEmail(String Email){
        this.Email = Email;
        return this;
    }
    public AR_Profile setPassword(String Password){
        this.Password = Password;
        return this;
    }
}
