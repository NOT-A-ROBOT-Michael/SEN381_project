/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;



import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import businesslogiclayer.logic.ClientService;
import businesslogiclayer.object.Address;
import businesslogiclayer.object.Client;

/**
 *
 * @author morne
 */
public class ClientViewModel {
    private final ClientService clientService;
    
    public ClientViewModel(){
        this.clientService = new ClientService();
    }
    
    public String[] registerClient(String name, String surname, String email, String phone, String password, String confirmPassword, String country, String state, String city, String streetName) throws ClassNotFoundException{
        //Step 1: Basic validation for empty fields
        if (emptyFields(name, surname, email, phone, password, confirmPassword, country, state, city, streetName)) {
            return new String[] {"All fields required."};
        }

        //Step 2:Check if passwords match
        if (!passwordMatch(password, confirmPassword)) {
            return new String[] {"Passwords do not match."};
        }
        
        //Step 3: Check password strength
        if (!isPasswordValid(password)) {
            return new String[] {"Password must be 8 characters in length, contain atleast 1 number and 1 special character."};
        }
        
        //Step 4: Check if the email is valid
        if (!isValidEmail(email)) {
            return new String[] {"Enter a valid email address."};
        }
        
        //Step 5: Check if the email already exists using ClientService
        if (isEmailRegistered(email)) {
            return new String[] {"Provided email account is already associated with another account, try logging in."};
        }

        Client clientDetails = saveClientToDatabase(name, surname, email, phone, password, country, state, city, streetName);
        if (clientDetails != null) {
            return clientDetails.getClientDetails();             
        } else {
            return new String[] {"Failed to save client in database."};
        } 
    }
    
    public Client saveClientToDatabase(String name, String surname, String email, String phone, String password, String country, String state, String city, String streetName) throws ClassNotFoundException{
        Client client = new Client(name, surname, email, phone, password);
        Address address = new Address(country, state, city, streetName);
              
        return clientService.registerClient(client, address); 
    }
    
    public boolean emptyFields(String name, String surname, String email, String phone, String password, String confirmPassword, String country, String state, String city, String streetName)
    {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || country == null || country.equals("None") || state == null || state.equals("None") || city == null || city.equals("None") || streetName == null || streetName.isEmpty()) {
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
        if (clientService.clientExists(email)) {
            System.out.println("Client with this email already exists.");
            return true;
        }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    
}
