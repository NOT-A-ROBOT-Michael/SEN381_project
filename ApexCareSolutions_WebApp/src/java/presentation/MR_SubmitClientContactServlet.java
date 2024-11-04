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
import java.util.Date;


import businesslogiclayer.logic.MR_ClientQueries;
import businesslogiclayer.object.MR_ClientQuery;
/**
 *
 * @author iyesme
 */
@WebServlet("/SubmitClientContact")
public class MR_SubmitClientContactServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
                //declare variables
                MR_ClientQuery query;
                String description;
                String serviceIDString;
                Integer serviceID;
                
        
                //Initialize
                MR_ClientQueries queryFunc = new MR_ClientQueries();
                Date currentDate = new Date();
        
                //get get parameters
                serviceIDString = request.getParameter("serviceID");
                description = request.getParameter("description");
                
                //Convert string to int
                serviceID = Integer.parseInt(serviceIDString);
                
                //run sumbit function
                
                queryFunc.LogClientQuery(serviceID, description, currentDate);
                
                //redirect
                
                request.getRequestDispatcher("./View/ClientContact.jsp").forward(request, response);
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
