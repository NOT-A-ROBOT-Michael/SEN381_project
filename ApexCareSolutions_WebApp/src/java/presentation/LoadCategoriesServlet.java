// The following page displayes the default values in the log problem page
package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import businesslogiclayer.logic.LoadCategoriesLogic;

@WebServlet("/client_LogProblem")
public class LoadCategoriesServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        LoadCategoriesLogic lcl = new LoadCategoriesLogic();
        
        
            // Gets the client details from the session storage
            String[] clientDetails = (String[]) session.getAttribute("userDetails");
            
            // Checks if the user exists
            if(clientDetails != null)
            {
                // Gets the client's Address ID from the details stored in the session
                String userType = clientDetails[0].split("_")[0];
                
                // Checks if the user belongs to the correct type
                if(userType.equals("C"))
                {
                    
                    try
                    {
                        // Gets the client's Address ID from the details stored in the session
                        Integer clientAddressID = Integer.parseInt(clientDetails[1]);

                        // Gets the client's address Information
                        String[] addressInfo = lcl.clientAddress(clientAddressID);

                        // List of the types of services offered by the company
                        ArrayList<String> serviceTypes = lcl.getServiceTypes();

                        // Adds the items to be displayed when the page is reloaded
                        request.setAttribute("addressInfo", addressInfo);
                        request.setAttribute("serviceTypes", serviceTypes);
                        request.getRequestDispatcher("./View/client_LogProblem.jsp").forward(request, response);
                        
                    }
                    catch (Exception e)
                    {
                        System.out.println("!E!----- (LoadCategoriesServlet) Error, while trying to send the service types: " + e.getMessage() + " -----!E!");
                    }
                }
                else
                {
                    // If the wrong user is trying to access the servlet.
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

