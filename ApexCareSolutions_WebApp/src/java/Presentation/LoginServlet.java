// The following page will handle the logic opperations
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import BusinessLogic.Logic;
import jakarta.servlet.http.HttpSession;

// The following servlet will run when the user clicks the submit button
@WebServlet("/user_login")
public class LoginServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
        Logic logic = new Logic();
        
        String[] userDetails = logic.setLoginStatus(email, pass);
        //HttpSession session = request.getSession(false);
        
        // The following requests a session
        HttpSession session = request.getSession();
            
        // Checks if the user has any details
        if(userDetails != null)
        {
            // Gets the user ID
            String userID = userDetails[0];
            // Gets the type of user
            String userType = userID.split("_")[0];
            
            
            
            // Gets the user type
            switch(userType)
            {
                // Client
                case "C":
                {
                    session.setAttribute("userType", userType);
                    session.setAttribute("userDetails", userDetails);
                    response.sendRedirect("./client_HomePage.jsp");
                    break;
                }
                // Technician
                case "T":
                {
                    session.setAttribute("userType", userType);
                    session.setAttribute("userDetails", userDetails);
                    response.sendRedirect("./technician_HomePage.jsp");
                    break;
                }
                // Call Service Agent
                case "CSA":
                {
                    session.setAttribute("userType", userType);
                    session.setAttribute("userDetails", userDetails);
                    response.sendRedirect("./csa_HomePage.jsp");
                    break;
                }
                // If the user does not have any type
                default:
                {
                    System.out.println("The type does not exist.");
                    break;
                }
            }
        }
        // If the user has no details (They are not a valid user), redirect them to the login page
        else
        {
            // In the event that an entered email or password is incorrect, the following will be saved to the session
            // and the user will be redirected to the index page
            session.setAttribute("state", "Warning: Invalid Information");
            response.sendRedirect("./");
            
        }   
    } 
}
