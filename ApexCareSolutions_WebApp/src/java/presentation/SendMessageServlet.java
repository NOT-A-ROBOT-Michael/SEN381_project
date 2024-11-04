// The following servlet is used to send SMSs to technicians when they are assigned to a new service
package presentation;

// Servlet
import datalayer.ConnectionProvider;
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
import businesslogiclayer.logic.CSA_ViewDetails_Logic;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CSA_ViewDetails_Logic csa_DetailsLogic = new CSA_ViewDetails_Logic();
        
        String ACCOUNT_SID = System.getenv("twil_ID");
        String AUTH_TOKEN = System.getenv("twil_Token");
        String phone = System.getenv("twil_Phone");
        String toNumber = "+27729489256";
        Integer serviceID = (Integer) request.getAttribute("serviceID");
        LocalDate currentDate = LocalDate.now();
        
        try
        {
            
            String[] serviceDetails = csa_DetailsLogic.getCSA_ServiceDetails(serviceID);
            
            
            String sms_Text;
            
            if(serviceDetails[15] != null && serviceDetails[16] != null)
            {
                sms_Text = "Greetings "+ serviceDetails[15] + "  " + serviceDetails[16]+"\n\nYou have been assigned to a new service." +"\n\nService ID: " + serviceDetails[0] + "\nClient Name: " + serviceDetails[2] + " " + serviceDetails[3] + "\nClient Phone Number: " + serviceDetails[4] + "\nClient Email: " + serviceDetails[5] + "\n\nAddress:\nCountry: " + serviceDetails[11] + "\nState: " + serviceDetails[12] + "\nCity: " + serviceDetails[13] + "\nStreet Name: " + serviceDetails[14] +"\n\n\nPlease check your portal to find out more about the service.";
            }
            else
            {
                sms_Text = "Greetings Technician,\n\n You have been assigned to a new service.\n\nPlease check your portal to find out more about the service.";
            }
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
            Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toNumber),
                new com.twilio.type.PhoneNumber(phone),
                sms_Text)
            .create();

            System.out.println(message.getSid());
            
            System.out.println("SMS sent successfully.");
            System.out.println(sms_Text);
        }
        catch (Exception e)
        {
            System.out.println("Failed to send SMS: " + e.getMessage());
        }
        
        response.sendRedirect("./technician_HomePage");
    }
}


