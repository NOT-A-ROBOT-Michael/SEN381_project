<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Problem Page</title>
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
                        <li class="link-container"><a href="./client_HomePage"" class="links">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links" id="current-page">Log Problem</a></li>
                        <li class="link-container"><a href="#" class="links">View Contracts</a></li>
                        <li class="link-container"><a href="#" class="links">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links">Profile</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section>
            <!--Section that will contain information-->
            
            
            
            <form name="submitRequest" action="submit_request" method="POST">
                
                <div id="main-container">
                    
                    <h1 id="block-title">Enter Problem Details:</h1>
                    
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

                            <h4 class="key"><label for="txt_ProblemTitle">Please give a title for your problem:</label></h4>
                            <input type="text" name="txt_ProblemTitle" id="txt_ProblemTitle" size="20" placeholder="Enter Problem Title..." maxlength="20"/>

                            <h4 class="key"><label for="drop_ServiceCategories">Please choose a category your problem is associated with:</label></h4>
                            <select name="drop_ServiceCategories">
                                <option value="None" title="Select a Category">Select a Category</option>
                                <%


                                    ArrayList<String> serviceTypes = (ArrayList<String>) request.getAttribute("serviceTypes");

                                    if(serviceTypes != null)
                                    {
                                        for(var i : serviceTypes)
                                        {
                                            out.println("<option value='" + i + "'>" + i + "</option>");
                                        }
                                    }
                                %>
                                <option value="Missing">Option not listed</option>
                            </select>

                            <h4 class="key"><label for="txtB_Description">Give a brief description of the required service</label></h4>
                            <textarea id="txtB_Description" name="txtB_Description" rows="4" cols="20" placeholder="Eneter Description..." maxlength="5500"></textarea>

                        </div>



                        <div id="right-block">

                            <h4 class="key"><label for="drop_Urgency">What is the urgency of the service you require:</label></h4>
                            <select name="drop_Urgency" title="Select an Urgency">
                                <option value="None">Select an Urgency</option>
                                <option value="Low">Low</option>
                                <option value="Medium">Medium</option>
                                <option value="High">High</option>
                            </select>

                            <h4 class="key">Please enter the address where the service should be performed:</h4>

                            <%

                                String[] addressInfo = (String[]) request.getAttribute("addressInfo");

                            %>

                            <div id="address-container">
                                <h3 id="address-title">Service Address:</h3>    

                                <p class="address-key"><label for="drop_country">Select a Country:</label></p>
                                <select name="drop_country" title="Select a Country">
                                    <option value="None" <%= "None".equals(addressInfo[1]) ? "selected" : "" %>>Select a Country</option>
                                    <option value="South Africa" <%= "South Africa".equals(addressInfo[1]) ? "selected" : "" %>>South Africa</option>
                                    <option value="Missing" <%= "Missing".equals(addressInfo[1]) ? "selected" : "" %>>My country is not listed</option>

                                </select>
                                <p class="address-key"><label for="drop_state">State:</label></p>
                                <select name="drop_state" title="Select a State">
                                    <option value="None" <%= "None".equals(addressInfo[2]) ? "selected" : "" %>>Select a State</option>
                                    <option value="Gauteng" <%= "Gauteng".equals(addressInfo[2]) ? "selected" : "" %>>Gauteng</option>
                                    <option value="Missing" <%= "Missing".equals(addressInfo[2]) ? "selected" : "" %>>My state is not listed</option>

                                </select>
                                <p class="address-key"><label for="drop_city">City:</label></p>
                                <select name="drop_city" title="Select a City">
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

                                <p class="address-key"><label for="txt_StreetName">Enter street name:</label></p>
                                <input type="text" name="txt_StreetName" size="15" placeholder="Enter street name..." value="<%=addressInfo[4]%>"/>


                            </div>

                        </div>
                    </div>    
                    
                    <div id="submit-container">
                        <input type="submit" value="Submit Request" name="requestSubmission" id="requestSubmission"/>
                    </div>
                    
                    
                </div>
                
                        
                
            </form>
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
        
    </body>
</html>
