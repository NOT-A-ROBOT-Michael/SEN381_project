<%@page import="sen381_project.Bussiness_Logic_Layer.Objects.MR_Survey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey Page</title>
        <link rel="stylesheet" href="./CSS/styling.css"/>
        <link rel="stylesheet" href="./CSS/surveyStyling.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <script type="text/javascript" src="./JavaScript/StarScript.js" defer></script>
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
                    <h1 id="title">Survey</h1>
                    
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
                        <li class="link-container"><a href="./client_HomePage" class="links" id="current-page">Home</a></li>
                        <li class="link-container"><a href="./client_LogProblem" class="links">Log Problem</a></li>
                        <li class="link-container"><a href="./ClientContracts" class="links">View Contracts</a></li>
                        <li class="link-container"><a href="./ClientContact" class="links">Contact</a></li>
                        <li class="link-container"><a href="./client_ProfilePage" class="links">Profile</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <section id="section-container">
            <!--Section that will contain information-->
            <%
                
                MR_Survey survey = (MR_Survey) request.getAttribute("Survey");
            %>
            <div id="main-container">
                <form method="POST" action="SubmitSurvey">
                    <h1 id="block-title">Survey: <% out.println(survey.getServiceID()); %></h1>
                    <script>console.log(<% out.println(survey.getServiceID()); %>);</script>
                <input type="hidden" name="hidden-ServiceID" value="<%= survey.getServiceID()%>" readonly="readonly" >
                
                
                <div id="center-block">
                    <div class="option-block">
                        <input type="hidden" name="hidden-rateSer" id="hidden-rateSer" value="<%= survey.getRateBackEndService() %>" readonly="readonly" >
                        <label>Rate Service:</label>
                        <div class ="Star-container Ser">
                            <i class="fa fa-star fa-2x<% if(survey.getRateBackEndService()>= 1){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateBackEndService()>= 2){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateBackEndService()>= 3){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateBackEndService()>= 4){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateBackEndService()== 5){out.println(" checked");} %>"></i>
                        </div>
                    </div>
                    
                    <div class="option-block">
                        <input type="hidden" name="hidden-rateTech" id="hidden-rateTech" value="<%= survey.getRateTechnician() %>" readonly="readonly" >
                        <label>Rate Technician:</label>
                        <div class ="Star-container Tech">
                            <i class="fa fa-star fa-2x<% if(survey.getRateTechnician()>= 1){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateTechnician()>= 2){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateTechnician()>= 3){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateTechnician()>= 4){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateTechnician()== 5){out.println(" checked");} %>"></i>
                        </div>
                    </div>
                    
                    <div class="option-block">
                        <input type="hidden" name="hidden-rateSA" id="hidden-rateSA" value="<%= survey.getRateServiceAgent() %>" readonly="readonly" >
                        <label>Rate Call Service Agent:</label>
                        <div class ="Star-container SA">
                            <i class="fa fa-star fa-2x<% if(survey.getRateServiceAgent()>= 1){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateServiceAgent()>= 2){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateServiceAgent()>= 3){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateServiceAgent()>= 4){out.println(" checked");} %>"></i>
                            <i class="fa fa-star fa-2x<% if(survey.getRateServiceAgent()== 5){out.println(" checked");} %>"></i>
                        </div>
                    </div>
                    
                    <input type="submit" value="Save Survey">
                </div>
                </form>
                
            </div>
            
        </section>
                
        <footer>
            <!--Section that will contain icons used to direct users to contacts or chat websites-->
            
        </footer>
    </body>
</html>
