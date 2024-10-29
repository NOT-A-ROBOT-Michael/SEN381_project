<%-- 
    Document   : Technician_Details_Page
    Created on : 28 Oct 2024, 22:30:02
    Author     : arlow
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
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
                    <h1 id="title">Profile</h1>
                    
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
                        <li class="link-container"><a href="./client_HomePage"" class="links">Task Page</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Profile</a></li>
                        <li class="link-container"><a href="#" class="links">Contact Page</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <div id="infoBlock">
                <div id="warning-container">
                <%
                    ArrayList<String[]> fullClientInfo = (ArrayList<String[]>) request.getAttribute("clientInfo");
                    String[] clientInfo = fullClientInfo.get(0);
                    String[] addressInfo = fullClientInfo.get(1);
                    
                    String[] messageInfo = (String[]) request.getAttribute("message");
                    
                    if(messageInfo != null)
                    {
                        String text = messageInfo[0];
                        String textColour = messageInfo[1];
                    
                        out.println("<h1 style=\"color: "+ textColour +"\" id=\"warning\">" + text + "</h1>");
                    }
                    
                    request.setAttribute("message", null);
                %>
                </div>
                <div id="top-container">
                    <div id="head-container">

                        <h1 class="head">Task Info:</h1>

                    </div>

                    
                </div>
                    
                
                <form name="changeProfile" action="client_ChangeProfile" method="POST" id="changeProfile">
                    <div id="main-container">
                        
                        <div id="profile-container">
                            
                            <p class="key">Technician ID:</p>
                            <input type="text" name="txt_clientID" value="" size="15" readonly="readonly" class="value"/>
                            <p class="key">First Name:</p>
                            <input type="text" name="txt_firstName" value="" size="15" class="value"/>
                            <p class="key">Last Name:</p>
                            <input type="text" name="txt_lastName" value="" size="15" class="value"/>
                            <p class="key">Phone Number:</p>
                            <input type="text" name="txt_phoneNumber" value="" size="15" class="value"/>
                            <p class="key">Email:</p>
                            <input type="text" name="txt_email" value="" size="15" class="value"/>
                            <p class="key">Notes:</p>
                            <input type="text" name="txt_Notes" value="" size="15" class="value"/>
                            
                            <div id="submit-container">

                            <input type="submit" value="Save Info" name="saveNote" class="btn" id="saveProfile"/>

                            </div>
                        </div>
                        
                        <div id="addressText-container">
                            
                            <h1 class="head">Client Info:</h1>
                            
                        </div>
                        <div id="address-container">

                            
                            <p class="key"><label for="drop_country">Country:</label></p>
                            <input type="text" name="txt_Country" value="" size="" class="value"/>
                            
                            <p class="key"><label for="drop_state">State:</label></p>
                            <input type="text" name="txt_State" value="" size="" class="value"/>
                            
                            <p class="key"><label for="drop_city">City:</label></p>
                            <input type="text" name="txt_City" value="" size="" class="value"/>

                            <p class="key"><label for="txt_StreetName">Street name:</label></p>
                            <input type="text" name="txt_StreetName" size="" placeholder="" value="" class="value"/>

                            <p class="key"><label for="date">Requested Date:</label></p>
                            <input type="text" name="txt_Date" value="" size="" class="value"/>
                            
                            <p class="key"><label for="clientName">Client Name:</label></p>
                            <input type="text" name="txt_ClinetName" value="" size="" class="value"/>
                            
                            <p class="key"><label for="clientProblem">Client Problem:</label></p>
                            <input type="text" name="txt_ClinetProblem" value="" size="" class="value"/>
                            
                            <p class="key"><label for="clientPhone">Client Phone:</label></p>
                            <input type="text" name="txt_ClinetPhone" value="" size="" class="value"/>

                        </div> 
                        <div>
                            <p class="key"><label for="ChangeStatus">Change Status:</label></p>
                            <select name="Change Status">
                                <option>In Progress</option>
                                <option>Outstanding</option>
                                <option>Finished </option>
                            </select>
                            
                            <input type="submit" value="Update Status" name="Update_Status" class="btn" id="saveProfile"/>
                            <input type="submit" value="Go Back" name="Go_Back" class="btn" id="saveProfile"/>
                        </div>
                        
                    </div>
                         
                </form>
                
                
            </div>
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
                
                
        
    </body>
</html>
