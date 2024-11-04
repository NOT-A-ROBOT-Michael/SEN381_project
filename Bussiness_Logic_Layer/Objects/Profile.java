/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class Profile {
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
    
    public Profile setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    public Profile setName(String Name){
        this.Name = Name;
        return this;
    }
    public Profile setSurname(String Surname){
        this.Surname = Surname;
        return this;
    }
    public Profile setPhone(String Phone){
        this.Phone = Phone;
        return this;
    }
    public Profile setEmail(String Email){
        this.Email = Email;
        return this;
    }
    public Profile setPassword(String Password){
        this.Password = Password;
        return this;
    }
}
