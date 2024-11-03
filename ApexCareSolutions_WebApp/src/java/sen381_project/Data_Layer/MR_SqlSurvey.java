/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Data_Layer;
import sen381_project.Bussiness_Logic_Layer.Objects.MR_Survey;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author iyesme
 */
public class MR_SqlSurvey {
    
    private Connection conn;
    
    public MR_SqlSurvey() {
        
        try {
            dbConnection connection = new dbConnection();
        
            this.conn = connection.getConnection();
        } catch (Exception e) {
            System.err.println("Connection error: "+e.getMessage());
        }
        
    }
    
    
    public Integer[] getSurveyValues(Integer serviceID){
        String selectSQL = "SELECT \"Rate_Technician\", \"Rate_Service_Agent\", \"Rate_End_Service\" FROM public.\"SurveyView\" where \"ServiceID\" = ?";
        
        Integer[] valuesArray = new Integer[3];
        
        try (PreparedStatement statement = conn.prepareStatement(selectSQL)) {
            System.out.println("ServiceID: "+ serviceID);
            statement.setInt(1, serviceID);
            ResultSet values = statement.executeQuery();
            
            values.next();
            valuesArray[0] = values.getInt("Rate_Technician");
            valuesArray[1] = values.getInt("Rate_Service_Agent");
            valuesArray[2] = values.getInt("Rate_End_Service");
            
            for (Integer integer : valuesArray) {
                System.out.println("Value: "+ integer);
            }
            
            return valuesArray;
            
        } catch (SQLException e) {
            System.err.println("SQL select Error: " + e.getMessage());
        }
        return valuesArray;
    }
    
    
    public void insertSurvey(MR_Survey survey){
        String insertSQL = "INSERT INTO public.\"Survey\" (" +
                "\"ServiceID\", \"Rate_Technician\", \"Rate_Service_Agent\", \"Rate_End_Service\") "+
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {         
            
            statement.setInt(1, survey.getServiceID());
            statement.setInt(2, survey.getRateTechnician());
            statement.setInt(3, survey.getRateServiceAgent());
            //statement.setInt(4, survey.rateBackEndService());
            statement.setInt(4, survey.getRateBackEndService());
            
            int rowsAffected = statement.executeUpdate();
            System.out.println("Success, inserted rows: " + rowsAffected);
            
            
        } catch (SQLException e) {
            System.err.println("Insert Error: " + e.getMessage());
        }
    }
    
    public void updateSurvey(MR_Survey survey){
        String insertSQL = "UPDATE public.\"Survey\" " +
        "SET \"Rate_Technician\"=?, \"Rate_Service_Agent\"=?, \"Rate_End_Service\"=? " +
        "WHERE \"SurveyID\" = (SELECT \"SurveyID\" FROM \"SurveyView\" WHERE \"ServiceID\" = ?)  ;";

        /*System.out.println(survey.getRateTechnician());
        System.out.println(survey.getRateServiceAgent());
        System.out.println(survey.getRateBackEndService());*/
        
        
        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {         
            
            //System.out.println(survey.getRateTechnician());
            
            statement.setInt(1, survey.getRateTechnician());
            statement.setInt(2, survey.getRateServiceAgent());
            statement.setInt(3, survey.getRateBackEndService());
            statement.setInt(4, survey.getServiceID());            
            
            int rowsAffected = statement.executeUpdate();
            System.out.println("Success, updated rows: " + rowsAffected);
            
            
        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
        }
    }
    
}
