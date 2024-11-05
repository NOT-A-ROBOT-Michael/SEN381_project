/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;



import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import businesslogiclayer.object.ServiceAgent;
import businesslogiclayer.logic.CallAgentLogic;

/**
 *
 * @author morne
 */
public class ServiceAgentViewModel {
    private final CallAgentLogic serviceAgentService;
    
    public ServiceAgentViewModel(){
        this.serviceAgentService = new CallAgentLogic();
    }
    
    public String[] registerServiceAgent(String name, String surname, String email, String phoneNumber, String password, String confirmPassword) throws ClassNotFoundException{
        //Step 1: Basic validation for empty fields
        if (emptyFields(name, surname, email, phoneNumber, password, confirmPassword)) {
            return new String[] {"All fields required."};
        }
        
        //Step 2: Check if passwords match
        if (!passwordMatch(password, confirmPassword)) {
            return new String[] {"Passwords does not match"};
        }
        
        //Step 3: Check password strength
        if (!isPasswordValid(password)) {
            return new String[] {"Password must be 8 characters in length, contain atleast 1 number and 1 special character."};
        }
        
        //Step 4: Check if email is valid
        if (!isValidEmail(email)) {
            return new String[] {"Enter a valid emal address."};
        }
        
        //Step 5: Check if the email already exists using ServiceAgentService
        if (isEmailRegistered(email)) {
            return new String[] {"Provided email account is already associated with another account, try logging in."};
        }
        
        System.out.println("ServiceAgentViewModel (registerServiceAgent):");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Password: " + password);
        System.out.println("Confirm: " + confirmPassword);
        
        ServiceAgent serviceAgent_Details = saveServiceAgentToDatabase(name, surname, email, phoneNumber, password);
        
        if (serviceAgent_Details != null) {
            return serviceAgent_Details.getCSA_Details();
        } else {
            return new String[] {"Failed to save service agent in database."};
        }
    }
    
    public ServiceAgent saveServiceAgentToDatabase(String name, String surname, String email, String phoneNumber, String password) throws ClassNotFoundException{
        ServiceAgent serviceAgent = new ServiceAgent(name, surname, email, phoneNumber, password);
        
        return serviceAgentService.registerServiceAgent(serviceAgent);
    }
    
        public boolean emptyFields(String name, String surname, String email, String phone, String password, String confirmPassword)
    {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
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
    
    public boolean isEmailRegistered(String email) throws ClassNotFoundException{
        try{
            if (serviceAgentService.serviceAgentExists(email)) {
                System.out.println("Service agent with this email already exists.");
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
  
}
