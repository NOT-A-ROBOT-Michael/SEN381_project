// The following page handles access to the home pages of the client, technician and call service agent

package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// The following servlet can be used by the client-, technician- and call service agent page
@WebServlet(urlPatterns = {"/client_HomePage", "/technician_HomePage", "/csa_HomePage"})
public class UserServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // The following is used to avoid a user from having multiple sessions of the application
        // It only checks if there is an existing session
        HttpSession session = request.getSession(false);
        
        if(session != null && session.getAttribute("userDetails") != null)
        {
            String userType = (String) session.getAttribute("userType");
            
            // Determine the type of the user
            switch(userType)
            {
                // Client
                case "C":
                {
                    System.out.println("The user is signed in an can access the Client page");
                    request.getRequestDispatcher("client_HomePage.jsp").forward(request, response);
                    break;
                }
                // Technician
                case "T":
                {
                    System.out.println("The user is signed in an can access the Technician page");
                    request.getRequestDispatcher("technician_HomePage.jsp").forward(request, response);
                    break;
                }
                // Call Service Agent
                case "CSA":
                {
                    System.out.println("The user is signed in an can access the Call Service Agent page");
                    request.getRequestDispatcher("csa_HomePage.jsp").forward(request, response);
                    break;
                }
                // In the event of a user type not existing
                default:
                {
                    System.out.println("The type does not exist.");
                    break;
                }
            }
            
        }
        else
        {
            // If the user details does not exist, they have to be redirected to the index page
            response.sendRedirect("./?error=Please log in first");
        }
        
    }
    
    
}