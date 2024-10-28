
package sen381_project.Bussiness_Logic_Layer.Objects;

import java.util.ArrayList;
import java.util.Date;


public class Client_Service
{
    
    // Service
    
    Integer serviceID, clientID;
    String serviceTitle, skillCategory, description, priority, status, country, state, city, streetName, firstName, lastName;
    
    public Client_Service()
    {
    }
    
    public Client_Service(Integer serviceID, Integer clientID, String serviceTitle, String skillCategory, String description, String priority, String status, String country, String state, String city, String streetName, String firstName, String lastName)
    {
        // Service
        this.serviceID = serviceID;
        this.clientID = clientID;
        this.serviceTitle = serviceTitle;
        this.skillCategory = skillCategory;
        this.description = description;
        this.priority = priority;
        this.status = status;
        
        // Address
        this.country = country;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
        
        // Technician
        this.firstName = firstName;
        this.lastName = lastName;
        
    }
    
    public ArrayList<String[]> getBasicDetails(ArrayList<Client_Service> serviceList)
    {
        ArrayList<String[]> basicDetail = new ArrayList<>();
        
        for(var service : serviceList)
        {
            String[] info = {service.serviceID.toString(), service.serviceTitle, service.firstName, service.lastName, service.status};
            
            basicDetail.add(info);
        }
        return basicDetail;
    }
    
    @Override
    public String toString()
    {
        return "Client Service Information:" + 
                "Service ID: " + serviceID +
                ", Client ID: " + clientID +
                ", Service Title: " + serviceTitle +
                ", Skill Category: " + skillCategory +
                ", Description: " + description +
                ", Priority: " + priority +
                ", Status: " + status +
                ", Country: " + country +
                ", State: " + state +
                ", City: " + city +
                ", Street Name: " + streetName +
                ", First Name: " + firstName +
                ", Last Name: " + lastName;
    }
    
    
    
    
    
}
