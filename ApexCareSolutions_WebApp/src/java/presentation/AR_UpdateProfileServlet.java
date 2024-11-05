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
import businesslogiclayer.logic.ProfileLogic;

/**
 *
 * @author arlow
 */
@WebServlet("/updateProfile")
public class AR_UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {

   ProfileLogic pt = new ProfileLogic();
   
   Integer TechID = Integer.parseInt(request.getParameter("txt_TechID"));
   String phone =  request.getParameter("txt_phoneNumber");
   String password =  request.getParameter("txt_password");
   
   pt.ExecuteProfile(TechID, phone, password);
   
   response.sendRedirect("./technicianProfile");
   
   
   
   }
}
