// The following is used to handle database queries and connection

package datalayer;

import businesslogiclayer.object.Client_Details;
import businesslogiclayer.object.Client_Service;
import businesslogiclayer.object.Address;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import businesslogiclayer.object.AR_ClientDetails;
import businesslogiclayer.object.AR_Notes;
import businesslogiclayer.object.AR_TechnicianDetails;
import businesslogiclayer.object.AR_TechnicianTask;
import businesslogiclayer.object.CSA_Service;
import businesslogiclayer.object.Service;

public class ConnectionProvider {
    String TechEmail;
    // Constructor
    public ConnectionProvider()
    {}
    
    // Enter own database info
    String username = "postgres";
    String pwd = System.getenv("post-pwd");
    
    // Database URL
    private static final String conURL = "jdbc:postgresql://localhost:5432/ApexCareDB";
    
    // Driver
    private static final String DRIVER = "org.postgresql.Driver";
    
    Connection con;
    
    // The following method is used to create a connection with the database
    public Connection getCon() throws ClassNotFoundException
    {
        try
        {
            // Get an instance of the driver class
            Class.forName(DRIVER);
            
            // Create the connection with the database
            this.con = DriverManager.getConnection(conURL, username, pwd);
            if(this.con != null)
            {
                // Indicate that the connection with the database was successful;
                System.out.println("!Info!----- Connection to ApexCareDB was successfully created. -----!Info!");
            }
            
        }
        catch (SQLException e)
        {
            // In the event of an error occurring
            System.out.println("!E!----- (ConnectionProvider -> getCon) Error, while trying to create a connection to the database: " + e.getMessage() + " -----!E!");
        }
        
        return con;
    }
    
