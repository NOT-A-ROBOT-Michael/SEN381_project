
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import sen381_project.Bussiness_Logic_Layer.CSA_HomePage_Logic;

@WebServlet("/addTechToService")
public class AddServiceServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CSA_HomePage_Logic csa_HomeLogic = new CSA_HomePage_Logic();
        
        try
        {
            Integer serviceID = Integer.parseInt(request.getParameter("hidden-ServiceID"));
            String addMessage = request.getParameter("hidden-SendMessage");
            csa_HomeLogic.setTechToService(serviceID);
            
            System.out.println("!Info!----- Successfully added updated service to ongoing and added the technician. -----!Info!");
            
            if(addMessage.equals("1"))
            {
                System.out.println("Go Send a message to technician");
                request.setAttribute("serviceID", serviceID);
                request.getRequestDispatcher("./sendMessage").forward(request, response);
            }
            else
            {
                // Get service info
                response.sendRedirect("./technician_HomePage");
            }
        }
        catch (Exception e)
        {
            System.out.println("!E!----- (AddServiceServlet) Error, while trying to change the service status and add a technician: " + e.getMessage() + " -----!E!");
        }
        
        
    }
}
