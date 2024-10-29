
package Presentation;

import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Data_Layer.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/client_LogProblem")
public class LoadCategoriesServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        try
        {
            System.out.println("The LoadCategories Servlet ran");
            ConnectionProvider cp = new ConnectionProvider();
            
            String[] clientDetails = (String[]) session.getAttribute("userDetails");
            
            Integer addressID = Integer.parseInt(clientDetails[1]);
            
            Address a = cp.getAddress(addressID);
            
            String[] addressInfo = a.getAddress();
        
            ArrayList<String> serviceTypes = cp.getTypeOfServices();
            
            for(var i : serviceTypes)
            {
                System.out.println(i);
            }
            
            request.setAttribute("addressInfo", addressInfo);
            request.setAttribute("serviceTypes", serviceTypes);
            request.getRequestDispatcher("./View/client_LogProblem.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to send the service types: " + e.getMessage());
        }
        
    }
}

