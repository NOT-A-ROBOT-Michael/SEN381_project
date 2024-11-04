/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author morne
 */
public class Client {
    private Integer clientID;
    private Integer addressID;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    
    // Client with no ID
    public Client(String name, String surname, String email, String phoneNumber, String password)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    
    // Client with ID
    public Client(Integer clientID, Integer addressID, String name, String surname, String phoneNumber, String email)
    {
        this.clientID = clientID;
        this.addressID = addressID;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public String[] getClientDetails()
    {
        String fullClientID = "C_" + this.clientID;
        return new String[] {fullClientID, this.addressID.toString(), this.name, this.surname, this.phoneNumber, this.email};
    }
    public int getId() { return clientID; }
    public void setId(int id) { this.clientID = id; }
    
    public int getAddressID() { return addressID; }
    public void setAddressID(int id) { this.addressID = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phoneNumber; }
    public void setPhone(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getPassword() { return password; };
    public void setPassword(String password) { this.password = password; };
}
