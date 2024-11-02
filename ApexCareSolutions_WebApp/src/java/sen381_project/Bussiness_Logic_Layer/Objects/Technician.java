/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sen381_project.Bussiness_Logic_Layer.Objects;

/**
 *
 * @author morne
 */
public class Technician {
    private Integer employeeNumber;
    private Integer addressID;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Integer specialization; //holds the specializationID hence int
    private Integer locationID;
    private String password;
    
    // Constructor with no ID
    public Technician(String name, String surname, String email, String phoneNumber, int specialization, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.password = password;
    }
    
    public Technician(Integer employeeNumber, Integer addressID, String name, String surname, String phoneNumber, String email, Integer specialization, Integer locationID)
    {
        this.employeeNumber = employeeNumber;
        this.addressID = addressID;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
        this.locationID = locationID;
        
    }
    
    
    public String[] getTechDetails()
    {
        String fullTechID = "T_" + this.employeeNumber;
        return new String[] {fullTechID, this.addressID.toString(), this.name, this.surname, this.phoneNumber, this.email, this.specialization.toString(), this.locationID.toString()};
    }
    
    public int getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(int employeeNumber) { this.employeeNumber = employeeNumber; }
    
    public int getAddressID() { return addressID; }
    public void setAddressID(int id) { this.addressID = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getSpecialization() { return specialization; }
    public void setSpecialization(int specialization) { this.specialization = specialization; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public int getLocationID() { return locationID; }
    public void setLocationID(int id) { this.locationID = id; }
    
}
