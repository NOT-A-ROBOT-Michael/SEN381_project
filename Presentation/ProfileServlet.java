package Presentation;

import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/client_ProfilePage")
public class ProfileServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        ArrayList<String[]> fullClientDetails = new ArrayList<>();
        
        ConnectionProvider cp = new ConnectionProvider();
        
        String[] clientDetails = (String[]) session.getAttribute("userDetails");
        
        String clientID = clientDetails[0];
        
        Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);
        
        try
        {
            Client_Details cd = cp.getClientDetails(clientIDNum);
            String[] clientInfo = cd.getClientInfo();
            
            Address a = cp.getAddress(Integer.parseInt(clientInfo[1]));
            
            String[] addressInfo = a.getAddress();
            
            System.out.println(cd);
            
            fullClientDetails.add(clientInfo);
            fullClientDetails.add(addressInfo);
            
            request.setAttribute("clientInfo", fullClientDetails);
            request.getRequestDispatcher("/View/client_ProfilePage.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to retrieve client info: " + e.getMessage());
        }
    }
}
