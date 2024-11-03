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
public class MR_Survey {
    
    private Integer serviceID;
    private Integer rateTechnician;
    private Integer rateServiceAgent;
    private Integer rateBackEndService;
    private Date requestedDate;
    //String wjaiojfklfjdlfjasdkfjasdklfjklfj;
    
    public MR_Survey(){}
    
    
    public MR_Survey(Integer serviceID, Integer rateTechnician, Integer rateServiceAgent, Integer rateBackEndService, Date requestedDate){        
        this.serviceID = serviceID;
        setRateTechnician(rateTechnician);
        setRateServiceAgent(rateServiceAgent);       
        setRateBackEndService(rateBackEndService);
        this.requestedDate = requestedDate;
    
    }
    
    
    public MR_Survey setServiceID(Integer serviceID){
        this.serviceID = serviceID;
        return this;
    }
    
    public MR_Survey setRateTechnician(Integer rateTechnician){
        if (rateTechnician == null) {
            this.rateTechnician = 0;
            return this;
        }else{
            this.rateTechnician = rateTechnician;
            return this;
        }
    }
    
    public MR_Survey setRateServiceAgent(Integer rateServiceAgent){
        if (rateServiceAgent == null) {
            this.rateServiceAgent = 0;
            return this;
        }else{
            this.rateServiceAgent = rateServiceAgent;
            return this;
        }
    }
    
    public MR_Survey setRateBackEndService(Integer rateBackEndService){
        if (rateBackEndService == null) {
            this.rateBackEndService = 0;
            return this;
        }else{
            this.rateBackEndService = rateBackEndService;
            return this;
        }
    }
    
    public MR_Survey setRequestedDate(Date requestedDate){
        this.requestedDate = requestedDate;
        return this;
    }
    
    public Integer getServiceID(){
        return this.serviceID;
    }
    
    public Integer getRateTechnician(){
        return this.rateTechnician;
    }
    
    public Integer getRateServiceAgent(){
        return this.rateServiceAgent;
    }
    
    public Integer getRateBackEndService(){
        return this.rateBackEndService;
    }
    
    public Date getRequestedDate(){
        return this.requestedDate;
    }
    
}
