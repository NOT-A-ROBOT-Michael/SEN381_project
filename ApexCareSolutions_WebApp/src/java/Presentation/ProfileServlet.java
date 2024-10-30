// The following page will run when the client profile page loads

package Presentation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.ClientProfileLogic;

@WebServlet("/client_ProfilePage")
public class ProfileServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        // Gets the client's details from the session storage
        String[] clientDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                ArrayList<String[]> fullClientDetails = new ArrayList<>();
                
                // Gets the ID from the details
                String clientID = clientDetails[0];
                // Gets the ID number from the ID
                Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);

                try
                {
                    // Logic to get client details from database
                    ClientProfileLogic cpl = new ClientProfileLogic();

                    // Gets all the client's information
                    String[] clientInfo = cpl.clientDetails(clientIDNum);

                    // Creates a variable for the client's address ID in the clientInfo Array
                    Integer clientAddressID = Integer.parseInt(clientInfo[1]);

                    // Stores the address details in an array to be used by the web page
                    String[] addressInfo = cpl.clientAddress(clientAddressID);

                    // Adds the Client Details and Address Information to the fullClientDetails ArrayList
                    fullClientDetails.add(clientInfo);
                    fullClientDetails.add(addressInfo);

                    // Returns the information and loads the client profile page
                    request.setAttribute("clientInfo", fullClientDetails);
                    request.getRequestDispatcher("./View/client_ProfilePage.jsp").forward(request, response);
                }
                catch (Exception e)
                {
                    System.out.println("!E!----- (ProfileServlet) Error, while trying to retrieve client info: " + e.getMessage() + " -----!E!");
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
