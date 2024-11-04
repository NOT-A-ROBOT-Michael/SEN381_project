
package Presentation;
/*
// Servlet
import DataAccess.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Twilio
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ACCOUNT_SID = System.getenv("twil_ID");
        String AUTH_TOKEN = System.getenv("twil_Token");
        String phone = System.getenv("twil_Phone");
        String sms_Text = "Greetings Technician,\n\n You have been assigned to a new service.\n\nPlease check your portal to find out more about the service.";
        String toNumber = "+27729489256";
        Integer serviceID = Integer.parseInt(request.getParameter("serviceID"));
        LocalDate currentDate = LocalDate.now();
        
        try
        {
            
            ConnectionProvider cp = new ConnectionProvider();
            
            cp.addTechnicianToService(serviceID, currentDate);
            cp.updateServiceToOngoing(serviceID);
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
            Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toNumber),
                new com.twilio.type.PhoneNumber(phone),
                sms_Text)
            .create();

            System.out.println(message.getSid());

            System.out.println("SMS sent successfully.");
        }
        catch (Exception e)
        {
            System.out.println("Failed to send SMS: " + e.getMessage());
        }
        
        response.sendRedirect("./client_HomePage");
    }
}
*/
public class SendMessageServlet 
{}
