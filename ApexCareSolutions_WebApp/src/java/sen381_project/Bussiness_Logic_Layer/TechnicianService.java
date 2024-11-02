/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer;

import java.sql.SQLException;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Bussiness_Logic_Layer.Objects.Specialisation;
import sen381_project.Bussiness_Logic_Layer.Objects.Technician;
import sen381_project.Bussiness_Logic_Layer.Objects.TechnicianLocation;
import sen381_project.Data_Layer.AddressDAO;
import sen381_project.Data_Layer.TechnicianDAO;

/**
 *
 * @author morne
 */
public class TechnicianService {
    private final TechnicianDAO technicianDAO;
    private final AddressDAO addressDAO;
    
    public TechnicianService(){
        this.technicianDAO = new TechnicianDAO();
        this.addressDAO = new AddressDAO();
    }
    
    public boolean technicianExists(String email) throws SQLException, ClassNotFoundException{
        return technicianDAO.technicianExists(email);
    }
    
    public Technician registerTechnician(Technician technician, Address address, TechnicianLocation location) throws ClassNotFoundException{
        
        Integer addressID = addressDAO.setAndGetAddressID(address);
        
        Integer locationID = technicianDAO.setTechLocation(location);
        
        technician.setAddressID(addressID);
        technician.setLocationID(locationID);
        
        return technicianDAO.registerTechnician(technician);
    }
    
    public boolean authenticateTechnician(String email, String password) throws SQLException, ClassNotFoundException {
        //Step 1: Check if the client exists
        if (!technicianDAO.technicianExists(email)) {
            return false;
        }
        
        //Step 2: Retreive the client's password from the database
        String passwordFromDb = technicianDAO.retreivePassword(email);
        
        //Step 3: Check if the entered password matches the stored password
        return passwordFromDb.equals(password);
    }
    
    public ArrayList<String[]> getServiceTypes() throws Exception
    {
        ArrayList<Specialisation> serviceTypes_Objects = technicianDAO.getSpecialisations();
        
        ArrayList<String[]> serviceTypes = new ArrayList<>();
        
        for(var item : serviceTypes_Objects)
        {
            serviceTypes.add(item.getSpecialDetails());
        }
        
        return serviceTypes;
    }
}
