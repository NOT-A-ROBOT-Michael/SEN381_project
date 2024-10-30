// The following handles the logic of the page where the clients can request a service
package sen381_project.Bussiness_Logic_Layer;

import java.time.LocalDate;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Data_Layer.ConnectionProvider;

public class ClientRequestLogic
{
    ConnectionProvider cp = new ConnectionProvider();
    
    // The following allocates a Call Service Agent to the service
    public Integer getCSA() throws Exception
    {
        // Gets a list of Call Service Agent IDs
        ArrayList<Integer> csa_IDs = cp.getListCSA_IDs();
            
        Integer selectedCSA = null;

        // Will not allocate a call service agent if there are none available
        if(!csa_IDs.isEmpty())
        {

            Integer numOfCSA = csa_IDs.size();
            Integer csaIndex = (int) (Math.random() * numOfCSA);

            selectedCSA = csa_IDs.get(csaIndex);

        }

        return selectedCSA;
    }
    
    // The following sees if the address that the client entered is the same as their own address
    public boolean checkAddress(Integer addressID, String country, String state, String city, String streetName) throws Exception
    {
        // Gets the client's address
        Address a = cp.getAddress(addressID);

        // Adds the items of the object to string array so it can be compared with the entered address
        String[] clientAddress = a.getAddress();
        
        // If the address is the same it will return true else it will return false
        return country.equals(clientAddress[1]) && state.equals(clientAddress[2]) && city.equals(clientAddress[3]) && streetName.equals(clientAddress[4]);
        
    }
    
    // The following adds a new address to the database and returns its ID
    public Integer addAddressAndGetID(String country, String state, String city, String streetName) throws Exception
    {
        // Inserts the new address and gets the ID of the new address
        Integer addressID = cp.setAndGetAddressID(country, state, city, streetName);

        return addressID;
    }
    
    // The following adds a new contract to the database and returns its ID
    public Integer getContractID(String specialisation) throws Exception
    {
        // The following checks if the specialisation that the user selected does exist
        Integer specialisationID = cp.checkSpecialisation(specialisation);

        // If the specialisation does exist, a contract will be created
        if(specialisationID != null)
        {
            // Inserts the new contract and gets the returned ID
            Integer contractID = cp.setAndGetContractID(specialisationID);

            return contractID;
        }
        else
        {
            return null;
        }
    }
    
    // The following adds a new service to the database
    public void addNewService(Integer csa_ID, Integer contractID, Integer clientID, Integer addressID, String description, String status, String urgency, LocalDate currentDate, String serviceTitle) throws Exception
    {
        cp.addServiceFromClient(csa_ID, contractID, clientID, addressID, description, status, urgency, currentDate, serviceTitle);
    }
    
    // The following gets the details of the client's address
    public String[] clientAddress(Integer addressID) throws Exception
    {
        Address a = cp.getAddress(addressID);
        
        return a.getAddress();
    }
    
    // The following gets the different service types that the company offers
    public ArrayList<String> getServiceTypes() throws Exception
    {
        return cp.getTypeOfServices();
    }
}
