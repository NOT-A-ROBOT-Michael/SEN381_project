<!--Login Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="./login_Styling.css"/>
    </head>
    <body>
        <header>
            <!-- Div that contains the title-->
            <div id="top-ribbon">
                <img src="./Images/Logo.png" alt="Logo" id="logo"/>
                <h1 id="title">Apex Care Solutions</h1>

            </div>
        </header>
        <section id="main-container">
            
            <div id="container">
                
                
                <h1 id="block-title">User Login</h1>
                
                

                <%
                    // The following is used to indicate if the user 
                    // did not enter the correct information
                    String currentState = "";
                    if(session.getAttribute("state") != null)
                    {
                        // Gets the attribute of the current state of the session
                        currentState = (String) session.getAttribute("state");
                        // Clears the session
                        session.invalidate();
                    }

                %>
                <h2 id="warning"><%= currentState %></h2>
                <!--The following form requires the user information to determine if they are
                    allowed to access a certain page assigned to them.-->
                <form name="Login" action="user_login" method="POST">
                        <div class="login-inputs">
                            <p class="key">Email:</p>
                            <input type="email" name="email" size="25" placeholder="Enter email" class="value"/>
                            <p class="key">Password:</p>
                            <input type="password" name="password" size="25" placeholder="Enter Password" class="value"/>
                        </div>

                    
                    
                    <div id="btn-container">
                        
                        <input type="submit" value="Login" name="submit" class="btn"/>
                        
                        <input type="button" value="Register" name="register" onclick="display()" class="btn"/>
                    </div>
                    

                </form>
                
            </div>
                
            
            
        </section>
        <footer>
            
        </footer>
                
        <script>
            function display()
            {
                window.location.href = './register.jsp';
            }
        </script>
        
    </body>
</html>
