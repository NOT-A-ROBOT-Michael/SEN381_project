<%-- 
    Document   : ClientContractDetails
    Created on : 31 Oct 2024, 10:12:36
    Author     : iyesme
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientService"%>
<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.MR_ClientAddress"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contract Details</title>
        <link rel="stylesheet" href="./CSS/styling.css"/>
        <link rel="stylesheet" href="./CSS/clientLogProblem.css"/>
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
                    if(userType.equals("C"))
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
            
            <%
                MR_ClientService serviceDetails = (MR_ClientService)session.getAttribute("ServiceDetails");
            %>
            
            <!--Title Bar-->
            <div>
                <!-- Div that contains the title-->
                <div id="top-ribbon">
                    <img src="./Images/Logo.png" alt="Logo" id="logo"/>
                    <h1 id="title">Log Problem</h1>
                    
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
                        <li class="link-container"><a href="./client_HomePage"" class="links" id="current-page">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Log Problem</a></li>
                        <li class="link-container"><a href="./ClientContracts" class="links">View Contracts</a></li>
                        <li class="link-container"><a href="./ClientContact" class="links">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links">Profile</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <form name="Problem Details" action="viewSurvey" method="POST">
                
                <div id="main-container">
                    
                    <h1 id="block-title">Problem Details: <% out.println(serviceDetails.serviceID); %></h1>
                    
                    <%
                        
                        String[] messageInfo = (String[]) request.getAttribute("message");
                        

                        if(messageInfo != null)
                        {
                            String text = messageInfo[0];
                            String textColour = messageInfo[1];

                            out.println("<h1 style=\"color: "+ textColour +"\" id=\"warning\">" + text + "</h1>");
                        }

                        request.setAttribute("message", null);

                    %>
                    
                    <div id="form-inputs">
                    
                        <div id="left-block">
                            
                            <% out.println("<input type=\"hidden\" name=\"serviceID\" value=\""+ serviceDetails.serviceID +"\" size=\"15\" readonly=\"readonly\" />"); %>

                            <h4 class="key"><label for="txt_ProblemTitle">Problem Title:</label></h4>
                            <input type="text" name="txt_ProblemTitle" id="txt_ProblemTitle" size="20" value="<% out.println(serviceDetails.serviceTitle); %>" maxlength="20" disabled/>

                            <h4 class="key"><label for="status">Status:</label></h4>
                            <input type = "text" name= "status" value = "<% out.println(serviceDetails.status); %>" disabled>
                                <%
                                    
                                    /*
                                    ArrayList<String> serviceTypes = (ArrayList<String>) request.getAttribute("serviceTypes");

                                    if(serviceTypes != null)
                                    {
                                        for(var i : serviceTypes)
                                        {
                                            out.println("<option value='" + i + "'>" + i + "</option>");
                                        }
                                    }*/
                                %>
                                

                            <h4 class="key"><label for="txtB_Description">Brief Description:</label></h4>
                            <textarea id="txtB_Description" name="txtB_Description" rows="4" cols="20" style="position: inherit" maxlength="5500" disabled><% out.println(serviceDetails.description); %></textarea>

                        </div>



                        <div id="right-block">
                            <div>                               
                                <h4 class="key"><label for="ServiceID">Problem ID:</label></h4>
                                <input type="text" name="serviceID" id="txt_ProblemTitle" size="20" value="<% out.println(serviceDetails.serviceID); %>" maxlength="20" disabled/>

                                <h4 class="key"><label for="Urgency">Urgency:</label></h4>
                                <input type = "text" name ="Urgency" value = "<% out.println(serviceDetails.priority); %>" disabled>
                            </div>
                            
                            

                            <%
                                MR_ClientAddress addressInfo = serviceDetails.getClientAddress();
                                

                            %>

                            <div id="address-container">
                                <h3 id="address-title">Service Address:</h3>    

                                <p class="address-key"><label for="drop_country">Country:</label></p>
                                <input type="text" value ="<% out.println(addressInfo.country()); %>" disabled>
                                    
                                <p class="address-key"><label for="drop_state">State:</label></p>
                                <input type="text" value ="<% out.println(addressInfo.state()); %>" disabled>
                                
                                <p class="address-key"><label for="drop_city">City:</label></p>
                                <input type="text" value ="<% out.println(addressInfo.city()); %>" disabled>

                                <p class="address-key"><label for="txt_StreetName">Street name:</label></p>
                                <input type="text" value ="<% out.println(addressInfo.street()); %>" disabled>

                            </div>

                        </div>
                    </div>    
                    
                    <div id="submit-container">
                        <input type="submit" value="Write Servey" name="requestSubmission" id="requestSubmission"/>
                    </div>
                    
                    
                </div>
                
                        
                
            </form>
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
    </body>
</html>
