/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;

import businesslogiclayer.object.MR_Survey;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import datalayer.MR_SqlSurvey;

/**
 *
 * @author iyesme
 */
public class ClientWriteSurveyLogic {
    
    MR_SqlSurvey sqlSur= new MR_SqlSurvey();
    
    public MR_Survey getSurvey(Integer serviceID){
        //get survey from the database, if the survey does not exist it will create a new one
        
        MR_Survey survey;
        Integer[] rateValues;
        
        //get rate values from the database, then assign it to a array
        
        rateValues = sqlSur.getSurveyValues(serviceID);
        
        //check if the record exists and create it if it does not
        if (rateValues[0] == null && rateValues[1] == null && rateValues[2] == null){
            assignZero(rateValues);
            writeSurvey(serviceID, 0, 0, 0);             
        }
        
        survey = createSurveyObject(serviceID, rateValues);
        //assign the array to survey
        return survey;
        
    }
    
    public void updateSurvey(Integer serviceID, Integer rateTech, Integer rateServiceAgent, Integer rateBackEnd){
        //Initialize
        MR_Survey survey;
        
        //create object
        survey = createSurveyObject(serviceID, rateTech, rateServiceAgent, rateBackEnd);
        
        //run sql to update survey
        sqlSur.updateSurvey(survey);
    }
    
    private Integer[] assignZero(Integer[] ratings){
        for (int i = 0; i < ratings.length; i++) {
            ratings[i] = 0;
        }
        return ratings;
    }
    
    
    
    
    private void writeSurvey(Integer serviceID, Integer rateTechnician, Integer rateServiceAgent,
            Integer rateBackEndService){
        //will get survey and update the created survey in the database
        
        MR_Survey survey;
        
        //create a survey
        survey = createSurveyObject(serviceID, rateTechnician, rateServiceAgent, rateBackEndService);
        
        //send survey to database
        //create
        Integer surveyID = sqlSur.insertSurvey(survey);
        
        sqlSur.updateServiceSurvey(serviceID, surveyID);
        
    }
    
    private MR_Survey createSurveyObject(Integer serviceID, Integer rateTechnician, Integer rateServiceAgent,
            Integer rateBackEndService){
        //initialize the class
        Date currentDate = new Date();
        MR_Survey s = new MR_Survey();
        
        s.setServiceID(serviceID)
                .setRateTechnician(rateTechnician)
                .setRateServiceAgent(rateServiceAgent)
                .setRateBackEndService(rateBackEndService)
                .setRequestedDate(currentDate);
        
        return s;
    }
    
    private MR_Survey createSurveyObject(Integer serviceID, Integer[] rateValues){
        MR_Survey s = new MR_Survey();
        
        s.setServiceID(serviceID)
                .setRateTechnician(rateValues[0])
                .setRateServiceAgent(rateValues[1])
                .setRateBackEndService(rateValues[2]);
        
        
        return s;
    }
    
}
