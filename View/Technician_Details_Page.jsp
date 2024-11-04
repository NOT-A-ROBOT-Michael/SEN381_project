<%-- 
    Document   : Technician_Details_Page
    Created on : 28 Oct 2024, 22:30:02
    Author     : arlow
--%>

<%@page import="sen381_project.Bussiness_Logic_Layer.NotesThing"%>
<%@page import="java.util.Scanner"%>
<%@page import="sen381_project.Data_Layer.ConnectionProvider"%>
<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.ClientDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.Client_Details"%>
<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.TechnicianDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details Page</title>
        <link rel="stylesheet" href="./CSS/styling.css"/>
        <link rel="stylesheet" href="./CSS/Tech_Details.css"/>
    </head>
    <body>
        <header>
            
            <%
                // The following will check if the user that is directed to this page
                // is logged in, it will also check if they are a valid user.
                String userType = (String) session.getAttribute("userType");
                String[] userDetails = (String[]) session.getAttribute("userDetails");
                String fullName = "";
                
                ClientDetails cDetails2 = (ClientDetails)session.getAttribute("cDetails");
                TechnicianDetails tDetails = (TechnicianDetails)session.getAttribute("tDetails");
                //Client_Details  cDetails = (Client_Details)session.getAttribute("cDetails2");
                NotesThing NT = new NotesThing();
             
                
               
                // dit breek na die volgende line gerun word
            
                
                if(userDetails != null)
                {
                    if(userType.equals("T"))
                    {
                        // If the user exists, they will receive access
                        fullName= userDetails[2] + " " + userDetails[3];
                    }
                    else
                    {
                        // If they do not exist they will receive an error.
                        response.sendRedirect("./?Error=Incorrect user type");
                    }
                }
                else
                {
                    // If they do not exist they will receive an error.
                    response.sendRedirect("./?Error=Please log in first");
                }
            %>
            
            <!--Title Bar-->
            <div>
                <!-- Div that contains the title-->
                <div id="top-ribbon">
                    <img src="./Images/Logo.png" alt="Logo" id="logo"/>
                    <h1 id="title">Details</h1>
                    
                    <div id="info-container">
                        <h2 id="username">User: <%= fullName %></h2>
                        <!--The following form is used to sign the user out, by requesting a POST method-->
                        <form name="sign_out" action="signOut" method="POST" id="sign_out">
                            <input type="submit" value="Sign Out" name="btn_signOut" id="btn_SignOut"/>
                        </form>
                    </div>
                </div>

                <!--Navigation Bar-->
                <nav id="nav-container">
                    <ul id="list-container">
                        <li class="link-container"><a href="./technician_HomePage" class="links">Task Page</a></li>
                        <li class="link-container"><a href="./technicianProfile" class="links">Profile</a></li>
                        <li class="link-container"><a href="./TechnicianContactPage" class="links">Contact Page</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <div id="infoBlock">
                
                <div id="warning-container">
                
                </div>
                <div id="top-container">
                    <div id="head-container">

                        <h1 class="head">Task Info:</h1>
                        <h1 class="head">Client Info:</h1>
                    </div>

                     
                </div>
                    
                
                <form name="Tech_Details" action="Technician_Details_Page" method="POST" id="changeProfile">
                    <div id="main-container">
                        
                        <div id="profile-container">
                            
                            <p class="key">Service ID:</p>
                            <input type="text" name="serviceID"  value = "<%= cDetails2.getServiceID() %>"  size="15" readonly="readonly" class="value"id="ServiceText"/>
                            
                            <p class="key">Technician ID:</p>
                            <input type="text" name="txt_clientID"  value = "<%= tDetails.getTechID() %>"  size="15" readonly="readonly" class="value"id="TechText"/>
                           
                            
                            <p class="key">First Name:</p>
                            <input type="text" name="txt_firstName" value="<%= tDetails.getName()%>" size="15" class="value"id="TechText" readonly="readonly"/>
                            
                            <p class="key">Last Name:</p>
                            <input type="text" name="txt_lastName" value="<%= tDetails.getSurname() %>" size="15" class="value"id="TechText" readonly="readonly"/>
                            
                            <p class="key">Phone Number:</p>
                            <input type="text" name="txt_phoneNumber" value="<%= tDetails.getNumber()%>" size="15" class="value"id="TechText" readonly="readonly"/>
                            
                            <p class="key">Email:</p>
                            <input type="text" name="txt_email" value="<%= tDetails.getEmail() %>" size="15" class="value"id="TechText" readonly="readonly"/>
                            
                            <p class="key">Notes:</p>
                            <input type="text" name="txt_Notes" value="" size="5000" class="value"id="NoteText"/>
                            <input type="submit" value="Save Note" name="saveNote" class="btn" id="saveProfile"
                                   onclick="<% NT.SaveNote(cDetails2.getServiceID() , request.getParameter("txt_Notes")); %>"/>
                                                        
                            
                            
              
                            
                            
                            
                            
                        </div>
                        
                       
                        <div id="address-container">

                            
                            <p class="key"><label for="drop_country">Country:</label></p>
                            <input type="text" name="txt_Country" value="<%= cDetails2.getCountry() %>" size="" class="value" id="clientText" readonly="readonly"/>
                            
                            <p class="key"><label for="drop_state">State:</label></p>
                            <input type="text" name="txt_State" value="<%= cDetails2.getState() %>" size="" class="value" id="clientText" readonly="readonly"/>
                            
                            <p class="key"><label for="drop_city">City:</label></p>
                            <input type="text" name="txt_City" value="<%= cDetails2.getCity() %>" size="" class="value" id="clientText" readonly="readonly"/>

                            <p class="key"><label for="txt_StreetName">Street name:</label></p>
                            <input type="text" name="txt_StreetName" size="" placeholder="" value="<%= cDetails2.getStreet() %>" class="value"id="clientText" readonly="readonly"/>

                            <p class="key"><label for="date">Requested Date:</label></p>
                            <input type="text" name="txt_Date" value="<%= cDetails2.getdate() %>" size="" class="value"id="clientText" readonly="readonly"/>
                            
                            <p class="key"><label for="clientName">Client Name:</label></p>
                            <input type="text" name="txt_ClinetName" value="<%= cDetails2.getName() %> <%= cDetails2.getSurname() %>" size="" class="value"id="clientText" readonly="readonly"/>
                            
                            <p class="key"><label for="clientProblem">Client Problem:</label></p>
                            <input type="text" name="txt_ClinetProblem" value="<%= cDetails2.getDescription() %>" size="" class="value"id="clientText" readonly="readonly"/>
                            
                            <p class="key"><label for="clientPhone">Client Phone:</label></p>
                            <input type="text" name="txt_ClinetPhone" value="<%= cDetails2.getNumber() %>" size="" class="value"id="clientText" readonly="readonly"/>

                        </div> 
                        
                         
                    </div>
                         
                </form>
                            <form name="Tech_Detail_update" action="updateStatus" method="POST" id="changeProfile">
                                <input type="hidden" name="serviceID"  value = "<%= cDetails2.getServiceID() %>"  size="15" readonly="readonly" class="value"id="ServiceText"/>
                                <p class="key" id="changeStatus"><label for="ChangeStatus">Change Status:</label></p>
                                    <select name="Change Status" id="Dropdown">
                                        <option>In Progress</option>
                                        <option>Outstanding</option>
                                        <option>Finished </option>
                                    </select>
                                
                                <input type="submit" value="Update Status" name="Update_Status" class="btn" id="bottomButton" />
                              
                             
                            </form>
                             <button onclick="window.location.href='./technician_HomePage'" class="btn" id="bottomButton2">Go Back</button>
                
            </div>
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
                
                
        
    </body>
</html>
