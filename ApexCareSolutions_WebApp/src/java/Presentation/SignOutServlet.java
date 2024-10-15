// The following servlet is used to handle the user sign out
package Presentation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// In the event that the user pressed the sign out button, the following action will happen
@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        // Check if a session exists, if it does use it. If there is not a session available, do not create one
        HttpSession session = request.getSession(false);
        
        try
        {
            // Clear the user's session cache and redirect them to the index page
            session.invalidate();
            response.sendRedirect("./");
        }
        catch (Exception e)
        {
            System.out.println("Error while trying to sign out: " + e.getMessage());
        }
        
        
    }
}
