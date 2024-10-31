// The following servlet handles any changes that are being made to the client's profile
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.ClientProfileLogic;


@WebServlet("/client_ChangeProfile")
public class ChangeProfileServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        // The following ArrayList is used to store the changed user information
        ArrayList<String[]> fullClientDetails = new ArrayList<>();
        
        // Used to update session data
        HttpSession session = request.getSession();
        
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                // Client details (Inputs)
                String clientID = request.getParameter("txt_clientID");
                String firstName = request.getParameter("txt_firstName");
                String lastName = request.getParameter("txt_lastName");
                String phoneNumber = request.getParameter("txt_phoneNumber");
                String email = request.getParameter("txt_email");

                // Client Address (Inputs)
                String country = request.getParameter("drop_country");
                String state = request.getParameter("drop_state");
                String city = request.getParameter("drop_city");
                String streetName = request.getParameter("txt_StreetName");


                // Gets the ID number of the client's ID
                Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);

                ClientProfileLogic cpl = new ClientProfileLogic();

                try
                {
                    String[] originalDetails = cpl.clientDetails(clientIDNum);

                    Integer addressID = Integer.parseInt(originalDetails[1]);

                    String[] addressInfo = cpl.clientAddress(addressID);

                    //String[] details = {"C_" + clientID, addressID, firstName, lastName, phoneNum, email};


                    // The following checks if the user entered all the required information
                    if(!firstName.isBlank() && !lastName.isBlank() && !phoneNumber.isBlank() && !email.isBlank() && !country.equals("None") && !state.equals("None") && !city.equals("None") && !streetName.isBlank())
                    {
                        // Checks if the client changed their details
                        if(!originalDetails[2].equals(firstName) || !originalDetails[3].equals(lastName) || !originalDetails[4].equals(phoneNumber) || !originalDetails[5].equals(email))
                        {
                            // Update client
                            cpl.updateClientDetails(clientIDNum, firstName, lastName, phoneNumber, email);
                            String[] messageInfo = {"Successfully updated client info", "green"};
                            request.setAttribute("message", messageInfo);

                            // Sets new originalDetails
                            originalDetails[2] = firstName;
                            originalDetails[3] = lastName;
                            originalDetails[4] = phoneNumber;
                            originalDetails[5] = email;

                            // Add changes to session storage
                            clientSessionDetails[2] = firstName;
                            clientSessionDetails[3] = lastName;
                            clientSessionDetails[4] = phoneNumber;
                            clientSessionDetails[5] = email;
                        }

                        // Checks if the client changed their address information
                        if(!addressInfo[1].equals(country) || !addressInfo[2].equals(state) || !addressInfo[3].equals(city) || !addressInfo[4].equals(streetName))
                        {
                            // Update Address
                            cpl.updateClientAddress(addressID, country, state, city, streetName);
                            String[] messageInfo = {"Successfully updated client info", "green"};
                            request.setAttribute("message", messageInfo);

                            // Sets new originalDetails
                            addressInfo[1] = country;
                            addressInfo[2] = state;
                            addressInfo[3] = city;
                            addressInfo[4] = streetName;
                        }
                    }
                    else
                    {
                        // Warning
                        String[] messageInfo = {"Missing values, please make sure to enter all the required information.", "red"};
                        request.setAttribute("message", messageInfo);
                    }

                    // Adds new details and address to the ArrayList to be displayed on the profile page
                    fullClientDetails.add(originalDetails);
                    fullClientDetails.add(addressInfo);

                    session.setAttribute("userDetails", clientSessionDetails);
                    request.setAttribute("clientInfo", fullClientDetails);
                    request.getRequestDispatcher("./View/client_ProfilePage.jsp").forward(request, response);
                }
                catch (Exception e)
                {
                    System.out.println("!E!----- (ChangeProfileServlet) Error, while trying to update client profile information: " + e.getMessage() + " -----!E!");
                }
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
