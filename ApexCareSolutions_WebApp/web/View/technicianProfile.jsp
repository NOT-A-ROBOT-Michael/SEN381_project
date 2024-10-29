<%-- 
    Document   : technicianProfile
    Created on : 28 Oct 2024, 17:02:15
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
        <link rel="stylesheet" href="./CSS/tech_Profile_Styling.css"/>
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
                         <li class="link-container"><a href="./client_ProfilePage" class="links" id="current-page">Profile</a></li>
                        <li class="link-container"><a href="#" class="links">Contact Page</a></li>
                       
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

                        <h1 class="head">Profile:</h1>

                    </div>

                    
                </div>
                    
                
                <form name="changeProfile" action="client_ChangeProfile" method="POST" id="changeProfile">
                    <div id="main-container">
                        
                        <div id="profile-container">
                            
                            <p class="key">Client ID:</p>
                            <input type="text" name="txt_clientID" value="" size="15" readonly="readonly" class="value"/>
                            <p class="key">First Name:</p>
                            <input type="text" name="txt_firstName" value="" size="15" class="value"/>
                            <p class="key">Last Name:</p>
                            <input type="text" name="txt_lastName" value="" size="15" class="value"/>
                            <p class="key">Phone Number:</p>
                            <input type="text" name="txt_phoneNumber" value="" size="15" class="value"/>
                            <p class="key">Email:</p>
                            <input type="text" name="txt_email" value="" size="15" class="value"/>
                           
                             <h1 class="head">Change Password:</h1>
                        <form name="changePass" action="client_ChangePass" method="GET">
                            <input type="submit" value="Change" name="btn_Pass" class="btn"/>
                        </form>
                        </div>
                        
                        <div id="submit-container">

                            <input type="submit" value="Save Info" name="saveProfile" class="btn" id="saveProfile"/>

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
