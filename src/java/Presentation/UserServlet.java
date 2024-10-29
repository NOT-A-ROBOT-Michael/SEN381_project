// The following page handles access to the home pages of the client, technician and call service agent

package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import sen381_project.Bussiness_Logic_Layer.Logic;
import jakarta.servlet.jsp.PageContext;

// The following servlet can be used by the client-, technician- and call service agent page
@WebServlet(urlPatterns = {"/client_HomePage", "/technician_HomePage", "/csa_HomePage"})
public class UserServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Hello from the client_HomePage servlet");
        
        HttpSession session = request.getSession(false);
        
        Logic logic = new Logic();
        
        String[] userDetails = (String[]) session.getAttribute("userDetails");
        // Gets the user ID
        String userID = userDetails[0];
        String userType = userID.split("_")[0];
        Integer userIDNum = Integer.parseInt(userID.split("_")[1]);
        
        ArrayList<String[]> services = logic.getServices(userIDNum);
        
        
        
        switch(userType)
        {
            case "C":
            {
                request.setAttribute("serviceInfo", services);
                request.getRequestDispatcher("/View/client_HomePage.jsp").forward(request, response);
                break;
            }
            case "T":
            {
                request.setAttribute("serviceInfo", services);
                request.getRequestDispatcher("/View/technician_HomePage.jsp").forward(request, response);
                break;
            }
            case "CSA":
            {
                request.setAttribute("serviceInfo", services);
                request.getRequestDispatcher("/View/csa_HomePage.jsp").forward(request, response);
                break;
            }
            default:
            {
                System.out.println("User type does not exist.");
                break;
            }
        }
    }
}
