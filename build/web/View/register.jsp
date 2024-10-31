<%-- 
    Document   : Register
    Created on : 28 Oct 2024, 10:39:29
    Author     : morne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <script>
            function showFields()
            {
                var role = document.getElementById("roleSelect").value;
                
                //initially hides all the fields
                document.getElementById("clientFields").style.display = "none";
                document.getElementById("technicianFields").style.display = "none";
                document.getElementById("serviceAgentFields").style.display = "none";
                
                //Show fields based on role selected
                if (role === "Client") {
                    document.getElementById("clientFields").style.display = "block";
                    document.getElementById("address").style.display = "block";
                    document.forms['Register'].action = "registerClient";
                    form.action = "registerClient";
                } else if  (role === "Technician") {
                    document.getElementById("technicianFields").style.display = "block";
                    document.getElementById("address").style.display = "block";
                    document.forms['Register'].action = "registerTechnician";
                    form.action = 'registerTechnician';
                } else if (role === "ServiceAgent") {
                    document.getElementById("serviceAgentFields").style.display = "block";
                    document.getElementById("address").style.display = "none";
                    document.forms['Register'].action = "registerServiceAgent";       
                    form.action = "registerServiceAgent";
                }
            }
        </script>
    </head>
    <body>
        <h1>Registering a new User</h1>

         <!-- Display error message if registration fails -->
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        
        <form name="Register" action="register" method="POST">
            <label for="roleSelect"> Select Role:</label><br/>
            <select name="role" id="roleSelect" onchange="showFields()">
                <option value = "" disabled selected>Select a role</option>
                <option value = "Client">Client</option>
                <option value = "Technician">Technician</option>
                <option value = "ServiceAgent">Service Agent</option>
            </select><br/>            
            
            <!-- Client Fields -->
            <div id="clientFields" style="display:none;">
                <label for="name">Name:</label><br/>
                <input type="text" name="name" value="" /><br/>

                <label for="surname">Surname:</label><br/>
                <input type="text" name="surname" value="" /><br/>

                <label for="email">Email:</label><br/>
                <input type="text" name="email" value="" /><br/>

                <label for="phone">Phone Number:</label><br/>
                <input type="text" name="phone" value="" /><br/>

                <label for="password">Password:</label><br/>
                <input type="password" name="password" value="" /><br/>

                <label for="confirmPassword">Confirm Password:</label><br/>
                <input type="password" name="confirmPassword" value="" /><br/>
            </div>
            
            <!-- Technician Fields -->
            <div id="technicianFields" style="display:none;">            
                <label for="name_Technician">Name:</label><br/>
                <input type="text" name="name_Technician" value="" /><br/>

                <label for="surname_Technician">Surname:</label><br/>
                <input type="text" name="surname_Technician" value="" /><br/>

                <label for="email_Technician">Email:</label><br/>
                <input type="text" name="email_Technician" value="" /><br/>

                <label for="phone_Technician">Phone Number:</label><br/>
                <input type="text" name="phone_Technician" value="" /><br/>

                <label for="specialization_Technician">Specialization:</label></br>
                <select name="specialization_Technician" id="specialization">
                    <option value="0"dis >
                    <option value="15">Mechanic</option>
                    <option value="16">Diagnostician</option>
                </select></br>

                <label for="password_Technician">Password:</label><br/>
                <input type="password" name="password_Technician" value="" /><br/>

                <label for="confirmPassword_Technician">Confirm Password:</label><br/>
                <input type="password" name="confirmPassword_Technician" value="" /><br/>
            </div>

            <!-- Service Agent Fields -->
            <div id="serviceAgentFields" style="display:none;">
                <label for="name">Name:</label><br/>
                <input type="text" name="name_ServiceAgent" value="" /><br/>

                <label for="surname">Surname:</label><br/>
                <input type="text" name="surname_ServiceAgent" value="" /><br/>

                <label for="email">Email:</label><br/>
                <input type="text" name="email_ServiceAgent" value="" /><br/>

                <label for="phone">Phone Number:</label><br/>
                <input type="text" name="phone_ServiceAgent" value="" /><br/>

                <label for="password">Password:</label><br/>
                <input type="password" name="password_ServiceAgent" value="" /><br/>

                <label for="confirmPassword">Confirm Password:</label><br/>
                <input type="password" name="confirmPassword_ServiceAgent" value="" /><br/>
            </div>
            
            <div id="address" style="display:none;">
                
                <h3 id="address-title">Service Address:</h3>    

                    <p class="address-key"><label for="drop_country">Select a Country:</label></p>
                    <select name="drop_country" title="Select a Country">
                        <option value="None">Select a Country</option>
                        <option value="South Africa">South Africa</option>
                        <option value="Missing">My country is not listed</option>

                    </select>
                    <p class="address-key"><label for="drop_state">State:</label></p>
                    <select name="drop_state" title="Select a State">
                        <option value="None">Select a State</option>
                        <option value="Gauteng">Gauteng</option>
                        <option value="Missing">My state is not listed</option>

                    </select>
                    <p class="address-key"><label for="drop_city">City:</label></p>
                    <select name="drop_city" title="Select a City">
                        <option value="None">Select a City</option>
                        <option value="Pretoria">Pretoria</option>
                        <option value="Johannesburg">Johannesburg</option>
                        <option value="Midrand">Midrand</option>
                        <option value="Benoni">Benoni</option>
                        <option value="Krugersdorp">Krugersdorp</option>
                        <option value="Germiston">Germiston</option>
                        <option value="Vereeniging">Vereeniging</option>
                        <option value="Boksburg">Boksburg</option>
                        <option value="Springs">Springs</option>
                        <option value="Randfontein">Randfontein</option>
                        <option value="Missing">My city is not listed</option>

                    </select>

                    <p class="address-key"><label for="txt_StreetName">Enter street name:</label></p>
                    <input type="text" name="txt_StreetName" size="15" placeholder="Enter street name..."/>
                
            </div>
                
            <input type="submit" value="Register" name="Register" />
        </form>
    
    <a href="./" class="return-link">Return to Login</a>
    </body>
</html>
