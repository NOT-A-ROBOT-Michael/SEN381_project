
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import sen381_project.Bussiness_Logic_Layer.CSA_HomePage_Logic;

@WebServlet("/declineRequestedService")
public class DeclineServiceServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CSA_HomePage_Logic csa_HomeLogic = new CSA_HomePage_Logic();
        
        try
        {
            Integer serviceID = Integer.parseInt(request.getParameter("hidden-ServiceID"));
        
            csa_HomeLogic.declineService(serviceID);
            
        }
        catch (Exception e)
        {
            System.out.println("!E!----- (DeclineServiceServlet) Error, while trying to change service status to declined: " + e.getMessage() + " -----!E!");
        }
        
        response.sendRedirect("./technician_HomePage");
        
    }
}
