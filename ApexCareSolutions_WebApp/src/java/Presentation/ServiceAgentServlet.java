/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Presentation;

import ViewModel.ServiceAgentViewModel;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author morne
 */
@WebServlet("/registerServiceAgent")
public class ServiceAgentServlet extends HttpServlet {
    
    private final ServiceAgentViewModel serviceAgentViewModel = new ServiceAgentViewModel();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceAgentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceAgentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{ 
            
            HttpSession session = request.getSession();
            
            String name = request.getParameter("name_ServiceAgent");
            String surname = request.getParameter("surname_ServiceAgent");
            String email = request.getParameter("email_ServiceAgent");
            String phone = request.getParameter("phone_ServiceAgent");
            String password = request.getParameter("password_ServiceAgent");
            String confirmPassword = request.getParameter("confirmPassword_ServiceAgent");
            
            System.out.println("ServiceAgentServlet:");
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            System.out.println("Password: " + password);
            System.out.println("Confirm: " + confirmPassword);
            
            String[] serviceAgent_Details = serviceAgentViewModel.registerServiceAgent(name, surname, email, phone, password, confirmPassword);
            
            if (serviceAgent_Details.length > 1) {
                session.setAttribute("userDetails", serviceAgent_Details);
                
                String userType = serviceAgent_Details[0].split("_")[0];
                
                session.setAttribute("userType", userType);
                response.sendRedirect("./csa_HomePage");//Success
            } else {
                request.setAttribute("error", serviceAgent_Details[0]);
                request.setAttribute("displayError", "block");
                request.getRequestDispatcher("./View/register.jsp").forward(request, response);//Forward with error message
            }
            
        } catch (ClassNotFoundException ex){
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
