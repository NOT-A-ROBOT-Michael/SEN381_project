// The following page handles access to the home pages of the client, technician and call service agent

package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import businesslogiclayer.logic.TechLogic;
import businesslogiclayer.logic.ServiceLogic;

// The following servlet can be used by the client-, technician- and call service agent page
@WebServlet(urlPatterns = {"/client_HomePage", "/technician_HomePage", "/csa_HomePage"})
public class UserServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        TechLogic techTask = new TechLogic();
        // Used to get the session info
        HttpSession session = request.getSession(false);
        
        ServiceLogic sl = new ServiceLogic();
        
        
        
        try
        {
            String[] userDetails = (String[]) session.getAttribute("userDetails");
                

            if(userDetails != null)
            {
                // Gets the user ID in the session data
                String userID = userDetails[0];

                // The following gets the user type from the ID
                String userType = userID.split("_")[0];

                // The following gets the user ID number from the user ID
                Integer userIDNum = Integer.parseInt(userID.split("_")[1]);

                // The following gets the basic service information to be displayed in the client home page.
                ArrayList<String[]> newServices = sl.serviceDetails(userIDNum);
                
                ArrayList<String[]> services ;
                
                // The following switch determines which jsp page needs to be loaded
                switch(userType)
                {
                    case "C":
                    {
                        // Client
                        request.setAttribute("serviceInfo", newServices);
                        request.getRequestDispatcher("./View/client_HomePage.jsp").forward(request, response);
                        break;
                    }
                    case "T":
                    {
                        // Technician
                        String email = userDetails[5];
                        services = techTask.getTechnicians(email);
                        request.setAttribute("serviceInfo", services);
                        request.getRequestDispatcher("./View/technician_HomePage.jsp").forward(request, response);
                        break;
                    }
                    case "CSA":
                    {
                        // The following gets the basic service information to be displayed in the client home page.
                        ArrayList<String[]> csaServices = sl.serviceDetails_CSA(userIDNum);
                        
                        // Call Service Agent
                        request.setAttribute("serviceInfo", csaServices);
                        request.getRequestDispatcher("./View/csa_HomePage.jsp").forward(request, response);
                        break;
                    }
                    default:
                    {
                        // A warning in the event a user being called that does not exist
                        System.out.println("!Warning!----- User type does not exist. -----!Warning!");
                        break;
                    }
                }
            }
            else
            {
                response.sendRedirect("./?Error=Please log in first");
            }
                
        }
        catch (Exception e)
        {
            System.out.println("!E!----- (UserServlet) Error, getting basic service information: " + e.getMessage() + " -----!E!");
        }
        
            
    }
}
