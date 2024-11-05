<%-- 
    Document   : ClientContractsjsp
    Created on : 29 Oct 2024, 09:43:45
    Author     : iyesme
--%>
<%@page import="businesslogiclayer.object.MR_ClientServiceListItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contracts Page</title>
        <link rel="stylesheet" href="./CSS/Styling.css"/>
        <link rel="stylesheet" href="./CSS/ClientHomePage.css"/>
        <link rel="stylesheet" href="./CSS/ClientContracts.css"/>
        
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
            
            <!--Title Bar-->
            <div>
                <!-- Div that contains the title-->
                <div id="top-ribbon">
                    <img src="./Images/Logo.png" alt="Logo" id="logo"/>
                    <h1 id="title">Contracts Page</h1>
                    
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
                        <li class="link-container"><a href="./client_HomePage" class="links">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Log Problem</a></li>
                        <li class="link-container"><a href="./ClientContracts" class="links" id="current-page">View Contracts</a></li>
                        <li class="link-container"><a href="./ClientContact" class="links">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links">Profile</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <div id="main-container">
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="ready-title">Ready</h1></li>
                    <li class="item-Titles">
                        <div class="list-container-title">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="btn"></p>
                            
                        </div>
                        
                        
                    </li>
                    <%
                    
                        ArrayList<MR_ClientServiceListItem> services = (ArrayList<MR_ClientServiceListItem>) request.getAttribute("ServiceItems");
                        
                        System.out.println("Service: " + request.getAttribute("serviceInfo"));
                        
                        Integer i = 1;
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service.GetStatus().equals("Closed by Technician")   )//.equals("Closed by Technician"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetails\" method=\"Post\" class=\"view-form\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetServiceID().toString() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service.getServiceTitle() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetTechnicianName() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetStatus() +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"viewSurvey-" + (i++) + "\" action=\"viewSurvey\" method=\"Post\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service.GetServiceID().toString() +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Complete Survey\" name=\"btn-CompleteSurvey\" class=\"btn\"/>"
                                                + "</form>"
                                            + "</div>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"viewSurvey-" + (i++) + "\" action=\"completeService\" method=\"Post\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service.GetServiceID().toString() +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Complete Service\" name=\"btn-CompleteService\" class=\"btn\"/>"
                                                + "</form>"
                                            + "</div>"
                                        + "</div>"
                                    + "</li>");
                                }
                            }
                        }
                    %>
                </ul>
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="ongoing-title">Ongoing</h1></li>
                    <li class="item-Titles">
                        <div  class="list-container-title">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        System.out.println("Service: " + request.getAttribute("ServiceItems"));
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service.GetStatus().equals("Ongoing"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetails\" method=\"Post\" class=\"view-form\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetServiceID().toString() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service.getServiceTitle() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetTechnicianName() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetStatus() +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"viewSurvey-" + (i++) + "\" action=\"viewSurvey\" method=\"Post\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service.GetServiceID().toString() +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Complete Survey\" name=\"btn-CompleteSurvey\" class=\"btn\"/>"
                                                + "</form>"
                                            + "</div>"
                                        + "</div>"
                                    + "</li>");
                                }
                            }
                        }
                    %>
                </ul>
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="pending-title">Pending</h1></li>
                    <li class="item-Titles">
                        <div class="list-container-title">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        System.out.println("Service: " + request.getAttribute("ServiceItems"));
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service.GetStatus().equals("Pending"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetails\" method=\"Post\" class=\"view-form\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetServiceID().toString() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service.getServiceTitle() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\"Unassigned\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetStatus() +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"viewSurvey-" + (i++) + "\" action=\"viewSurvey\" method=\"Post\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service.GetServiceID().toString() +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Complete Survey\" name=\"btn-CompleteSurvey\" class=\"btn\"/>"
                                                + "</form>"
                                            + "</div>"
                                        + "</div>"
                                    + "</li>");
                                }
                            }
                        }
                    %>
                </ul>
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="completed-title">Completed</h1></li>
                    <li class="item-Titles">
                        <div class="list-container-title">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        System.out.println("Service: " + request.getAttribute("ServiceItems"));
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service.GetStatus().equals("Completed"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetails\" method=\"Post\" class=\"view-form\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetServiceID().toString() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service.getServiceTitle() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetTechnicianName() +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service.GetStatus() +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"viewSurvey-" + (i++) + "\" action=\"viewSurvey\" method=\"Post\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service.GetServiceID().toString() +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Complete Survey\" name=\"btn-CompleteSurvey\" class=\"btn\"/>"
                                                + "</form>"
                                            + "</div>"
                                        + "</div>"
                                    + "</li>");
                                }
                            }
                        }
                    %>
                </ul>

            </div>
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
                        
    </body>
</html>
