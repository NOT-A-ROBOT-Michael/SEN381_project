
package Presentation;

import sen381_project.Data_Layer.ConnectionProvider;
import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/submit_request")
public class LogRequestServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Adding new service");
        
        HttpSession session = request.getSession();
        
        // Service Title
        String serviceTitle = request.getParameter("txt_ProblemTitle");
        
        // Service Categories
        String serviceType = request.getParameter("drop_ServiceCategories");
        
        // Service Description
        String description = request.getParameter("txtB_Description");
        
        // Service Urgency
        String urgency = request.getParameter("drop_Urgency");
        
        // Service Address
        
        // Country
        String country = request.getParameter("drop_country");
        
        // State
        String state = request.getParameter("drop_state");
        
        // City
        String city = request.getParameter("drop_city");
        
        // Street Name
        String streetName = request.getParameter("txt_StreetName");
        
        System.out.println("Inputs: " + serviceTitle + ", " +
                serviceType + ", " +
                description + ", " +
                urgency + ", " +
                country + ", " +
                state + ", " +
                city + ", " +
                streetName);
        
        if(!serviceTitle.isBlank() && !serviceType.equals("None") && !description.isBlank() && !urgency.equals("None") && !country.equals("None") && !state.equals("None") && !city.equals("None") && !streetName.isBlank())
        {
            String[] client_Details = (String[]) session.getAttribute("userDetails");

            if(client_Details != null)
            {
                // Set a random call service agent to the service
                Integer csa_ID = getCSA();
                
                if(csa_ID != null)
                {
                    // Gets the user ID
                    String fullID = client_Details[0];

                    Integer clientAddressID = Integer.parseInt(client_Details[1]);

                    // Set ID num
                    Integer clientID = Integer.parseInt(fullID.split("_")[1]);

                    // Check if the password that the user entered exists and if not add a new address.
                    boolean atClient = checkAddress(clientAddressID, country, state, city, streetName);

                    Integer addressID;

                    if(atClient)
                    {
                        System.out.println("Service located at client's address");
                        addressID = clientAddressID;
                    }
                    else
                    {
                        System.out.println("Create a new address");
                        addressID = addAddressAndGetID(country, state, city, streetName);
                    }


                    if(addressID != null)
                    {


                        // Add contract ID too!
                        Integer contractID = getContractID(serviceType);

                        // Date with the format YYYY-MM-DD
                        LocalDate currentDate = LocalDate.now();

                        try
                        {
                            ConnectionProvider cp = new ConnectionProvider();

                            cp.addServiceFromClient(csa_ID, contractID, clientID, addressID, description, "Pending", urgency, currentDate, serviceTitle);

                            String[] messageInfo = {"Successfully added new service.", "green"};
                            request.setAttribute("message", messageInfo);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Something went wrong while trying to add a new service from the client side: " +e.getMessage());
                        }
                    }
                }
                else
                {
                    String[] messageInfo = {"No call service agents are available, please try again later.", "red"};
                    request.setAttribute("message", messageInfo);
                }
                    
            }
            else
            {
                String[] messageInfo = {"Missing client details, website might require a refresh.", "red"};
                request.setAttribute("message", messageInfo);
            }
            System.out.println("Service Info: " +
                    serviceTitle + ", " +
                    serviceType + ", " +
                    description + ", " +
                    urgency + ", " +
                    country + ", " +
                    state + ", " +
                    city + ", " +
                    streetName);
        }
        else
        {
            String[] messageInfo = {"Missing Values, please make sure to enter all the required information.", "red"};
            request.setAttribute("message", messageInfo);
        }
        
        try
        {
            
            ConnectionProvider cp = new ConnectionProvider();
            
            String[] clientDetails = (String[]) session.getAttribute("userDetails");
            
            Integer addressID = Integer.parseInt(clientDetails[1]);
            
            Address a = cp.getAddress(addressID);
            
            String[] addressInfo = a.getAddress();
        
            ArrayList<String> serviceTypes = cp.getTypeOfServices();
            
            for(var i : serviceTypes)
            {
                System.out.println(i);
            }
            
            request.setAttribute("addressInfo", addressInfo);
            request.setAttribute("serviceTypes", serviceTypes);
            
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to send the service types: " + e.getMessage());
        }
        
        
        request.getRequestDispatcher("./View/client_LogProblem.jsp").forward(request, response);
    }
    
    public Integer addAddressAndGetID(String country, String state, String city, String streetName)
    {
        try
        {
            ConnectionProvider cp = new ConnectionProvider();
            
            Integer addressID = cp.setAndGetAddressID(country, state, city, streetName);
            
            return addressID;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while handling the new service address: " + e.getMessage());
        }
        
        return null;
        
    }
    
    public boolean checkAddress(Integer addressID, String country, String state, String city, String streetName)
    {
        try
        {
            
            ConnectionProvider cp = new ConnectionProvider();
            
            Address a = cp.getAddress(addressID);
            
            String[] clientAddress = a.getAddress();
            
            if(country.equals(clientAddress[1]) && state.equals(clientAddress[2]) && city.equals(clientAddress[3]) && streetName.equals(clientAddress[4]))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while comparing client address with entered address: " + e.getMessage());
        }
        
        return false;
    }
    
    public Integer getCSA()
    {
        try
        {
            ConnectionProvider cp = new ConnectionProvider();
            ArrayList<Integer> csa_IDs = cp.getListCSA_IDs();
            
            Integer selectedCSA = null;

            if(!csa_IDs.isEmpty())
            {
                
                Integer numOfCSA = csa_IDs.size();
                Integer csaIndex = (int) (Math.random() * numOfCSA);
                
                selectedCSA = csa_IDs.get(csaIndex);

            }
            
            return selectedCSA;
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to get a list of the csa IDs: " + e.getMessage());
        }
        
        return null;
    }
    
    public Integer getContractID(String specialisation)
    {
        try
        {
            ConnectionProvider cp = new ConnectionProvider();
            
            Integer specialisationID = cp.checkSpecialisation(specialisation);
            
            if(specialisationID != null)
            {
                Integer contractID = cp.setAndGetContractID(specialisationID);
                
                return contractID;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong while trying to get the contract ID: " + e.getMessage());
        }
        
        return null;
    }
}
