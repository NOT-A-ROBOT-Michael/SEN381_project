/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;



import java.sql.SQLException;
import businesslogiclayer.object.ServiceAgent;
import datalayer.ServiceAgentDAO;

/**
 *
 * @author morne
 */
public class CallAgentLogic {
    private final ServiceAgentDAO serviceAgentDAO;
    
    public CallAgentLogic(){
        this.serviceAgentDAO = new ServiceAgentDAO();
    }
    
    public boolean serviceAgentExists(String email) throws SQLException, ClassNotFoundException{
        return serviceAgentDAO.serviceAgentExists(email);
    }
    
    public ServiceAgent registerServiceAgent(ServiceAgent serviceAgent) throws ClassNotFoundException{
        
        return serviceAgentDAO.registerServiceAgent(serviceAgent);
    }
    
    public boolean authenticateServiceAgent(String email, String password) throws SQLException, ClassNotFoundException {
        //Step 1: Check if the client exists
        if (!serviceAgentDAO.serviceAgentExists(email)) {
            return false;
        }
        
        //Step 2: Retreive the client's password from the database
        String passwordFromDb = serviceAgentDAO.retreivePassword(email);
        
        //Step 3: Check if the entered password matches the stored password
        return passwordFromDb.equals(password);
    }
}
