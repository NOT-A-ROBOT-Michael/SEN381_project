
package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import businesslogiclayer.logic.CallAgentViewDetailLogic;

@WebServlet("/viewMoreDetailsCSA")
public class CSADetailsServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CallAgentViewDetailLogic csa_DetailsLogic = new CallAgentViewDetailLogic();
        
        try
        {
            Integer serviceID = Integer.parseInt(request.getParameter("serviceID"));
        
            String[] serviceDetails = csa_DetailsLogic.getCSA_ServiceDetails(serviceID);
            
            request.setAttribute("csa_ServiceDetails", serviceDetails);
            request.getRequestDispatcher("./View/csa_ViewDetailsPage.jsp").forward(request, response);
            
        }
        catch (Exception e)
        {
            System.out.println("!E!----- (CSADetailsServlet) Error, while trying to load service details: " + e.getMessage() + " -----!E!");
        }
    }
}
