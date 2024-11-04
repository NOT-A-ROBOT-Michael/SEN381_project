
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import sen381_project.Bussiness_Logic_Layer.ClientDetailsThing;
import sen381_project.Bussiness_Logic_Layer.TechnicianDetailsPage;

import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Bussiness_Logic_Layer.Objects.TechnicianDetails;

import jakarta.servlet.http.HttpSession;
import sen381_project.Bussiness_Logic_Layer.Objects.ClientDetails;
/**
 *
 * @author arlow
 */
@WebServlet("/Technician_Details_Page")
public class ViewTechDetailsServlet extends HttpServlet{
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        
        //Instanciate session
        HttpSession session = request.getSession();
        
        //Instanciate techDetailsFunc
        TechnicianDetailsPage techDetailsFunc = new TechnicianDetailsPage();
        //Instanciate clientDetailsFunc
        ClientDetailsThing clientDetailsFunc = new ClientDetailsThing();
        
        //declare object x2
        ClientDetails cDetails;
        Client_Details cDetails2;
        TechnicianDetails tDetails;
        
        String[] userDetails = (String[])session.getAttribute("userDetails");
        
        String email = userDetails[5];
        System.out.println("Email: "+email);
        
        //get serviceID
        String serviceIDString = request.getParameter("serviceID");
        //convert to integer
        Integer serviceID = Integer.parseInt(serviceIDString);
              
        
        
        //assign object x2
         cDetails2 = clientDetailsFunc.CreateNoteObject(serviceID);
        tDetails = techDetailsFunc.getTechnicianDetails(email);
        
        // as die lyn onder ge-execute word dan se dit Details 2 = null.
         cDetails= clientDetailsFunc.CreateNoteObject2(serviceID);
        
        //asign attribute to object x2
        session.setAttribute("cDetails", cDetails); // die een word geroep en dit werk
        session.setAttribute("cDetails2", cDetails2);
        session.setAttribute("tDetails", tDetails);
        
        request.getRequestDispatcher("./View/Technician_Details_Page.jsp").forward(request, response);
    }
}
