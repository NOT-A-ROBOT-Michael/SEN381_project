/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//import object
import businesslogiclayer.object.MR_ClientServiceListItem;
        
//import Client Serives to return a list
import businesslogiclayer.logic.MR_ClientServices;

/**
 *
 * @author iyesme
 */

@WebServlet("/ClientContracts")
public class MR_ClientContractServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Intanciate Session
        HttpSession session = request.getSession();
        
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                //Initialize
                MR_ClientServices clientServices = new MR_ClientServices();
                
        
                //variables
                ArrayList<MR_ClientServiceListItem> serviceItems;
        
        
                String[] clientDetails = (String[])session.getAttribute("userDetails");
        
                String clientID = clientDetails[0];
        
                Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);
        
                serviceItems = clientServices.getDisplayClientContracts(clientIDNum);
        
                request.setAttribute("ServiceItems", serviceItems);
                request.getRequestDispatcher("/View/ClientContracts.jsp").forward(request, response);
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
