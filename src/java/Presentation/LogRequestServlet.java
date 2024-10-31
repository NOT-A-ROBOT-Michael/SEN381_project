// The following handles the inputs of the request that the client logged and adds a new service if the information is correct
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.ClientRequestLogic;

@WebServlet("/submit_request")
public class LogRequestServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Used to access client details stored in the session storage
        HttpSession session = request.getSession();
        
        // Gets the client details from the session storage
        String[] client_Details = (String[]) session.getAttribute("userDetails");
        
        if(client_Details != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = client_Details[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                System.out.println("!Info!----- Trying to add a new service -----!Info!");
        
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

                System.out.println("!Info!----- Inputs: " + serviceTitle + ", " +
                        serviceType + ", " +
                        description + ", " +
                        urgency + ", " +
                        country + ", " +
                        state + ", " +
                        city + ", " +
                        streetName + " -----!Info!");

                ClientRequestLogic crl = new ClientRequestLogic();

                try 
                {
                    // The following checks if all the required information is inserted
                    if(!serviceTitle.isBlank() && !serviceType.equals("None") && !description.isBlank() && !urgency.equals("None") && !country.equals("None") && !state.equals("None") && !city.equals("None") && !streetName.isBlank())
                    {


                        // Set a random call service agent to the service
                        Integer csa_ID = crl.getCSA();

                        if(csa_ID != null)
                        {
                            // Gets the user ID
                            String fullID = client_Details[0];

                            // Set ID num
                            Integer clientID = Integer.parseInt(fullID.split("_")[1]);

                            // Gets the client's Address ID from the details stored in the session
                            Integer clientAddressID = Integer.parseInt(client_Details[1]);

                            // Check if the address that the user entered exists and if not add a new address.
                            boolean atClient = crl.checkAddress(clientAddressID, country, state, city, streetName);

                            Integer addressID;

                            // The following checks if the request is located at the client's address
                            if(atClient)
                            {
                                System.out.println("!Info!----- Service located at client's address -----!Info!");
                                addressID = clientAddressID;
                            }
                            else
                            {
                                System.out.println("!Info!----- Create a new address -----!Info!");
                                addressID = crl.addAddressAndGetID(country, state, city, streetName);
                            }

                            // Checks if the address ID exists, before moving on
                            if(addressID != null)
                            {
                                // Contract ID
                                Integer contractID = crl.getContractID(serviceType);

                                // Date with the format YYYY-MM-DD
                                LocalDate currentDate = LocalDate.now();

                                // Add a new service to the database

                                crl.addNewService(csa_ID, contractID, clientID, addressID, description, "Pending", urgency, currentDate, serviceTitle);
                                String[] messageInfo = {"Successfully added new service.", "green"};
                                request.setAttribute("message", messageInfo);
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
                        String[] messageInfo = {"Missing Values, please make sure to enter all the required information.", "red"};
                        request.setAttribute("message", messageInfo);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("!E!----- (LogRequestServlet) Error, while trying to add a new service from the client's side -----!E!");
                }



                try
                {
                    // Gets the client details from the session storage
                    String[] clientDetails = (String[]) session.getAttribute("userDetails");

                    // Gets the client's Address ID from the details stored in the session
                    Integer clientAddressID = Integer.parseInt(clientDetails[1]);

                    // Gets the client's address Information
                    String[] addressInfo = crl.clientAddress(clientAddressID);

                    // List of the types of services offered by the company
                    ArrayList<String> serviceTypes = crl.getServiceTypes();

                    // Adds the items to be displayed when the page is reloaded
                    request.setAttribute("addressInfo", addressInfo);
                    request.setAttribute("serviceTypes", serviceTypes);

                }
                catch (Exception e)
                {
                    System.out.println("!E!----- (LogRequestServlet) Error, while trying to set default values: " + e.getMessage() + " -----!E!");
                }

                // Directs the client back to the Log Problem page
                request.getRequestDispatcher("./View/client_LogProblem.jsp").forward(request, response);
            }
            else
            {
                // If the user is the wrong type they will receive an error.
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        else
        {
            // If they do not exist they will receive an error.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        
        
    }
}
