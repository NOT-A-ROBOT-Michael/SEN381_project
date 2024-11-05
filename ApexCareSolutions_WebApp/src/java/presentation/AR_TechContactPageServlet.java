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
import businesslogiclayer.object.AR_TechnicianDetails;
import businesslogiclayer.logic.TechDetailLogic;
import datalayer.ConnectionProvider;

/**
 *
 * @author arlow
 */
@WebServlet("/TechnicianContactPage")
public class AR_TechContactPageServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try{
        HttpSession session = request.getSession();
        AR_TechnicianDetails tDetails = (AR_TechnicianDetails)session.getAttribute("tDetails");
        ConnectionProvider cpt = new ConnectionProvider();
       
        
       String[] Details = (String[])session.getAttribute("userDetails");
       Integer techID = Integer.parseInt(Details[0].split("_")[1]) ;
       ArrayList<Integer> serviceID_List = cpt.getServicesOfTechnician(techID);
       
            ArrayList<String[]> TechServices = cpt.getServiceForTech(serviceID_List);
            request.setAttribute("techservices", TechServices);
        //Instanciate session
        //HttpSession session = request.getSession();
        
        //Instanciate techDetailsFunc
        TechDetailLogic techDetailsFunc = new TechDetailLogic();

        
        //declare object x2

        //TechnicianDetails tDetails;
        
        String[] userDetails = (String[])session.getAttribute("userDetails");
        
        String email = userDetails[5];
        System.out.println("Email: "+email);
        

        //assign object x2

        tDetails = techDetailsFunc.getTechnicianDetails(email);

        
        //asign attribute to object x2

        session.setAttribute("tDetails", tDetails);
        request.getRequestDispatcher("./View/TechnicianContactPage.jsp").forward(request, response);
        }catch(Exception e){
            System.out.println("Contact page servlet error: " + e.getMessage());
        }
    }

}
