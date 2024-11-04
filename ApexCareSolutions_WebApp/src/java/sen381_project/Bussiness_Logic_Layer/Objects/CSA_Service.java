
package sen381_project.Bussiness_Logic_Layer.Objects;

import java.util.ArrayList;

public class CSA_Service {
    // Service Details
    Integer serviceID, clientID;
    String serviceTitle, skillCategory, description, priority, status, country, state, city, streetName, tech_FirstName, tech_LastName;
    String client_FirstName, client_LastName, phoneNumber, email;
    
    // Empty Constructor
    public CSA_Service()
    {
    }
    
    // The following is based on a view created in the database
    public CSA_Service(Integer serviceID, Integer clientID, String serviceTitle, String skillCategory, String description, String priority, String status, String country, String state, String city, String streetName, String tech_FirstName, String tech_LastName)
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
        this.tech_FirstName = tech_FirstName;
        this.tech_LastName = tech_LastName;
        
    }
    
    // The following is based on a view created in the database
    public CSA_Service(Integer serviceID, Integer clientID, String client_FirstName, String client_LastName, String phoneNumber, String email, String serviceTitle, String skillCategory, String description, String priority, String status, String country, String state, String city, String streetName, String tech_FirstName, String tech_LastName)
    {
        
        this.serviceID = serviceID;
        
        // Client
        this.clientID = clientID; // 0
        this.client_FirstName = client_FirstName; // 1
        this.client_LastName = client_LastName; // 2
        this.phoneNumber = phoneNumber; // 3
        this.email = email; // 4
        
        // Service
        this.serviceTitle = serviceTitle; // 5
        this.skillCategory = skillCategory; // 6
        this.description = description; // 7
        this.priority = priority; // 8
        this.status = status; // 9
        
        // Address
        this.country = country; // 10
        this.state = state; // 11
        this.city = city; // 12
        this.streetName = streetName; // 13
        
        // Technician
        this.tech_FirstName = tech_FirstName; // 14
        this.tech_LastName = tech_LastName; // 15
        
    }
    
    // Gets the basic information, that will be displayed on the client home page
    public ArrayList<String[]> getBasicDetails(ArrayList<CSA_Service> serviceList)
    {
        // Will return an ArrayList of all the basic details of the services that belongs to a specific client
        ArrayList<String[]> basicDetail = new ArrayList<>();
        
        // Adds the services to a list
        for(var service : serviceList)
        {
            String[] info = {service.serviceID.toString(), service.serviceTitle, service.tech_FirstName, service.tech_LastName, service.status};
            
            basicDetail.add(info);
        }
        
        // Returns the ArrayList
        return basicDetail;
    }
    
    public String[] getServiceDetails()
    {
        
        return new String[] {this.serviceID.toString(), this.clientID.toString(), this.client_FirstName, this.client_LastName, this.phoneNumber, this.email, this.serviceTitle, this.skillCategory, this.description, this.priority, this.status, this.country, this.state, this.city, this.streetName, this.tech_FirstName, this.tech_LastName};
    
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
                ", First Name: " + tech_FirstName +
                ", Last Name: " + tech_LastName;
    }
}
