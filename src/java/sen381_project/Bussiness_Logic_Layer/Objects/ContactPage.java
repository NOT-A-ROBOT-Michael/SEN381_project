/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class ContactPage {
    private Integer serviceID;
    private String Query;
    
    public ContactPage(){}
    
    public ContactPage (Integer serviceID, String Query)
    {
        this.serviceID = serviceID;
        this.Query = Query;
    }
    public String getQueryText(){
        return this.Query;
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public ContactPage setQueryText(String Query){
        this.Query = Query;
        return this;
    }
    
    public ContactPage setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
}
