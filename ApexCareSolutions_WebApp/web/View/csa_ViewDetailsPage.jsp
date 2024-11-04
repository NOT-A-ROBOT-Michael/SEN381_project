

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSA Details Page</title>
        <link rel="stylesheet" href="./CSS/Styling.css"/>
        <link rel="stylesheet" href="./CSS/ViewDetailsCSA.css"/>
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
                    <h1 id="title">Service Details</h1>
                    
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
                        <li class="link-container"><a href="./technician_HomePage" class="links" id="current-page">Home</a></li>
                        <li class="link-container"><a href="#" class="links">Clients</a></li>
                        <li class="link-container"><a href="#" class="links">Technicians</a></li>
                    </ul>
                </nav>
                
            </div>
            
        </header>
        <section>
            
            <%
                
                String serviceID = "", clientID = "";
                String serviceTitle = "", skillCategory = "", description = "", priority = "", status = "", country = "", state = "", city = "", streetName = "", tech_FirstName = "", tech_LastName = "";
                String client_FirstName = "", client_LastName = "", phoneNumber = "", email = "";
                String techFullName = "Unassigned";
                
                String[] csa_ServiceDetails = (String[]) request.getAttribute("csa_ServiceDetails");
                
                if(csa_ServiceDetails != null)
                {
                
                    serviceID = csa_ServiceDetails[0];
                    clientID = csa_ServiceDetails[1];
                    client_FirstName = csa_ServiceDetails[2];
                    client_LastName = csa_ServiceDetails[3];
                    phoneNumber = csa_ServiceDetails[4];
                    email = csa_ServiceDetails[5];
                    serviceTitle = csa_ServiceDetails[6];
                    skillCategory = csa_ServiceDetails[7];
                    description = csa_ServiceDetails[8];
                    priority = csa_ServiceDetails[9];
                    status = csa_ServiceDetails[10];
                    country = csa_ServiceDetails[11];
                    state = csa_ServiceDetails[12];
                    city = csa_ServiceDetails[13];
                    streetName = csa_ServiceDetails[14];
                    
                    if(csa_ServiceDetails[15] != null)
                    {
                        tech_FirstName = csa_ServiceDetails[15];
                        tech_LastName = csa_ServiceDetails[16];
                        
                        techFullName = tech_FirstName + " " + tech_LastName;
                    }
                    
                    
                    
                }
            
            %>
            
            <div id="main-container">
                
                <div id="btn-container">

                    <button onclick="window.location.href='./technician_HomePage'" class="return-login-button">&#9664 Return to Login</button>

                </div>
                
                <h1 id="block-title">Service Details:</h1>
                <div id="sub-container">
                    
                    <div id="left-container">

                        <h2 class="section-head">Service ID Number:</h2>
                        <h3><%= serviceID %></h3>

                        <h2 class="section-head">Client Details:</h2>

                        <h3>Client ID: <%= clientID %></h3>

                        <h3>Client Name: <%= client_FirstName %> <%= client_LastName %></h3>


                        <h3>Client Phone Number: <%= phoneNumber %></h3>


                        <h3>Client Email: <%= email %></h3>


                        <h2 class="section-head">Service Details:</h2>

                        <h3>Service Title: <%= serviceTitle %></h3>


                        <h3>Technician: <%= techFullName %></h3>


                        <h3>Service Type: <%= skillCategory %></h3>


                        

                    </div>

                    <div id="right-container">

                        <div>

                            <h3>Service Urgency: <%= priority %></h3>


                            <h3>Service Status: <%= status %></h3>


                        </div>

                        <div>

                            <h2 class="section-head">Service Address:</h2>

                            <h3>Country: <%= country %></h3>


                            <h3>State: <%= state %></h3>


                            <h3>City: <%= city %></h3>


                            <h3>Street Name: <%= streetName %></h3>
                            
                            <h2 class="section-head">Service Description:</h2>
                            <textarea id="id" name="name" rows="5" cols="10" readonly><%= description %></textarea>

                        </div>

                    </div>
                    
                </div>
                    
                
            </div>
                
                
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>       
    </body>
</html>
