/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import businesslogiclayer.object.MR_Survey;
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
            
            
            if(values != null)
            {
                values.next();
                valuesArray[0] = values.getInt("Rate_Technician");
                valuesArray[1] = values.getInt("Rate_Service_Agent");
                valuesArray[2] = values.getInt("Rate_End_Service");

                for (Integer integer : valuesArray) {
                    System.out.println("Value: "+ integer);
                }
            }
            else
            {
                for(int i = 0;  i < 3; i++)
                {
                    valuesArray[i] = null;
                }
            }
                
            
            return valuesArray;
            
        } catch (SQLException e) {
            System.err.println("SQL select Error: " + e.getMessage());
        }
        return valuesArray;
    }
    
    
    public Integer insertSurvey(MR_Survey survey){
        String insertSQL = "INSERT INTO public.\"Survey\" (" +
                "\"Rate_Technician\", \"Rate_Service_Agent\", \"Rate_End_Service\") "+
                "VALUES (?, ?, ?) RETURNING \"SurveyID\"";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {         
            
            
            statement.setInt(1, survey.getRateTechnician());
            statement.setInt(2, survey.getRateServiceAgent());
            //statement.setInt(4, survey.rateBackEndService());
            statement.setInt(3, survey.getRateBackEndService());
            
            //System.out.println("Success, inserted rows: " + rowsAffected);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next())
            {
                return rs.getInt("SurveyID");
            }
            
        } catch (SQLException e) {
            System.err.println("Insert Error: " + e.getMessage());
        }
        
        return null;
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
    
    // Sets a service's status to ongoing
    public void updateServiceSurvey(Integer serviceID, Integer surveyID)
    {
        // The query updates a specified service's status to ongoing
        String query = "UPDATE \"Services\"SET \"SurveyID\"=? WHERE \"ServiceID\"=?;";
        
        
        
        try(PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, surveyID);
            psmt.setInt(2, serviceID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated service survey ID. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateServiceToDeclined) Error, while trying to set the service updated service survey ID: " + e.getMessage() + " -----!E!");
        }
    }
    
}
