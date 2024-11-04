
package businesslogiclayer.object;


public class Specialisation {
    
    Integer specialID;
    String serviceType;
    
    public Specialisation()
    {}
    
    public Specialisation(Integer specialID, String serviceType)
    {
        
        this.specialID = specialID;
        this.serviceType = serviceType;
        
    }
    
    public String[] getSpecialDetails()
    {
        return new String[] {this.specialID.toString(), this.serviceType};
    }
    
    @Override
    public String toString()
    {
        return "Specialisation ID: " + specialID + ", "
                + "Category: " + serviceType;
    }
}
