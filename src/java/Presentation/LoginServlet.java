// The following page will handle the user login
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import sen381_project.Bussiness_Logic_Layer.UserLoginLogic;

// The following servlet will run when the user clicks the submit button on the login page
@WebServlet("/user_login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // The following requests a session and is used to store the client's basic details
        HttpSession session = request.getSession();
        // Gets the value entered in the email section
        String email = request.getParameter("email");
        // Gets the value entered in the password section
        String pass = request.getParameter("password");
        
        try
        {
            UserLoginLogic ull = new UserLoginLogic();

            String[] userDetails = ull.getLoginInformation(email, pass);



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
                        System.out.println("!Info!----- Client successfully logged into their account -----!Info!");
                        
                        // Add information to session data and direct user to home page
                        session.setAttribute("userType", userType);
                        session.setAttribute("userDetails", userDetails);
                        response.sendRedirect("./client_HomePage");
                        break;
                    }
                    // Technician
                    case "T":
                    {
                        System.out.println("!Info!----- Technician successfully logged into their account -----!Info!");
                        
                        // Add information to session data and direct user to home page
                        session.setAttribute("userType", userType);
                        session.setAttribute("userDetails", userDetails);
                        response.sendRedirect("./technician_HomePage");
                        break;
                    }
                    // Call Service Agent
                    case "CSA":
                    {
                        System.out.println("!Info!----- Call Service Agent successfully logged into their account -----!Info!");
                        
                        // Add information to session data and direct user to home page
                        session.setAttribute("userType", userType);
                        session.setAttribute("userDetails", userDetails);
                        response.sendRedirect("./csa_HomePage");
                        break;
                    }
                    // If the user does not have any type
                    default:
                    {
                        System.out.println("!Info!----- The type does not exist. -----!Info!");
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
        catch (Exception e)
        {
            System.out.println("!E!----- (LoginServlet) Error, while trying to log into account -----!E!");
        }
    } 
}
