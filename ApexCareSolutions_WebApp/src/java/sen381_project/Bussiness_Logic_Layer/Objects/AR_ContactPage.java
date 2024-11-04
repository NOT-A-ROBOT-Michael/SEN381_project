/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author arlow
 */
public class AR_ContactPage {
    private Integer serviceID;
    private String Query;
    
    public AR_ContactPage(){}
    
    public AR_ContactPage (Integer serviceID, String Query)
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
    
    public AR_ContactPage setQueryText(String Query){
        this.Query = Query;
        return this;
    }
    
    public AR_ContactPage setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
}
