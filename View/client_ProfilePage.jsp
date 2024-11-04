<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link rel="stylesheet" href="./CSS/styling.css"/>
        <link rel="stylesheet" href="./CSS/profile_Styling.css"/>
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
                        <li class="link-container"><a href="./client_HomePage"" class="links">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Log Problem</a></li>
                        <li class="link-container"><a href="#" class="links">View Contracts</a></li>
                        <li class="link-container"><a href="#" class="links">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links" id="current-page">Profile</a></li>
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

                        <h1 class="head">Profile:</h1>

                    </div>

                    <div id="pass-container">

                        <h1 class="head">Change Password:</h1>
                        <form name="changePass" action="client_ChangePass" method="GET">
                            <input type="submit" value="Change" name="btn_Pass" class="btn"/>
                        </form>

                    </div>
                </div>
                    
                
                <form name="changeProfile" action="client_ChangeProfile" method="POST" id="changeProfile">
                    <div id="main-container">
                        
                        <div id="profile-container">
                            
                            <p class="key">Client ID:</p>
                            <input type="text" name="txt_clientID" value="<%=clientInfo[0]%>" size="15" readonly="readonly" class="value"/>
                            <p class="key">First Name:</p>
                            <input type="text" name="txt_firstName" value="<%=clientInfo[2]%>" size="15" class="value"/>
                            <p class="key">Last Name:</p>
                            <input type="text" name="txt_lastName" value="<%=clientInfo[3]%>" size="15" class="value"/>
                            <p class="key">Phone Number:</p>
                            <input type="text" name="txt_phoneNumber" value="<%=clientInfo[4]%>" size="15" class="value"/>
                            <p class="key">Email:</p>
                            <input type="text" name="txt_email" value="<%=clientInfo[5]%>" size="15" class="value"/>
                        </div>
                        
                        <div id="addressText-container">
                            
                            <h1 class="head">Address:</h1>
                            
                        </div>
                        <div id="address-container">

                            
                            <p class="key"><label for="drop_country">Country:</label></p>
                            <select name="drop_country" title="Select a Country" class="value">
                                <option value="None" <%= "None".equals(addressInfo[1]) ? "selected" : "" %>>Select a Country</option>
                                <option value="South Africa" <%= "South Africa".equals(addressInfo[1]) ? "selected" : "" %>>South Africa</option>
                                <option value="Missing" <%= "Missing".equals(addressInfo[1]) ? "selected" : "" %>>My country is not listed</option>

                            </select>
                            <p class="key"><label for="drop_state">State:</label></p>
                            <select name="drop_state" title="Select a State" class="value">
                                <option value="None" <%= "None".equals(addressInfo[2]) ? "selected" : "" %>>Select a State</option>
                                <option value="Gauteng" <%= "Gauteng".equals(addressInfo[2]) ? "selected" : "" %>>Gauteng</option>
                                <option value="Missing" <%= "Missing".equals(addressInfo[2]) ? "selected" : "" %>>My state is not listed</option>

                            </select>
                            <p class="key"><label for="drop_city">City:</label></p>
                            <select name="drop_city" title="Select a City" class="value">
                                <option value="None" <%= "None".equals(addressInfo[3]) ? "selected" : "" %>>Select a City</option>
                                <option value="Pretoria" <%= "Pretoria".equals(addressInfo[3]) ? "selected" : "" %>>Pretoria</option>
                                <option value="Johannesburg" <%= "Johannesburg".equals(addressInfo[3]) ? "selected" : "" %>>Johannesburg</option>
                                <option value="Midrand" <%= "Midrand".equals(addressInfo[3]) ? "selected" : "" %>>Midrand</option>
                                <option value="Benoni" <%= "Benoni".equals(addressInfo[3]) ? "selected" : "" %>>Benoni</option>
                                <option value="Krugersdorp" <%= "Krugersdorp".equals(addressInfo[3]) ? "selected" : "" %>>Krugersdorp</option>
                                <option value="Germiston" <%= "Germiston".equals(addressInfo[3]) ? "selected" : "" %>>Germiston</option>
                                <option value="Vereeniging" <%= "Vereeniging".equals(addressInfo[3]) ? "selected" : "" %>>Vereeniging</option>
                                <option value="Boksburg" <%= "Boksburg".equals(addressInfo[3]) ? "selected" : "" %>>Boksburg</option>
                                <option value="Springs" <%= "Springs".equals(addressInfo[3]) ? "selected" : "" %>>Springs</option>
                                <option value="Randfontein" <%= "Randfontein".equals(addressInfo[3]) ? "selected" : "" %>>Randfontein</option>
                                <option value="Missing" <%= "Missing".equals(addressInfo[3]) ? "selected" : "" %>>My city is not listed</option>

                            </select>

                            <p class="key"><label for="txt_StreetName">Street name:</label></p>
                            <input type="text" name="txt_StreetName" size="15" placeholder="Enter street name..." value="<%=addressInfo[4]%>" class="value"/>

                            

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
