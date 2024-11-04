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
import sen381_project.Bussiness_Logic_Layer.ClientDetailsThing;
import sen381_project.Bussiness_Logic_Layer.Objects.ClientDetails;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Bussiness_Logic_Layer.Objects.TechnicianDetails;
import sen381_project.Bussiness_Logic_Layer.TechnicianDetailsPage;

/**
 *
 * @author arlow
 */
@WebServlet("/technicianProfile")
public class LoadTechnicianProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Instanciate session
        HttpSession session = request.getSession();
        
        //Instanciate techDetailsFunc
        TechnicianDetailsPage techDetailsFunc = new TechnicianDetailsPage();

        //declare object x2

        TechnicianDetails tDetails;
        
        String[] userDetails = (String[])session.getAttribute("userDetails");
        
        String email = userDetails[5];
        System.out.println("Email: "+email);
        
        //assign object x2

        tDetails = techDetailsFunc.getTechnicianDetails(email);
        //asign attribute to object x2

        session.setAttribute("tDetails", tDetails);
        
        
        request.getRequestDispatcher("./View/technicianProfile.jsp").forward(request, response);
    }
}
