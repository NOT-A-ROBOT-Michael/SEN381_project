// The following loads the more details page on the client's side
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/viewMoreDetails")
public class ClientMoreDetailsServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        HttpSession session = request.getSession();
        
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                // Directs the client to the page that shows more information about their service
                request.getRequestDispatcher("./View/client_ViewMoreDetails.jsp").forward(request, response);
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