    // The following will check if the user exists in the database
    public String[] checkUserLoginDetails(String userEmail, String userPass) throws ClassNotFoundException
    {
        // Used to increment between the different users
        int i = 0;
        
        // Queries
        String query_Client = "SELECT * FROM public.\"Client\" WHERE \"Email\" = ? AND \"Password\" = ?";
        String query_Technician = "SELECT * FROM public.\"Technician\" WHERE \"Email\" = ? AND \"Password\" = ?";
        String query_CallServiceAgent = "SELECT * FROM public.\"Call Service Agent\" WHERE \"Email\" = ? AND \"Password\" = ?";
        
        // Add the strings to an array
        String[] queries = {query_Client, query_Technician, query_CallServiceAgent};
        
        try(Connection conn = getCon();)
        {
            // Use to handle inputs
            PreparedStatement psmt;
            
            // Iterate through the different queries to check all the users
            for(String query : queries)
            {
                i++;
                
                // Set the values to be evaluated 
                psmt = conn.prepareStatement(query);
                psmt.setString(1, userEmail);
                psmt.setString(2, userPass);

                // Get the result of the query
                ResultSet result = psmt.executeQuery();

                // Check if there is a value found
                while (result.next())
                {
                    System.out.println("!Info!----- Found a user! -----!Info!");
                    
                    // Items that all the users should have
                    String firstName = result.getString("First_Name");
                    String lastName = result.getString("Last_Name");
                    String phoneNum = result.getString("Phone_Number");
                    String email = result.getString("email");
                    
                    // The following switch be able to determine which user type is being accessed
                    switch (i)
                    {
                        // Client
                        case 1:
                        {
                            System.out.println("!Info!----- The user is a Client. -----!Info!");
                            
                            // Items required for this user type
                            String clientID = result.getString("ClientID");
                            String addressID = result.getString("AddressID");
                            
                            // Return a array of details
                            String[] details = {"C_" + clientID, addressID, firstName, lastName, phoneNum, email};
                            return details;
                        }
                        // Technician
                        case 2:
                        {
                            System.out.println("!Info!----- The user is a Technician. -----!Info!");
                            
                            // Items required for this user type
                            String technicianID = result.getString("TechnicianID");
                            String addressID = result.getString("AddressID");
                            String specialisationID = result.getString("SpecialisationID");
                            String locationID = result.getString("LocationID");
                            
                            // Return a array of details
                            String[] details = {"T_" + technicianID, addressID, firstName, lastName, phoneNum, email, specialisationID, locationID};
                            return details;
                        }
                        // Call Service Agent
                        case 3:
                        {
                            System.out.println("!Info!----- The user is a Call Service Agent. -----!Info!");
                            
                            // Items required for this user type
                            String csaID = result.getString("CallServiceAgentID");
                            
                            // Return a array of details
                            String[] details = {"CSA_" + csaID, firstName, lastName, phoneNum, email};
                            return details;
                        }
                        default:
                        {
                            // In the event a user was found but they have no type assigned to them,
                            // it will not give them access to any pages
                            System.out.println("!Info!----- The user does not exist -----!Info!");
                            return null;
                        }
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> checkUserLoginDetails)Error, while trying to login user: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    
    // The following returns a ArrayList of service objects that contains the information required on the client's environment
    public ArrayList<Client_Service> getClientService(Integer cID) throws ClassNotFoundException
    {
        // A view was created to get the specific data that will be displayed to the client
        String query = "SELECT * FROM \"clientServiceView\" WHERE \"ClientID\" = ?";
        // In the event of a client having multiple services, we store them in an ArrayList
        ArrayList<Client_Service> services = new ArrayList<>();
        
        // The following try-with-resources will close the connection to the database on completion or in the event of an error.
        try (Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Entered Client ID
            psmt.setInt(1, cID);
            
            // Cotains the items that have been returned by the query
            ResultSet rs = psmt.executeQuery();
            
            // The following will add all the services that were found to the created ArrayList
            while(rs.next())
            {
                // Service
                Integer serviceID = rs.getInt("ServiceID"); // 0
                Integer clientID = rs.getInt("ClientID"); // 1
                String serviceTitle = rs.getString("Service_Title"); // 2
                String skillCategory = rs.getString("Category"); // 3
                String description = rs.getString("Description"); // 4
                String priority = rs.getString("Priority"); // 5
                String status = rs.getString("Status"); // 6

                // Address
                String country = rs.getString("Country"); // 7
                String state = rs.getString("State"); // 8
                String city = rs.getString("City"); // 9
                String streetName = rs.getString("Street_Name"); // 10

                // Technician
                String firstName = rs.getString("First_Name"); // 11
                String lastName = rs.getString("Last_Name"); // 12

                System.out.println("!Info!----- Service ID: " + serviceID + " -----!Info!");
                
                Client_Service serviceInfo = new Client_Service(serviceID, clientID, serviceTitle, skillCategory, description, priority, status, country, state, city, streetName, firstName, lastName);

                services.add(serviceInfo);
            }
            
            return services;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getClientService) Error, while trying to get service info: " + e.getMessage() + "-----!E!");
        }
        
        return null;
    }
    
    // The following gets the possible types of services that the company can offer
    public ArrayList<String> getTypeOfServices() throws ClassNotFoundException
    {
        // Gets the possible specialisations
        String query = "SELECT * FROM \"Specialisation\"";
        
        // The service types will be stored in an ArrayList
        ArrayList<String> serviceTypes = new ArrayList<>();
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                serviceTypes.add(rs.getString("Category"));
            }
            
            System.out.println("!Info!----- Successfully got service types if any exist. -----!Info!");
            
            return serviceTypes;
            
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getTypeOfServices) Error, while trying to get the service categories: " + e.getMessage() + " -----!E!");
        }
        
        
        return null;
    }
    
