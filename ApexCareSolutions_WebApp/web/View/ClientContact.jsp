<%-- 
    Document   : ClientContact
    Created on : 29 Oct 2024, 09:48:24
    Author     : iyesme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Page</title>
        <link rel="stylesheet" href="./CSS/Styling.css"/>
        <link rel="stylesheet" href="./CSS/TechContactStyling.css"/>
        <link rel="stylesheet" href="./CSS/ClientContactStyling.css">
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
                    <h1 id="title">Contact Page</h1>
                    
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
                        <li class="link-container"><a href="./client_HomePage"" class="links">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Log Problem</a></li>
                        <li class="link-container"><a href="./ClientContracts" class="links">View Contracts</a></li>
                        <li class="link-container"><a href="./ClientContact" class="links" id="current-page">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links">Profile</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            
            <div id="main-container">
               
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="ongoing-title">Contact Details</h1></li>
                    <li class="item-Titles">
                        <div  class="list-container-title">
                            
                           
                            <p class="info-text">Phone Number:</p>
                            <p class="info-text">0123456789</p>
                            <p class="info-text">Email:</p>
                            <p class="info-text">csa@serviceagent.com</p>
                            <p class="btn"></p>
                            
                        </div>
                    </li>

                </ul>
            </div>
            
            <div id="main-container">
               
                <ul class="info-container">
                    <li class="serviceItem"><h1 class="titleOfBlock" id="ongoing-title">Provide Query</h1></li>
                    
                        <div  >
                            <form action="SubmitClientContact" method="Post" id = "input-field">
                                <div id="thingTing">
                                    <div>
                                        <p class="key">Service ID:</p>
                                        <input type="text" name="serviceID" value="" size="15" class="value"id="TechText"/>
                                    </div>
                                
                                    <div>
                                        <p class="key">Service Query:</p>
                                        <input type="text" name="description" value="" size="15" class="value"id="TechText"/>
                                        
                                    </div>
                                    
                                </div>
                                <input type="submit" value="Submit" name="Submit"  class="bottomButton"/>
                                
                            </form>
                            
                        </div>
                    

                </ul>
            </div>
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
    </body>
</html>
