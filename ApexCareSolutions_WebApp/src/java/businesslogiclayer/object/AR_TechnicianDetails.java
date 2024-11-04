/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author arlow
 */
public class AR_TechnicianDetails {
    private Integer TechID;
    private String Name;
    private String Surname;
    private String Email;
    private String Number;
    
   public AR_TechnicianDetails(){}
   
   public AR_TechnicianDetails(Integer TechID,String Name,String Surname,String Email,String Number)
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
    
    public AR_TechnicianDetails setTechID(Integer TechID){
        this.TechID = TechID;
        return this;
    }
    
     public String getName(){
        return this.Name;
    }
    
    public AR_TechnicianDetails setName(String Name){
        this.Name = Name;
        return this;
    }
    
    public String getSurname(){
        return this.Surname;
    }
    
    public AR_TechnicianDetails setSurname(String Surname){
        this.Surname = Surname;
        return this;
    }
    public String getEmail(){
        return this.Email;
    }
    
    public AR_TechnicianDetails setEmail(String Email){
        this.Email = Email;
        return this;
    }
    public String getNumber(){
        return this.Number;
    }
    
    public AR_TechnicianDetails setNumber(String Number){
        this.Number = Number;
        return this;
    }
}
