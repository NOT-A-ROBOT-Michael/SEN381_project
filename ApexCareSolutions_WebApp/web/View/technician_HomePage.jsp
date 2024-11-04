<%@page import="java.util.ArrayList"%>
<!--Technician Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <body>
        
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="./CSS/Styling.css"/>
        <link rel="stylesheet" href="./CSS/TechnicianHomePage.css"/>
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
                        <li class="link-container"><a href="./technician_HomePage" class="links" id="current-page">Task Page</a></li>
                        <li class="link-container"><a href="./technicianProfile" class="links">Profile</a></li>
                        <li class="link-container"><a href="./TechnicianContactPage" class="links">Contact Page</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <div id="main-container">
               
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="ongoing-title">Tasks Available</h1></li>
                    <li class="item-Titles">
                        <div  class="list-container-title">
                            
                            <p class="info-text">Priority</p>
                            <p class="info-text">Service ID</p>
                            <p class="info-text">Status</p>
                            <p class="info-text">Requested Date</p>
                            <p class="btn"></p>
                            
                        </div>
                    </li>
                    <%
                         ArrayList<String[]> services = (ArrayList<String[]>) request.getAttribute("serviceInfo");
                        
                        Integer i = 1;
                        
                        System.out.println("Service: " + request.getAttribute("serviceInfo"));
                        
                        if(services != null)
                        {
                            for(var service : services)
                            {
                                
                                out.println("<li class=\"serviceItem\">"
                                    + "<div class=\"outside-block\">"
                                        + "<form name=\"viewMore-" + (i) + "\" action=\"Technician_Details_Page\" method=\"Post\" class=\"view-form\">"
                                            + "<div class=\"list-container\">" 
                                                + "<input class=\"info-text\" name=\"Priority\" readonly=\"readonly\" size=\"3\" value=\""+ service[0] +"\"/>"
                                                + "<input class=\"info-text\" name=\"serviceID\" readonly=\"readonly\" size=\"3\" value=\""+ service[1] +"\"/>"
                                                + "<input class=\"info-text\" name=\"fullTechName\" readonly=\"readonly\" size=\"3\" value=\""+ service[2] + "\"/>"
                                                + "<input class=\"info-text\" name=\"serviceStatus\" readonly=\"readonly\" size=\"3\" value=\""+ service[3] +"\"/>"
                                                + "<input type=\"submit\" value=\"View Details\" name=\"btn-ViewDetails\" class=\"btn\"/>"
                                            + "</div>"
                                        + "</form>"
                                    + "</div>"
                                + "</li>");
                                
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
