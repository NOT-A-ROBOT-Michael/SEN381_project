
package businesslogiclayer.logic;
import businesslogiclayer.object.Address;

public class AddressLogic {
    public Address createObject(Integer addressID, String country, String city, String state, String street){
        
        Address a = new Address(addressID, country, state, city, street);
        
        return a;
    }
    
    public void getAddress(Integer customerID){
        //Initialize
        Address address;
        
        
    }
    
    public void updateAddress(String country, String city, String state, String street){
        //if exist then update else run insert
        
    }
    
    public void insertAddress(String country, String city, String state, String street){
        //Initialize
        
    
    }
}
