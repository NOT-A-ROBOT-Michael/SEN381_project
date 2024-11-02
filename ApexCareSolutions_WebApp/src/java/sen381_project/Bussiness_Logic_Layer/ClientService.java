/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;



import java.sql.SQLException;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Bussiness_Logic_Layer.Objects.Client;
import sen381_project.Data_Layer.AddressDAO;
import sen381_project.Data_Layer.ClientDAO;

/**
 *
 * @author morne
 */
public class ClientService {
    private final ClientDAO clientDAO;
    private final AddressDAO addressDAO;
    
    public ClientService(){
        this.clientDAO = new ClientDAO();
        this.addressDAO = new AddressDAO();
    }
    
    public boolean clientExists(String email) throws SQLException, ClassNotFoundException{
        return clientDAO.clientExists(email);
    }
    
    public Client registerClient(Client client, Address address) throws ClassNotFoundException {
        Integer addressID = addressDAO.setAndGetAddressID(address);
        
        client.setAddressID(addressID);
        
        return clientDAO.registerClient(client);
    }
    
    public boolean authenticateClient(String email, String password) throws SQLException, ClassNotFoundException {
        //Step 1: Check if the client exists
        if (!clientDAO.clientExists(email)) {
            return false;
        }
        
        //Step 2: Retreive the client's password from the database
        String passwordFromDb = clientDAO.retreivePassword(email);
        
        //Step 3: Check if the entered password matches the stored password
        return passwordFromDb.equals(password);
    }
}
