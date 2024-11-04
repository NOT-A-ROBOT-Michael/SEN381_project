/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class TechnicianDetails {
    private Integer TechID;
    private String Name;
    private String Surname;
    private String Email;
    private String Number;
    
   public TechnicianDetails(){}
   
   public TechnicianDetails(Integer TechID,String Name,String Surname,String Email,String Number)
   {
    this.TechID = TechID;
    this.Name = Name;
    this.Surname = Surname;
    this.Email = Email;
    this.Number = Number;
            
   }
   
   public Integer getTechID(){
        return this.TechID;
    }
    
    public TechnicianDetails setTechID(Integer TechID){
        this.TechID = TechID;
        return this;
    }
    
     public String getName(){
        return this.Name;
    }
    
    public TechnicianDetails setName(String Name){
        this.Name = Name;
        return this;
    }
    
    public String getSurname(){
        return this.Surname;
    }
    
    public TechnicianDetails setSurname(String Surname){
        this.Surname = Surname;
        return this;
    }
    public String getEmail(){
        return this.Email;
    }
    
    public TechnicianDetails setEmail(String Email){
        this.Email = Email;
        return this;
    }
    public String getNumber(){
        return this.Number;
    }
    
    public TechnicianDetails setNumber(String Number){
        this.Number = Number;
        return this;
    }
}
