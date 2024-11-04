package Presentation;

import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePassServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        ConnectionProvider cp = new ConnectionProvider();
        
        String[] clientDetails = (String[]) session.getAttribute("userDetails");
        
        String clientID = clientDetails[0];
        
        Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);
        
        String enteredOriginalPass = request.getParameter("old-pass");
        String newPass = request.getParameter("new-pass");
        
        try
        {
            Client_Details cd = cp.getClientDetails(clientIDNum);
            String originalPass = cd.getClientPass(cd);
            
            System.out.println(cd);
            
            if(enteredOriginalPass.isBlank() || newPass.isBlank())
            {
                System.out.println("Empty Pass");
                String[] messageDetails = {"Password cannot be empty.", "red"};
                request.setAttribute("message", messageDetails);
                request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
                
                
            }
            else if(enteredOriginalPass.equals(originalPass))
            {
                System.out.println("Correct Pass");
                
                try
                {
                    cp.updateClientPass(newPass, clientIDNum);
                    String[] messageDetails = {"Successfully changed password.", "green"};
                    request.setAttribute("message", messageDetails);
                    request.getRequestDispatcher("/View/client_ChangePass.jsp").forward(request, response);
                }
                catch (Exception e)
                {
                    System.out.println("Something went wrong while trying set the new password: " + e.getMessage());
                }
                
                
            }
            else
            {
                System.out.println("Incorrect Pass");
                String[] messageDetails = {"Current password is incorrect.", "red"};
                request.setAttribute("message", messageDetails);
                request.getRequestDispatcher("./View/client_ChangePass.jsp").forward(request, response);
            }
            
            
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to retrieve client info: " + e.getMessage());
        }
    }
}
