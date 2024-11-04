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
import java.io.IOException;
import businesslogiclayer.logic.AR_StatusThing;

/**
 *
 * @author arlow
 */
@WebServlet("/updateStatus")
public class AR_UpdateStatusServlet extends HttpServlet{
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
   AR_StatusThing ST = new AR_StatusThing();
       
   Integer serviceID = Integer.parseInt(request.getParameter("serviceID"));
   
   
   
   String status =  request.getParameter("Change Status");
   ST.updateStatus(serviceID, status);
   
   response.sendRedirect("./technician_HomePage");
   
   
   
   }
}
