
package sen381_project.Bussiness_Logic_Layer.Objects;


public class Client_Details 
{
    Integer clientID, addressID;
    String firstName, lastName, phoneNumber, email, password;
    
    
    public Client_Details()
    {}
    
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
    
    public String[] getClientLoginInfo(Client_Details cd)
    {
        String[] loginInfo = {this.email, this.password};
        return loginInfo;
    }
    
    public String[] getClientInfo()
    {
        String fullClientID = "C_" + this.clientID;
        
        String[] clientInfo = {fullClientID, this.addressID.toString(), this.firstName, this.lastName, this.phoneNumber, this.email, this.password};
        
        return clientInfo;
    }
    
    public String getClientPass(Client_Details cd)
    {
        return cd.password;
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
