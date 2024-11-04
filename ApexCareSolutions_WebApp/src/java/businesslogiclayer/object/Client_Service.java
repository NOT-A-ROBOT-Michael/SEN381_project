// The following is used for the Client_Service Objects
package businesslogiclayer.object;

import java.util.ArrayList;

public class Client_Service
{
    // Service Details
    Integer serviceID, clientID;
    String serviceTitle, skillCategory, description, priority, status, country, state, city, streetName, firstName, lastName;
    
    // Empty Constructor
    public Client_Service()
    {
    }
    
    // The following is based on a view created in the database
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
    
    // Gets the basic information, that will be displayed on the client home page
    public ArrayList<String[]> getBasicDetails(ArrayList<Client_Service> serviceList)
    {
        // Will return an ArrayList of all the basic details of the services that belongs to a specific client
        ArrayList<String[]> basicDetail = new ArrayList<>();
        
        // Adds the services to a list
        for(var service : serviceList)
        {
            String[] info = {service.serviceID.toString(), service.serviceTitle, service.firstName, service.lastName, service.status};
            
            basicDetail.add(info);
        }
        
        // Returns the ArrayList
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