    // The following is used to add a new address and return its ID
    public Integer setAndGetAddressID(String country, String state, String city, String streetName) throws ClassNotFoundException
    {
        // The query inserts a new address into the database and returns its ID
        String query = "INSERT INTO \"Address\" (\"Country\", \"State\", \"City\", \"Street_Name\") VALUES(?,?,?,?) RETURNING \"AddressID\"";
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Used to store the ID of the created address
            Integer addressID;
            
            psmt.setString(1, country);
            psmt.setString(2, state);
            psmt.setString(3, city);
            psmt.setString(4, streetName);
            
            ResultSet rs = psmt.executeQuery();
            
            System.out.println("!Info!----- Successfully added new service address. -----!Info!");
            
            while(rs.next())
            {
                addressID = rs.getInt("AddressID");
                
                System.out.println("!Info!----- Successfully got the added service's address ID. -----!Info!");
                
                return addressID;
            }
            
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> setAndGetAddressID) Error, while trying to add service address: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following is used to add a new service from the client's side (When the client logs a problem)
    public void addServiceFromClient(Integer csaID, Integer contractID, Integer clientID, Integer addressID, String description, String status, String priority, LocalDate requestedDate, String serviceTitle) throws ClassNotFoundException
    {
        // The following query inserts a new service into the database
        String query = "INSERT INTO \"Services\" (\"CallServiceAgentID\", \"ContractID\", \"ClientID\", \"AddressID\", \"Description\", \"Status\", \"Priority\", \"Requested_Date\", \"Service_Title\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // The following formats the local date to a sql date
            Date sql_RequestedDate = Date.valueOf(requestedDate);
            
            psmt.setInt(1, csaID); // Call Service Agent ID
            psmt.setInt(2, contractID); // Contract ID
            psmt.setInt(3, clientID); // Client ID
            psmt.setInt(4, addressID); // Service Address ID
            psmt.setString(5, description); // Service Description
            psmt.setString(6, status); // Service Status
            psmt.setString(7, priority); // Service Priority
            psmt.setDate(8, sql_RequestedDate); // Date of service request
            psmt.setString(9, serviceTitle); // Service Title
            
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully added new service from client side. -----!Info!");
            
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> addServiceFromClient) Error, while trying to add a new service from the client's side: " + e.getMessage() + " -----!E!");
        }
    }
    
    // The following gets a list of IDs of all the Call Service Agents
    public ArrayList<Integer> getListCSA_IDs() throws ClassNotFoundException
    {
        // Gets the IDs of all the Call Service Agents stored in the database
        String query = "SELECT \"CallServiceAgentID\" FROM \"Call Service Agent\"";
        ArrayList<Integer> csaIDs = new ArrayList<>();
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                csaIDs.add(rs.getInt("CallServiceAgentID"));
                System.out.println("!Info!----- Successfully got the csa ID -----!Info!");
            } 
            
            return csaIDs;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getListCSAIDs) Error, while trying to get a list of csa IDs: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following checks if the specialization that the user selected does exist in the database and returns its ID
    public Integer checkSpecialisation(String specialisation) throws ClassNotFoundException
    {
        // Select statement used to see if the selected specialization does exist in the database
        String query = "SELECT * FROM \"Specialisation\" WHERE \"Category\" = ?";
        
        try(Connection conn  = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Used to store the found ID if any were found
            Integer specialisationID;
            
            psmt.setString(1, specialisation);
            
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                specialisationID = rs.getInt("SpecialisationID");
                
                System.out.println("!Info!----- Successfully got the specialisation ID -----!Info!");
                
                return specialisationID;
            } 
        }
        catch (SQLException e)
        {
            System.out.println("!E! (ConnectionProvider -> checkSpecialisation) Error, while trying to get the specialisation ID: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following inserts a new contract into the database and returns its ID
    public Integer setAndGetContractID(Integer specialisationID) throws ClassNotFoundException
    {
        // The following query adds a contract to the database and returns the new contract's ID
        String query = "INSERT INTO \"Contract\" (\"SpecialisationID\") VALUES(?) RETURNING \"ContractID\"";
        
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Used to store the created contract's ID
            Integer contractID;
            
            psmt.setInt(1, specialisationID);
            
            ResultSet rs = psmt.executeQuery();
            
            if(rs.next())
            {
                contractID = rs.getInt("ContractID");
                
                System.out.println("!Info!----- Successfully got the new contract ID -----!Info!");
                
                return contractID;
            }
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> setAndGetContractID) Error, while trying to set the new contract and get the its ID: " + e.getMessage() + " -----!E!");
        }
        
        
        return null;
    }
    
    // The following returns a specific client's details
    public Client_Details getClientDetails(Integer clientID) throws ClassNotFoundException
    {
        // Gets the client's details based on the indicated ID
        String query = "SELECT * FROM \"Client\" WHERE \"ClientID\" = ?";
        
        
        try(Connection conn  = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, clientID);
            
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                Integer addressID = rs.getInt("AddressID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String phoneNumber = rs.getString("Phone_Number");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                
                Client_Details cd = new Client_Details(clientID, addressID, firstName, lastName, phoneNumber, email, password);
                
                System.out.println("!Info!----- Successfully found the client. -----!Info!");
                
                return cd;
            }
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getClientDetails) Error, while trying to get client details: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following is used to update a client's password in the database
    public void updateClientPass(String newPass, Integer clientID)throws ClassNotFoundException
    {
        // The query is used to update a client's password in the database
        String query = "UPDATE \"Client\" SET \"Password\" = ? WHERE \"ClientID\" = ?;";
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, newPass);
            psmt.setInt(2, clientID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated the password of client: " + clientID + " -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateClientPass) Error, while trying to update client password: " + e.getMessage() + "-----!E!");
        }
    }
    
    // The following is used to get an address based on a given address ID
    public Address getAddress(Integer addressID) throws ClassNotFoundException
    {
        // The query checks for the address based on the given address ID
        String query = "SELECT * FROM \"Address\" WHERE \"AddressID\" = ?";
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, addressID);
            
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next())
            {
                String country = rs.getString("Country");
                String state = rs.getString("State");
                String city = rs.getString("City");
                String streetName = rs.getString("Street_Name");
                
                System.out.println("!Info!----- Successfully found the address: " + addressID + " -----!Info!");
                
                return new Address(addressID, country, state, city, streetName);
            }
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getAddress) Error, while trying to get address: " + e.getMessage() + " -----!E!");
        }
        
        return null;
    }
    
    // The following is used to update a client's information
    public void updateClientInfo(Integer clientID, String firstName, String lastName, String phoneNumber, String email) throws ClassNotFoundException
    {
        // The query is used to update the client's information in the database
        String query = "UPDATE \"Client\" SET \"First_Name\"=?, \"Last_Name\"=?, \"Phone_Number\"=?, \"Email\"=? WHERE \"ClientID\" = ?;";
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, firstName);
            psmt.setString(2, lastName);
            psmt.setString(3, phoneNumber);
            psmt.setString(4, email);
            psmt.setInt(5, clientID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated the info of client: " + clientID + " -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateClientInfo) Error, while trying to update the client's info in the database: " + e.getMessage() + " -----!E!");
        }
    }
    
    // The following is used the update a client's address
    public void updateClientAddress(Integer addressID, String country, String state, String city, String streetName) throws ClassNotFoundException
    {
        // The following query is used to update a specified address in the database
        String query = "UPDATE \"Address\" SET \"Country\"=?, \"State\"=?, \"City\"=?, \"Street_Name\"=? WHERE \"AddressID\"=?;";
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, country);
            psmt.setString(2, state);
            psmt.setString(3, city);
            psmt.setString(4, streetName);
            psmt.setInt(5, addressID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated client's address info. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateClientAddress) Error, while trying to update the client address info in the database: " + e.getMessage() + " -----!E!");
        }
    }
    
    // Sets a service's status to ongoing
    public void updateServiceToOngoing(Integer serviceID) throws ClassNotFoundException
    {
        // The query updates a specified service's status to ongoing
        String query = "UPDATE \"Services\"SET \"Status\"=? WHERE \"ServiceID\"=?;";
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, "Ongoing");
            psmt.setInt(2, serviceID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated service state to ongoing. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateServiceToOngoing) Error, while trying to set the service state to ongoing: " + e.getMessage() + " -----!E!");
        }
    }
    
    // Sets a service's status to ongoing
    public void updateServiceToDeclined(Integer serviceID) throws ClassNotFoundException
    {
        // The query updates a specified service's status to ongoing
        String query = "UPDATE \"Services\"SET \"Status\"=? WHERE \"ServiceID\"=?;";
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, "Declined");
            psmt.setInt(2, serviceID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated service state to declined. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateServiceToDeclined) Error, while trying to set the service state to declined: " + e.getMessage() + " -----!E!");
        }
    }
    
    // Sets a service's status to ongoing
    public void updateServiceToComplete(Integer serviceID) throws ClassNotFoundException
    {
        // The query updates a specified service's status to ongoing
        String query = "UPDATE \"Services\"SET \"Status\"=? WHERE \"ServiceID\"=?;";
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setString(1, "Completed");
            psmt.setInt(2, serviceID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully updated service state to completed. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> updateServiceToDeclined) Error, while trying to set the service state to completed: " + e.getMessage() + " -----!E!");
        }
    }
    
    // -------------------- Important Note ------------------------------
    // The following is not the correct way of setting the technician to a service
    // The following adds a technician to a indicated service
    public void addTechnicianToService(Integer serviceID, Integer technicianID, LocalDate requestedDate) throws ClassNotFoundException
    {
        // The query sets a technician to a service
        String query = "INSERT INTO \"Service Technician\" (\"ServiceID\", \"TechnicianID\", \"Requested_Date\") VALUES (?, ?, ?);";
        
        
        
        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Formats the local date to a sql date
            Date sql_RequestedDate = Date.valueOf(requestedDate);
            
            psmt.setInt(1, serviceID);
            psmt.setInt(2, technicianID);
            psmt.setDate(3, sql_RequestedDate);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully added technician to service. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> addTechnicianToService) Error, while trying to add a technician to service: " + e.getMessage() + " -----!E!");
        }
    }
    
    // Step 2: Add the ability insert a survey (Michael's part)
    
    // Step 3: Add a search ability to search for services
    
    // Step 4: Add the ability to log problems and insert them into the database
    
    // Step 5: Retrieve the user's information and display it to the profile page. (Might be able to use the session data)
    
    // Step 6: Add the system admin
    
    // Step 7: Create their environment.
    
 public ArrayList<AR_TechnicianTask> GetTechTasks(String Email)throws ClassNotFoundException
    {
        TechEmail= Email;
    String sql = "SELECT \"Priority\", \"Status\", \"Requested_Date\", \"ServiceID\" FROM \"TechTaskPageView\" WHERE \"Email\" ="+ "'"+Email+"'";    
        try (Connection conn = getCon();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

           ArrayList<AR_TechnicianTask> TaskList = new ArrayList<AR_TechnicianTask>(); 

            // Populates the list with User objects
            while (rs.next())
            {
                //Initialize
                AR_TechnicianTask task = new AR_TechnicianTask();
                
                //assign selected values to variables
                Integer sServiceID = rs.getInt("ServiceID");
                String sPriority = rs.getString("Priority");
                String sStatus = rs.getString("Status");
                java.sql.Date Rdate = rs.getDate("Requested_Date");   
                
                //assign variables to object instance values
                task.setServiceID(sServiceID)
                        .setPriority(sPriority)
                        .setStatus(sStatus)
                        .setRequestedDate((java.util.Date)Rdate)
                        .setEmail(Email);
                
                //add the task instance to the list of tasks
                TaskList.add(task);   
            }
           
            return TaskList;
            
        } catch (SQLException ex) {
            System.out.println("Could not validate login: " + ex.getMessage());
        } 
        
         return null;
    }
    
    public void UpdateProfile(int ID,String PhoneNumber, String Password) throws ClassNotFoundException
      {
        
        Connection conn;
       
        
        try{
            conn = getCon();  
            Statement st = conn.createStatement();
            
         String UpdateQuery = "Update \"Technician\" SET  \"Phone_Number\"="+ "'"+PhoneNumber+"'"+""
                 + ", \"Password\"="+ "'"+Password+"'"+" WHERE \"TechnicianID\" = "+ "'"+ID+"'";
          
        
            st.executeUpdate(UpdateQuery);
            System.out.println("Data has updated: " );
            conn.close();
            
            
        }catch(SQLException ex)
        {
           System.out.println("Could not add the data: "+ ex.getMessage());
        }
      }
    
        public ArrayList<Integer> getServicesOfTechnician(Integer techID) throws ClassNotFoundException
        {
            String query = "SELECT \"ServiceID\" FROM \"Service Technician\" WHERE \"TechnicianID\" = ?";
            ArrayList<Integer> techServices = new ArrayList<>();
            try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
            {
                psmt.setInt(1, techID);
 
                ResultSet rs = psmt.executeQuery();
 
                while(rs.next())
                {
                    System.out.println("!Info!----- Got a service -----!Info!");
                    techServices.add(rs.getInt("ServiceID"));
                }

                return techServices;
            }
            catch (SQLException e)
            {
                System.out.println("!E!----- (ConnectionProvider -> getServicesOfTechnician)Error, while trying to get technician services: " + e.getMessage() + "-----!E!");
            }
            return null;
        }
    
    
    
    
    public ArrayList<String[]> getServiceForTech(ArrayList<Integer> serviceID_List) throws ClassNotFoundException
        {
            String query = "SELECT * FROM \"queryView\" WHERE \"ServiceID\" = ?";
            ArrayList<String[]> serviceDetailsForTech = new ArrayList<>();
            try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
            {
                for(var serviceID : serviceID_List)
                {
                    psmt.setInt(1, serviceID);
 
                    ResultSet rs = psmt.executeQuery();
 
                    while(rs.next())
                    {
                        System.out.println("!Info!----- Got a technician -----!Info!");
                        serviceDetailsForTech.add(new String[] {rs.getString("ServiceID"), rs.getString("Service_Title"), rs.getString("First_Name"), rs.getString("Last_Name")});
                    }
                }
                return serviceDetailsForTech;
            }
            catch (SQLException e)
            {
                System.out.println("!E!----- (ConnectionProvider -> getServiceForTech)Error, while trying to get technicians that belong to a specialisation: " + e.getMessage() + "-----!E!");
            }
            return null;
        }
    
    
    
    
    public void InsertContactPageQuery (Integer ServiceID,Integer TechID, String Query) throws ClassNotFoundException
    {
    String sql = "INSERT INTO \"Service Query\" (\"ServiceID\", \"Description\" , \"Requested_Date\", \"TechnicianID\") VALUES (?, ?, ?, ?);";
    LocalDate localDate = LocalDate.now();
    Date date = Date.valueOf(localDate);
    try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(sql);)
        {
            // Formats the local date to a sql date
             
            
            psmt.setInt(1, ServiceID);
            psmt.setString(2, Query);
            psmt.setDate(3, date );
            psmt.setInt(4, TechID);
            
            psmt.execute();
            
            System.out.println("!Info!----- Successfully added technician to service Queries. -----!Info!");
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> addTechnicianToQueryService) Error, while trying to add a technician to service: " + e.getMessage() + " -----!E!");
        }
    }
    
        public void ContactPageProvideQuery(Integer ServiceID,String Query) throws ClassNotFoundException
        {
               String sql = "SELECT  \"ServiceID\" FROM \"Services\" WHERE \"ServiceID\" ="+ "'"+ServiceID+"'";  
               String sql2 = "SELECT  \"TechnicianID\" FROM \"Technician\" WHERE \"Email\" ="+ "'"+TechEmail+"'";
        try (Connection conn = getCon();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             
             PreparedStatement pstmt2 = conn.prepareStatement(sql2);
             ResultSet rs2 = pstmt2.executeQuery();) {

        java.sql.Date DateOfQuery = java.sql.Date.valueOf(LocalDate.now()); 
        
        String sServiceID = null;
        
        boolean containServiceID = false;    
            while (rs.next())
            {
                sServiceID = rs.getString("ServiceID");
                 if (sServiceID != null)
                 {
                  containServiceID = true;
                  break;
                 }
            }
            
            int TechID = 0;
    if (rs2.next()) {
         TechID = rs2.getInt("TechnicianID");
    }      
            if (containServiceID == true ) {
          String insertQuery = "INSERT INTO \"Service Query\" (\"ServiceID\", \"Description\", \"Requested_Date\", \"TechnicianID\""
                  + ") VALUES (?,?,?,?)";
          PreparedStatement pstmt3 = conn.prepareStatement(insertQuery);
           
            pstmt3.setInt(1,ServiceID);
            pstmt3.setString(2, Query);
            pstmt3.setDate(3, DateOfQuery);
            pstmt3.setInt(4, TechID);
            
            
            pstmt3.executeUpdate();
            }else{System.out.println("Enter valid Service ID");}
            
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        }
        public void ContactPageDetails()
        {
        
        }
        
        
        
        public AR_TechnicianDetails ViewTechDetails(String techEmail) throws ClassNotFoundException
        {
           AR_TechnicianDetails TechDetails = new AR_TechnicianDetails();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            
        String sql1 = "SELECT  \"TechnicianID\",  \"First_Name\",  \"Last_Name\",  \"Phone_Number\" ,  \"Email\"FROM \"Technician\" WHERE \"Email\" = ? ";  
        try (Connection conn = getCon();) {
            
            
            PreparedStatement pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, techEmail);
                
            ResultSet rs = pstmt.executeQuery();
            
            rs.next();
            
           
            
            // Populates the list with User objects
            
                Integer TechID = rs.getInt("TechnicianID");
                
                String Name = rs.getString("First_Name");
                String Surname = rs.getString("Last_Name");
                String email = rs.getString("Email");
                String Number = rs.getString("Phone_Number");
               
                TechDetails.setTechID(TechID)
                        .setName(Name)
                        .setSurname(Surname)
                        .setEmail(email)
                        .setNumber(Number);
                 
            
           
            return TechDetails;
            
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        
         return null;
        }
        

        public AR_ClientDetails ViewClientDetails(Integer ServiceID) throws ClassNotFoundException
        {
            
        String sql1 = "SELECT  \"Country\",\"State\",\"City\",\"Street_Name\",\"Requested_Date\",\"First_Name\",\"Last_Name\",\"Description\",\"Phone_Number\" FROM \"ClientDetailsView\" WHERE \"ServiceID\" ="+ "'"+ServiceID+"'";  
         
        try (Connection conn = getCon();
             PreparedStatement pstmt = conn.prepareStatement(sql1);
             ResultSet rs = pstmt.executeQuery()) {
            
          AR_ClientDetails details = new AR_ClientDetails();

            // Populates the list with User objects
           
           rs.next();
           
                String Country = rs.getString("Country");
                String State = rs.getString("State");
                String City = rs.getString("City");
                String Street = rs.getString("Street_Name");
                java.util.Date date = rs.getDate("Requested_Date");
                String Name = rs.getString("First_Name");
                String Surname = rs.getString("Last_Name");
                String Description = rs.getString("Description");
                String Number = rs.getString("Phone_Number");
                
               details.setCity(City).setCountry(Country).setDescription(Description)
                      .setName(Name).setNumber(Number).setServiceID(ServiceID).setState(State)
                      .setStreet(Street).setSurname(Surname).setdate(date);
               System.out.println("doe goed is geset");
           
            return details;
            
        } catch (SQLException ex) {
            System.out.println("error: " + ex.getMessage());
        }
        
         return null;
        }
      
       public void UpdateStatus(String Status, Integer ServiceID) throws ClassNotFoundException
       {
        Connection conn;
        try{
            conn = getCon();  
            Statement st = conn.createStatement();
           // Status status = new Status();
         String UpdateQuery = "Update \"Services\" SET \"Status\"="+ "'"+Status+"'"+" WHERE \"ServiceID\" = "+ "'"+ServiceID+"'";
          
        
            st.executeUpdate(UpdateQuery);
            System.out.println("Data has been updated");
            conn.close();
            
            
        }catch(SQLException ex)
          {
           System.out.println("Could not add the data: "+ ex.getMessage());
          }
        }
       
       
      public Integer SaveNotes(AR_Notes note) throws ClassNotFoundException
       {
           try{
            Connection conn = getCon();
            String insertQuery = "INSERT INTO \"Note\" (\"Note_Text\""
                  + ") VALUES (?) RETURNING \"NoteID\"";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, note.getNoteText());
            
            Integer NoteID = null;
            
            ResultSet rs = pstmt.executeQuery();
           
            while(rs.next())
            {
                System.out.println("Successfully got the service address ID");
                NoteID = rs.getInt("NoteID");
                return NoteID;
            }
            note.setNoteID(NoteID);
            
            
             conn = getCon();  
            Statement st = conn.createStatement();
            
         String UpdateQuery = "Update \"Services\" SET \"NoteID\"="+ "'"+NoteID+"'"+" WHERE \"ServiceID\" = "+ "'"+note.getNoteID()+"'";
          
        
            st.executeUpdate(UpdateQuery);
            System.out.println("Data has been updated");
            conn.close();
            
            
           }catch(SQLException ex)
           {
               System.out.println("error: " + ex);
           }
          
         return null; 
       }
    public AR_Notes getNotes (Integer NoteID) throws ClassNotFoundException
    {
      String sql1 = "SELECT  \"Note_text\" FROM \"Note\" WHERE \"NoteID\" ="+ "'"+NoteID+"'";  
         try (Connection conn = getCon();
             PreparedStatement pstmt = conn.prepareStatement(sql1);
             ResultSet rs = pstmt.executeQuery()) {

             rs.first();
              Integer noteID = rs.getInt("Note_text");
              
         AR_Notes note = new AR_Notes();
         
         note.setNoteID(noteID);
         return note;
         }catch(SQLException ex)
           {
               System.out.println("error: " + ex);
                return null;
           }
        
      
    }
    
    // The following returns a ArrayList of service objects that contains the information required on the client's environment
    public ArrayList<CSA_Service> getCSAService(Integer csaID) throws ClassNotFoundException
    {
        // A view was created to get the specific data that will be displayed to the client
        String query = "SELECT * FROM \"clientServiceView\" WHERE \"CallServiceAgentID\" = ?";
        // In the event of a client having multiple services, we store them in an ArrayList
        ArrayList<CSA_Service> services = new ArrayList<>();

        // The following try-with-resources will close the connection to the database on completion or in the event of an error.
        try (Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            // Entered Client ID
            psmt.setInt(1, csaID);

            // Cotains the items that have been returned by the query
            ResultSet rs = psmt.executeQuery();

            // The following will add all the services that were found to the created ArrayList
            while(rs.next())
            {
                // Service
                Integer serviceID = rs.getInt("ServiceID"); // 0
                Integer clientID = rs.getInt("ClientID"); // 1
                String serviceTitle = rs.getString("Service_Title"); // 2
                String skillCategory = rs.getString("Category"); // 3
                String description = rs.getString("Description"); // 4
                String priority = rs.getString("Priority"); // 5
                String status = rs.getString("Status"); // 6

                // Address
                String country = rs.getString("Country"); // 7
                String state = rs.getString("State"); // 8
                String city = rs.getString("City"); // 9
                String streetName = rs.getString("Street_Name"); // 10

                // Technician
                String firstName = rs.getString("First_Name"); // 11
                String lastName = rs.getString("Last_Name"); // 12

                System.out.println("!Info!----- Service ID: " + serviceID + " -----!Info!");

                CSA_Service serviceInfo = new CSA_Service(serviceID, clientID, serviceTitle, skillCategory, description, priority, status, country, state, city, streetName, firstName, lastName);

                services.add(serviceInfo);
            }

            return services;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getCSAService) Error, while trying to get service info: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
        
    public ArrayList<Integer> getSpecialTech(Integer specialisationID) throws ClassNotFoundException
    {
        String query = "SELECT \"TechnicianID\" FROM \"specialisationOfTechnicianView\" WHERE \"SpecialisationID\" = ?";
        ArrayList<Integer> techID_List = new ArrayList<>();

        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, specialisationID);

            ResultSet rs = psmt.executeQuery();

            while(rs.next())
            {
                System.out.println("!Info!----- Got a technician -----!Info!");
                techID_List.add(rs.getInt("TechnicianID"));
            }

            return techID_List;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getSpecialTech)Error, while trying to get technicians that belong to a specialisation: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
        
    public Integer getSpecial(Integer contractID) throws ClassNotFoundException
    {
        String query = "SELECT \"SpecialisationID\" FROM \"Contract\" WHERE \"ContractID\" = ?";


        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, contractID);

            ResultSet rs = psmt.executeQuery();

            while(rs.next())
            {
                System.out.println("!Info!----- Got a contract -----!Info!");
                return rs.getInt("SpecialisationID");
            }


        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getSpecial)Error, while trying to get specialisation from contract table: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
        
    public Service getServiceForAddingATech(Integer serviceID) throws ClassNotFoundException
    {
        String query = "SELECT * FROM \"Services\" WHERE \"ServiceID\" = ?";


        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, serviceID);

            ResultSet rs = psmt.executeQuery();

            while(rs.next())
            {
                System.out.println("!Info!----- Got a technician -----!Info!");
                return new Service(rs.getInt("ServiceID"), rs.getInt("ContractID"));
            }

            return null;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getSpecialTech)Error, while trying to get technicians that belong to a specialisation: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
        
    public ArrayList<Integer[]> numOfServicesForTech(ArrayList<Integer> technicianID_List) throws ClassNotFoundException
    {
        String query = "SELECT COUNT(*) FROM \"Service Technician\" WHERE \"TechnicianID\" = ?";
        ArrayList<Integer[]> techAndNumOfServices = new ArrayList<>();

        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {

            for(var tech : technicianID_List)
            {
                psmt.setInt(1, tech);

                ResultSet rs = psmt.executeQuery();

                while(rs.next())
                {
                    System.out.println("!Info!----- Got a technician -----!Info!");
                    techAndNumOfServices.add(new Integer[] {tech, rs.getInt(1)});
                }
            }

            return techAndNumOfServices;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> numOfServicesForTech)Error, while trying to get number of services that belong to a specific technician: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
        
    public CSA_Service getServiceForCSA_Details(Integer serviceID) throws ClassNotFoundException
    {
        String query = "SELECT * FROM \"csaServiceDetailsView\" WHERE \"ServiceID\" = ?";


        try(Connection conn = getCon(); PreparedStatement psmt = conn.prepareStatement(query);)
        {
            psmt.setInt(1, serviceID);

            ResultSet rs = psmt.executeQuery();

            while(rs.next())
            {
                System.out.println("!Info!----- Got a technician -----!Info!");
                return new CSA_Service(rs.getInt("ServiceID"), rs.getInt("ClientID"), rs.getString("client_first_name"), rs.getString("client_Last_Name"), rs.getString("Phone_Number"), rs.getString("Email"), rs.getString("Service_Title"), rs.getString("Category"), rs.getString("Description"), rs.getString("Priority"), rs.getString("Status"), rs.getString("Country"), rs.getString("State"), rs.getString("City"), rs.getString("Street_Name"), rs.getString("tech_first_name"), rs.getString("tech_last_name"));
            }

            return null;
        }
        catch (SQLException e)
        {
            System.out.println("!E!----- (ConnectionProvider -> getSpecialTech)Error, while trying to get technicians that belong to a specialisation: " + e.getMessage() + "-----!E!");
        }

        return null;
    }
    
}

