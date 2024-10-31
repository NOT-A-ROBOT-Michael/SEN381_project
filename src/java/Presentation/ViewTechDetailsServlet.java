
package Presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author arlow
 */
@WebServlet("/Technician_Details_Page")
public class ViewTechDetailsServlet extends HttpServlet{
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("./View/Technician_Details_Page.jsp").forward(request, response);
    }
}
