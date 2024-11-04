
package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import businesslogiclayer.logic.CSA_ViewDetails_Logic;

@WebServlet("/viewMoreDetailsCSA")
public class CSADetailsServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CSA_ViewDetails_Logic csa_DetailsLogic = new CSA_ViewDetails_Logic();
        
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
