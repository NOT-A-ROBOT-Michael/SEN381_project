// The following is used to for the address object
package businesslogiclayer.object;

public class Address
{
    // Fields for the address
    Integer addressID;
    String country, state, city, streetName;
    
    // Empty Constructor
    public Address()
    {}
    
    // Address constructor without the ID
    public Address(String country, String state, String city, String streetName)
    {
        this.country = country;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
    }
    
    // Constructor that contains the address info
    public Address(Integer addressID, String country, String state, String city, String streetName)
    {
        this.addressID = addressID;
        this.country = country;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
    }
    
    // The following returns the address information in a string array format without the ID
    public String[] getAddressNoID()
    {
        String[] address = {this.country, this.state, this.city, this.streetName};
        
        return address;
    }
    
    // The following returns the address information in a string array format
    public String[] getAddress()
    {
        String[] address = {this.addressID.toString(), this.country, this.state, this.city, this.streetName};
        
        return address;
    }
    
    // Used in the event of an address object needs to be displayed
    @Override
    public String toString()
    {
        return "Service Address: " + 
                country + ", " +
                state + ", " +
                city + ", " +
                streetName;
    }
}
