// The following is used to load the jsp file where the users can change their password
package Presentation;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
 
@WebServlet("/client_ChangePass")
public class LoadPassChangePageServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            if(!userType.isBlank())
            {
                // Directs the user to the page where they can change their current password
                request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
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
