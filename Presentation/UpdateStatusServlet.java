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
import sen381_project.Bussiness_Logic_Layer.StatusThing;

/**
 *
 * @author arlow
 */
@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet{
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
   StatusThing ST = new StatusThing();
       
   Integer serviceID = Integer.parseInt(request.getParameter("serviceID"));
   
   
   
   String status =  request.getParameter("Change Status");
   ST.updateStatus(serviceID, status);
   
   response.sendRedirect("./technician_HomePage");
   
   
   
   }
}
