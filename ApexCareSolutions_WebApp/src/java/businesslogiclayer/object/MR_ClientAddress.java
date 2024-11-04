/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslogiclayer.object;

/**
 *
 * @author iyesme
 */
public class MR_ClientAddress {
    private String country;
    private String state;
    private String city;
    private String street;
    
    public MR_ClientAddress(){}
    
    public MR_ClientAddress(String country, String state, String city, String street){
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }
    
    public MR_ClientAddress country(String country){
        this.country = country;
        return this;
    }
    
    public String country(){
        return this.country;
    }
    
    public MR_ClientAddress state(String state){
        this.state = state;
        return this;
    }
    
    public String state(){
        return this.state;
    }
    
    public MR_ClientAddress city(String city){
        this.city = city;
        return this;
    }
    
    public String city(){
        return this.city;
    }
    
    public MR_ClientAddress street(String street){
        this.street = street;
        return this;
    }
    
    public String street(){
        return this.street;
    }
}
