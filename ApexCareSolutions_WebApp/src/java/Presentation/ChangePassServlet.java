// The following servlet is executed when the user wants to change their password
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import sen381_project.Bussiness_Logic_Layer.ClientProfileLogic;

@WebServlet("/changePassword")
public class ChangePassServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Used to get the user details stored in the session storage
        HttpSession session = request.getSession();
        
        // Used to get client details stored in the session storage
        String[] clientDetails = (String[]) session.getAttribute("userDetails");
        
        
        if(clientDetails != null)
        {
            // Gets the client Id from the details
            String clientID = clientDetails[0];

            // Gets the user type
            String userType = clientID.split("_")[0];
            
            if(userType.equals("C"))
            {
                // Gets the ID number
                Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);

                // Gets the old and new password from the inputs entered on the web page
                String enteredOriginalPass = request.getParameter("old-pass");
                String enteredNewPass = request.getParameter("new-pass");

                // Handles the logic
                ClientProfileLogic cpl = new ClientProfileLogic();

                try
                {
                    // Gets the user's currect password in the database
                    String originalPass = cpl.clientPass(clientIDNum);
                    // Determines if the user entered a valid password or not
                    String passwordFeedback = cpl.changePass(clientIDNum, enteredOriginalPass, originalPass, enteredNewPass);

                    // Determines what should be displayed to the user
                    switch(passwordFeedback)
                    {
                        // In the event of the user entering empty values
                        case "Empty":
                        {
                            String[] messageDetails = {"Password cannot be empty.", "red"};
                            request.setAttribute("message", messageDetails);
                            request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
                            break;
                        }
                        // In the event of the user entering the correct password
                        case "Valid":
                        {
                            String[] messageDetails = {"Successfully changed password.", "green"};
                            request.setAttribute("message", messageDetails);
                            request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
                            break;
                        }
                        // In the event of the user entering the wrong password
                        case "Invalid":
                        {
                            String[] messageDetails = {"Current password is incorrect.", "red"};
                            request.setAttribute("message", messageDetails);
                            request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
                            break;
                        }
                        // In the event a state was not given
                        default:
                        {
                            request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
                            break;
                        }
                    }
                }
                catch (Exception e)
                {
                    System.out.println("!E!----- (ChangePassServlet) Error, while trying to update client password: " + e.getMessage() + " -----!E!");
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
