/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import businesslogiclayer.object.Address;
import businesslogiclayer.object.Technician;
import businesslogiclayer.object.TechnicianLocation;
import businesslogiclayer.logic.TechnicianService;

/**
 *
 * @author morne
 */
public class TechnicianViewModel {
    private final TechnicianService technicianService;
    
    public TechnicianViewModel(){
        this.technicianService = new TechnicianService();
    }
    
    public String[] registerTechnician(String name, String surname, String email, String phoneNumber, String specializationString, String password, String passwordConfirm, String country, String state, String city, String streetName) throws ClassNotFoundException{
        int specialization;
        
        //Convert specialization to int
        if(specializationString != null)
        {
            specialization = Integer.parseInt(specializationString);
        }
        else
        {
            specialization = 0;
        }
        

        //1. Basic validation for empty fields
        if (emptyFields(name, surname, email, phoneNumber, specializationString, password, passwordConfirm)) {
            return new String[] {"All fields are required."};
        }
        
        //2. Check if passwords match
        if (!passwordMatch(password, passwordConfirm)) {
            return new String[] {"Passwords do not match."};
        }
        
        //3. Check password strength
        if (!isPasswordValid(password)) {
            return new String[] {"Password must be 8 characters in length, contain atleast 1 number and 1 special character."};
        }
        
        //4. Check if email is valid
        if (!isValidEmail(email)) {
            return new String[] {"Enter a valid email address."};
        }
            
        //5. Check if Technician already exists using technicianService
        if (isTechnicianRegistered(email)) {
            return new String[] {"Provided email account is already associated with another account, try logging in."};
        }
        
        Technician tech = saveTechnicianToDatabase(name, surname, email, phoneNumber, specialization, password, country, state, city, streetName);
        
        if (tech != null)
        {
            return tech.getTechDetails();             
        } else {
            return new String[] {"Failed to save technician in database."};
        }
        
    }
    
    public Technician saveTechnicianToDatabase(String name, String surname, String email, String phoneNumber, Integer specialization, String password, String country, String state, String city, String streetName) throws ClassNotFoundException{
        
        Technician technician = new Technician(name, surname, email, phoneNumber, specialization, password);
        Address address = new Address(country, state, city, streetName);
        TechnicianLocation location = new TechnicianLocation(0, 0);
        
        return technicianService.registerTechnician(technician, address, location);
    }
    
     public boolean emptyFields(String name, String surname, String email, String phoneNumber, String specialization, String password, String confirmPassword)
    {// 0 is used to represent an "empty" int        
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || email == null || email.isEmpty() || phoneNumber == null || phoneNumber.isEmpty() || specialization == null || specialization.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            System.out.println("All fields are required");
            return true;
        }
        return false;
    }
    
    public boolean passwordMatch(String password, String confirmPassword){
        if (!password.equals(confirmPassword)) {
            System.out.println("The passwords does not match.");
            return false;
        }
        return true;
    }
    
    public  boolean isPasswordValid(String password){
        String passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})";//Regular expression pattern; lockhead expression (?.*) - used for looking for the items in the set
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);        
        return matcher.find();
    }
    
    public boolean isValidEmail(String email){
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; //Regular expression for validating email
        Pattern pattern = Pattern.compile(emailPattern);// Compile the regular expression into a pattern object
        Matcher matcher = pattern.matcher(email);// Create a matcher object that will match the email against the given pattern
        return matcher.find();//Return true if the email matches the pattern
    }
    
    public boolean isTechnicianRegistered(String email) throws ClassNotFoundException{
        try{
        if (technicianService.technicianExists(email)) {
            System.out.println("Technician with this email already exists.");
            return true;
        }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
