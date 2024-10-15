// Note: Add own PostgreSQL database information
// The following is used to handle database queries and connection

package DataAccess;

import java.sql.*;

public class ConnectionProvider {
    
    // Constructor
    public ConnectionProvider()
    {}
    
    // Enter own database info
    // ------------------------------------------------------------------------------------------------------------
    String username = "postgres";
    String pwd = "";
    // ------------------------------------------------------------------------------------------------------------
    
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
                System.out.println("Connection to ApexCareDB was successfully created");
            }
            
        }
        catch (SQLException e)
        {
            // In the event of an error occurring
            System.out.println("Error: " + e.getMessage());
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
        
        // Used for connection
        Connection conn;
        // Use to handle inputs
        PreparedStatement psmt;
        
        try
        {
            // Get the connection
            conn = getCon();
            
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
                    System.out.println("---------- Found a user! ----------");
                    
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
                            System.out.println("---------- The user is a Client ----------");
                            
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
                            System.out.println("---------- The user is a Technician ----------");
                            
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
                            System.out.println("---------- The user is a Call Service Agent ----------");
                            
                            // Items required for this user type
                            String technicianID = result.getString("CallServiceAgentID");
                            
                            // Return a array of details
                            String[] details = {"CSA_" + technicianID, firstName, lastName, phoneNum, email};
                            return details;
                        }
                        default:
                        {
                            // In the event a user was found but they have no type assigned to them,
                            // it will not give them access to any pages
                            System.out.println("The user does not exist");
                            return null;
                        }
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        return null;
    }
    
}
