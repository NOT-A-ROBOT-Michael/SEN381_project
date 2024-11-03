// The following loads the more details page on the client's side
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import sen381_project.Bussiness_Logic_Layer.MR_ClientServices;
import sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientService;


@WebServlet("/viewMoreDetails")
public class MR_ClientMoreDetailsServlet extends HttpServlet
{
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
                // Directs the client to the page that shows more information about their service
                String ServiceIDString;
                Integer ServiceID;
                MR_ClientService serviceDetails;
        
        
        
        
                //Instanciate
                MR_ClientServices clientServiceFunc = new MR_ClientServices();
        
                //get service id from post
                ServiceIDString = request.getParameter("serviceID");
                //Set current serviceID to session
                session.setAttribute("CurrentServiceID", ServiceIDString);
                //convert to integer
                ServiceID = Integer.parseInt(ServiceIDString);
        
                //use service ID to get an object containing all the contract details
                serviceDetails = clientServiceFunc.getClientContractDetails(ServiceID);
        
                //assign the object to an atribute
                session.setAttribute("ServiceDetails",serviceDetails);
        
                //Reroute the user to the more details page
                request.getRequestDispatcher("./View/ClientContractDetails.jsp").forward(request, response);
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
