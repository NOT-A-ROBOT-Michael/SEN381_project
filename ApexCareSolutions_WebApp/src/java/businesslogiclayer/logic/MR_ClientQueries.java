/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.logic;
import businesslogiclayer.object.MR_ClientQuery;
import datalayer.MR_SqlClientQuery;
import java.util.Date;

/**
 *
 * @author iyesme
 */
public class MR_ClientQueries {
    
    MR_SqlClientQuery sqlcq = new MR_SqlClientQuery();
    
    
    public void LogClientQuery(Integer ServiceID, String description, Date requestedDate){
        MR_ClientQuery cq;
        
        //create a client query object
        cq = createClientQuery(ServiceID, description, requestedDate);

        //call insert for client query
        sqlcq.insertClientQuery(cq);
        
    } 
    
    private MR_ClientQuery createClientQuery(Integer ServiceID, String description, Date requestedDate){
        MR_ClientQuery cq = new MR_ClientQuery();
        
        cq.serviceID(ServiceID)
                .description(description)
                .requestedDate(requestedDate);
        
        return cq;
    }
    
}
