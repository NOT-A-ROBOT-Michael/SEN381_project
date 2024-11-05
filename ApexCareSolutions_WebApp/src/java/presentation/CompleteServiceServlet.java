
package presentation;

import businesslogiclayer.logic.CallAgentHomePageLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/completeService")
public class CompleteServiceServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CallAgentHomePageLogic csa_HomeLogic = new CallAgentHomePageLogic();
        
        try
        {
            Integer serviceID = Integer.parseInt(request.getParameter("hidden-ServiceID"));
        
            csa_HomeLogic.completeService(serviceID);
            
        }
        catch (Exception e)
        {
            System.out.println("!E!----- (CompleteServiceServlet) Error, while trying to change service status to completed: " + e.getMessage() + " -----!E!");
        }
        
        response.sendRedirect("./ClientContracts");
        
    }
}
