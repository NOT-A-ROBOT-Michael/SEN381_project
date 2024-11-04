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
import java.io.IOException;
import sen381_project.Bussiness_Logic_Layer.ContactPageThing;
import sen381_project.Bussiness_Logic_Layer.ProfileThing;

/**
 *
 * @author arlow
 */
@WebServlet("/queryPage")
public class TechQueryContactPageServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {

   
   ContactPageThing Contact = new ContactPageThing();
   Integer TechID = Integer.valueOf(request.getParameter("txt_TechnicianID"));
   String Query =  request.getParameter("txt_query");
   Integer ServiceID = Integer.parseInt(request.getParameter("TaskDropdown")) ;
   
   Contact.InsertIntoServiceQuery(ServiceID , TechID, Query);
   
   response.sendRedirect("./technician_HomePage");
   
   
   
   }
    
    
}
