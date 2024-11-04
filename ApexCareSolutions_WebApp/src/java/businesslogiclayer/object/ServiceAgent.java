/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author morne
 */
public class ServiceAgent {
    private Integer serviceAgentID;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;

    // The following does not have a serviceAgentID
    public ServiceAgent(String name, String surname, String email, String phoneNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    
    // The following has a serviceAgentID
    public ServiceAgent(Integer serviceAgentID, String name, String surname, String phoneNumber, String email) {
        
        this.serviceAgentID = serviceAgentID;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        
    }
    
    public String[] getCSA_Details()
    {
        String fullCSA_ID = "CSA_" + this.serviceAgentID;
        return new String[] {fullCSA_ID, this.name, this.surname, this.phoneNumber, this.email};
    }

    public int getServiceAgentID() { return serviceAgentID; }
    public void setServiceAgentID(int serviceAgentID) { this.serviceAgentID = serviceAgentID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }    
}
