// The following loads the survey page on the client's side
package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import businesslogiclayer.object.MR_Survey;
import businesslogiclayer.logic.ClientWriteSurveyLogic;


@WebServlet("/viewSurvey")
public class MR_ClientViewSurveyServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                String ServiceIDString;
                Integer ServiceID;
                
                //Initialize
                ClientWriteSurveyLogic surveyFunc = new ClientWriteSurveyLogic();
                MR_Survey survey;
                
                //get serviceID from param
                ServiceIDString = request.getParameter("hidden-ServiceID");
                if(ServiceIDString == null){
                    ServiceIDString = request.getParameter("serviceID");
                }
                
                request.setAttribute("serviceID", ServiceIDString);
                
                //convert serviceId to Integer
                ServiceID = Integer.parseInt(ServiceIDString);
                
                //get survey
                survey = surveyFunc.getSurvey(ServiceID);
                
                
                
                //set servey to request attribute
                request.setAttribute("Survey", survey);
                
                // Directs the client to the survey page
                request.getRequestDispatcher("./View/client_ViewSurvey.jsp").forward(request, response);
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
