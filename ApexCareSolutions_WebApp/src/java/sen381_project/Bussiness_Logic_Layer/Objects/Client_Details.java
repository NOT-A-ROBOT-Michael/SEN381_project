// The following is used for the Client_Details objects
package sen381_project.Bussiness_Logic_Layer.Objects;


public class Client_Details 
{
    // All the details of a client
    Integer clientID, addressID;
    String firstName, lastName, phoneNumber, email, password;
    
    // Empty constructor
    public Client_Details()
    {}
    
    // The following constructor is based on the client table in the database
    public Client_Details(Integer clientID, Integer addressID, String firstName, String lastName, String phoneNumber, String email, String password)
    {
        this.clientID = clientID;
        this.addressID = addressID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    
    // Returns the client's information in a String array
    public String[] getClientInfo()
    {
        // Indicates that the ID belongs to a client
        String fullClientID = "C_" + this.clientID;
        
        String[] clientInfo = {fullClientID, this.addressID.toString(), this.firstName, this.lastName, this.phoneNumber, this.email, this.password};
        
        return clientInfo;
    }
    
    public String[] getClientInfo2()
    {
        
        
        String[] clientInfo = {this.clientID.toString(), this.addressID.toString(), this.firstName, this.lastName, this.phoneNumber, this.email, this.password};
        
        return clientInfo;
    }
    
    // Returns the client's password
    public String getClientPass()
    {
        return this.password;
    }
    
    @Override
    public String toString()
    {
        return "Client Info: "
                + "Client ID: " + clientID
                + "Address ID: " + addressID
                + "First Name: " + firstName
                + "Last Name: " + lastName
                + "Phone Number: " + phoneNumber
                + "Email: " + email;
    }
    
}
