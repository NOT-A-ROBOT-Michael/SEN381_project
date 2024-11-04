
package sen381_project.Bussiness_Logic_Layer;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;

public class Address_Logic {
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
