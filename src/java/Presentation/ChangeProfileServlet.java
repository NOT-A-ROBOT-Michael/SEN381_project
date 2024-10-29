
package Presentation;

import sen381_project.Bussiness_Logic_Layer.Objects.Address;
import sen381_project.Bussiness_Logic_Layer.Objects.Client_Details;
import sen381_project.Data_Layer.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/client_ChangeProfile")
public class ChangeProfileServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        ArrayList<String[]> fullClientDetails = new ArrayList<>();
        
        String clientID = request.getParameter("txt_clientID");
        String firstName = request.getParameter("txt_firstName");
        String lastName = request.getParameter("txt_lastName");
        String phoneNumber = request.getParameter("txt_phoneNumber");
        String email = request.getParameter("txt_email");
        
        String country = request.getParameter("drop_country");
        String state = request.getParameter("drop_state");
        String city = request.getParameter("drop_city");
        String streetName = request.getParameter("txt_StreetName");
        
        System.out.println("User Info check: " + clientID + ", " + firstName + ", " + lastName + ", " + phoneNumber + ", " + email);
        
        System.out.println("Address Info check: " + country + ", " + state + ", " + city + ", " + streetName);
        
        // Check if any value changed
        
        Integer clientIDNum = Integer.parseInt(clientID.split("_")[1]);
        
        ConnectionProvider cp = new ConnectionProvider();
        
        try
        {
            Client_Details cd = cp.getClientDetails(clientIDNum);
            
            String[] originalDetails = cd.getClientInfo();
            
            Integer addressID = Integer.parseInt(originalDetails[1]);
            
            Address a = cp.getAddress(addressID);
            
            String[] addressInfo = a.getAddress();
            
            // {fullClientID, this.addressID.toString(), this.firstName, this.lastName, this.phoneNumber, this.email, this.password}
            
            if(!firstName.isBlank() && !lastName.isBlank() && !phoneNumber.isBlank() && !email.isBlank() && !country.equals("None") && !state.equals("None") && !city.equals("None") && !streetName.isBlank())
            {
                if(!originalDetails[2].equals(firstName) || !originalDetails[3].equals(lastName) || !originalDetails[4].equals(phoneNumber) || !originalDetails[5].equals(email))
                {
                    // Update client
                    cp.updateClientInfo(clientIDNum, firstName, lastName, phoneNumber, email);
                    String[] messageInfo = {"Successfully updated client info", "green"};
                    request.setAttribute("message", messageInfo);
                    
                    originalDetails[2] = firstName;
                    originalDetails[3] = lastName;
                    originalDetails[4] = phoneNumber;
                    originalDetails[5] = email;
                }

                if(!addressInfo[1].equals(country) || !addressInfo[2].equals(state) || !addressInfo[3].equals(city) || !addressInfo[4].equals(streetName))
                {
                    // Update Address
                    cp.updateClientAddress(addressID, country, state, city, streetName);
                    String[] messageInfo = {"Successfully updated client info", "green"};
                    request.setAttribute("message", messageInfo);
                    
                    addressInfo[1] = country;
                    addressInfo[2] = state;
                    addressInfo[3] = city;
                    addressInfo[4] = streetName;
                }
            }
            else
            {
                String[] messageInfo = {"Missing values, please make sure to enter all the required information.", "red"};
                request.setAttribute("message", messageInfo);
            }
            
            System.out.println(cd);
            
            fullClientDetails.add(originalDetails);
            fullClientDetails.add(addressInfo);
            
            request.setAttribute("clientInfo", fullClientDetails);
            
            request.getRequestDispatcher("./View/client_ProfilePage.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong while trying to update client profile information: " + e.getMessage());
        }
        
        
        
    }
}
