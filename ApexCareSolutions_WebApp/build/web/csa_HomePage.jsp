<!--Call Service Agent Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="./styling.css"/>
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
                        <li class="link-container"><a href="#" class="links" id="current-page">Home</a></li>
                        <li class="link-container"><a href="#" class="links">Clients</a></li>
                        <li class="link-container"><a href="#" class="links">Technicians</a></li>
                    </ul>
                </nav>
                
            </div>
            
        </header>
        <section>
            <!--Section that will contain information-->
            
        </section>
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>       
    </body>
</html>
