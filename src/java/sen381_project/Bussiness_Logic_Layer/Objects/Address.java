
package sen381_project.Bussiness_Logic_Layer.Objects;

public class Address {
    Integer addressID;
    String country, state, city, streetName;
    
    public Address()
    {}
    
    public Address(Integer addressID, String country, String state, String city, String streetName)
    {
        this.addressID = addressID;
        this.country = country;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
    }
    
    public Address setCountry(String country){
        this.country = country;
        return this;
    }
    
    public Address setState(String country){
        this.country = country;
        return this;
    }
    
    public String[] getAddress()
    {
        String[] address = {this.addressID.toString(), this.country, this.state, this.city, this.streetName};
        
        return address;
    }
    
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
