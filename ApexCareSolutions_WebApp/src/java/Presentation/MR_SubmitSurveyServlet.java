/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import sen381_project.Bussiness_Logic_Layer.Objects.MR_Survey;
import sen381_project.Bussiness_Logic_Layer.MR_writeSurvey;
/**
 *
 * @author iyesme
 */

@WebServlet("/SubmitSurvey")
public class MR_SubmitSurveyServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        
        System.out.println(request.getParameter("hidden-ServiceID"));
        
        
        HttpSession session = request.getSession();
        
        String[] clientSessionDetails = (String[]) session.getAttribute("userDetails");
        
        if(clientSessionDetails != null)
        {
            // Gets the client's Address ID from the details stored in the session
            String userType = clientSessionDetails[0].split("_")[0];
            
            if(userType.equals("C"))
            {
                //Declare variables
                Integer serviceID;
                String serviceIDString;
                Integer rateService, rateTech, rateServiceAgent;
                String rateServiceString, rateTechString, rateServiceAgentString;

                //Initialize
                MR_writeSurvey surveyFunc = new MR_writeSurvey();
                MR_Survey survey = new MR_Survey();
        
                //get ratings from parameters
                serviceIDString = request.getParameter("hidden-ServiceID");
                rateServiceString = request.getParameter("hidden-rateSer");
                rateTechString = request.getParameter("hidden-rateTech");
                rateServiceAgentString = request.getParameter("hidden-rateSA");
        
                //convert string to int
                serviceID = Integer.parseInt(serviceIDString);
                //System.out.println(serviceID);
                rateService = Integer.parseInt(rateServiceString);
                rateTech = Integer.parseInt(rateTechString);
                rateServiceAgent = Integer.parseInt(rateServiceAgentString);
        
                
                survey.setServiceID(serviceID)
                        .setRateBackEndService(rateService)
                        .setRateTechnician(rateTech)
                        .setRateServiceAgent(rateServiceAgent);
                
                request.setAttribute("Survey", survey);
                
                //save to database
                surveyFunc.updateSurvey(serviceID, rateTech, rateServiceAgent, rateService);
        
                //redirect back to survey page
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
