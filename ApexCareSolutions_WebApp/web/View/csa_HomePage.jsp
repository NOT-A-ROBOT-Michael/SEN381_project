<%@page import="java.util.ArrayList"%>
<!--Call Service Agent Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="./CSS/Styling.css"/>
        <link rel="stylesheet" href="./CSS/HomePageOfCSA.css"/>
    </head>
    <body>
        <header>
            
            <%
                // The following will check if the user that is directed to this page
                // is logged in, it will also check if they are a valid user.
                String[] userDetails = (String[]) session.getAttribute("userDetails");
                String userType = (String) session.getAttribute("userType");
                String fullName = "";
                if(userDetails != null)
                {
                    if(userType.equals("CSA"))
                    {
                        // If the user exists, they will receive access
                        fullName= userDetails[1] + " " + userDetails[2];
                    }
                    else
                    {
                        // If they do not exist they will receive an error.
                        response.sendRedirect("../?Error=Incorrect user type");
                    }
                    
                }
                else
                {
                    // If they do not exist they will receive an error.
                    response.sendRedirect("../?Error=Please log in first");
                }
                
            %>
            
            
            <!--Title Bar-->
            <div>
                <!-- Div that contains the title-->
                <div id="top-ribbon">
                    <img src="./Images/Logo.png" alt="Logo" id="logo"/>
                    <h1 id="title">Home Page</h1>
                    
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
                        <li class="link-container"><a href="#" class="links" id="current-page">Home</a></li>
                        <li class="link-container"><a href="#" class="links">Clients</a></li>
                        <li class="link-container"><a href="#" class="links">Technicians</a></li>
                    </ul>
                </nav>
                
            </div>
            
        </header>
        <section>
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
                            <p class="fake-btn"></p>
                            
                        </div>
                        
                        
                    </li>
                    <%
                    
                        ArrayList<String[]> services = (ArrayList<String[]>) request.getAttribute("serviceInfo");
                        
                        Integer i = 1;
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service[4].equals("Closed by Technician"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetailsCSA\" method=\"POST\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service[0] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service[1] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service[2] + " " + service[3] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service[4] +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
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
                            <p class="fake-btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service[4].equals("Ongoing"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetailsCSA\" method=\"POST\">"
                                                + "<div class=\"list-container\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service[0] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service[1] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service[2] + " " + service[3] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service[4] +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
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
                        <div class="list-container-title-pending">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text-service">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="fake-btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service[4].equals("Pending"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block-pending\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetailsCSA\" method=\"POST\" class=\"view-form\">"
                                                + "<div class=\"list-container-pending\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service[0] +"\"/>"
                                                    + "<input class=\"info-text-service\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service[1] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\"Unassigned\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service[4] +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"assignTech\" action=\"addTechToService\" method=\"POST\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service[0] +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"hidden\" name=\"hidden-SendMessage\" value=\"0\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Add Service\" name=\"btn-AddService\" class=\"btn-Add\"/>"
                                                + "</form>"
                                            + "</div>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"declineService\" action=\"declineRequestedService\" method=\"POST\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service[0] +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Decline Service\" name=\"btn-DeclineService\" class=\"btn-Decline\"/>"
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
                    <li class="serviceItem"><h1 class="titleOfBlock" id="declined-title">Declined</h1></li>
                    <li class="item-Titles">
                        <div class="list-container-title-pending">
                            
                            <p class="info-text">Service ID</p>
                            <p class="info-text-service">Service Title</p>
                            <p class="info-text">Technician Name</p>
                            <p class="info-text">Service Status</p>
                            <p class="fake-btn"></p>
                            
                        </div>
                    </li>
                    <%
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                if(service[4].equals("Declined"))
                                {
                                    out.println("<li class=\"serviceItem\">"
                                        + "<div class=\"outside-block-pending\">"
                                            + "<form name=\"viewMore-" + (i) + "\" action=\"viewMoreDetailsCSA\" method=\"POST\" class=\"view-form\">"
                                                + "<div class=\"list-container-pending\">" 
                                                    + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service[0] +"\"/>"
                                                    + "<input class=\"info-text-service\" name=\"serviceTitle\" readonly=\"readonly\" size=\"3\" value=\""+ service[1] +"\"/>"
                                                    + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\"Unassigned\"/>"
                                                    + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service[4] +"\"/>"
                                                    + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                                + "</div>"
                                            + "</form>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"assignTech\" action=\"addTechToService\" method=\"POST\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service[0] +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"hidden\" name=\"hidden-SendMessage\" value=\"0\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Add Service\" name=\"btn-AddService\" class=\"btn-Add\"/>"
                                                + "</form>"
                                            + "</div>"
                                            + "<div class=\"survey-block\">"
                                                + "<form name=\"declineService\" action=\"declineRequestedService\" method=\"POST\">"
                                                    + "<input type=\"hidden\" name=\"hidden-ServiceID\" value=\""+ service[0] +"\" size=\"15\" readonly=\"readonly\" />"
                                                    + "<input type=\"submit\" value=\"Decline Service\" name=\"btn-DeclineService\" class=\"btn-Decline\"/>"
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
