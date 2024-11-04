
package businesslogiclayer.object;


public class TechnicianLocation {
    Integer locationID;
    float lat, lon;
    
    public TechnicianLocation()
    {
    }
    
    public TechnicianLocation(float lat, float lon)
    {
        this.lat = lat;
        this.lon = lon;
    }
    
    public TechnicianLocation(Integer locationID, float lat, float lon)
    {
        this.locationID = locationID;
        this.lat = lat;
        this.lon = lon;
    }
    
    public float[] getLocationNoID()
    {
        return new float[] {this.lat, this.lon};
    }
    
    @Override
    public String toString()
    {
        return "Technician Location: \nLat: " + lat + "\nLon: " + lon;     
    }
}
