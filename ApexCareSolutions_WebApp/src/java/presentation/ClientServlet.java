package presentation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import viewmodel.ClientViewModel;

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
@WebServlet("/registerClient")
public class ClientServlet extends HttpServlet {
    
    private final ClientViewModel clientViewModel = new ClientViewModel();

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
            out.println("<title>Servlet ClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClientServlet at " + request.getContextPath() + "</h1>");
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
        
        
        try { //try catch necessary for ClassNotFoundException
            
            HttpSession session = request.getSession();
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            
            String country = request.getParameter("drop_country");
            String state = request.getParameter("drop_state");
            String city = request.getParameter("drop_city");
            String streetName = request.getParameter("txt_StreetName");
            
            String[] details = clientViewModel.registerClient(name, surname, email, phone, password, confirmPassword, country, state, city, streetName);          
            
            if (details.length > 1) {
                session.setAttribute("userDetails", details);
                
                String userType = details[0].split("_")[0];
                
                session.setAttribute("userType", userType);
                response.sendRedirect("./client_HomePage");//Success
            } else {
                request.setAttribute("error", details[0]);
                request.setAttribute("displayError", "block");
                request.getRequestDispatcher("./View/register.jsp").forward(request, response);//Forward with error message
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /*
    
    
    
     
    
    
    
    
    test_view
     SELECT "ClientID",
    "AddressID",
    "First_Name",
    "Last_Name",
    "Phone_Number",
    "Email",
    "Password"
   FROM "Client"
  WHERE "ClientID" > 2;
    */

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
