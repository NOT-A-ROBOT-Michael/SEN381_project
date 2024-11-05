<%-- 
    Document   : Register
    Created on : 28 Oct 2024, 10:39:29
    Author     : morne
--%>

<%@page import="businesslogiclayer.logic.TechServiceLogic"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="./CSS/RegisterStyle.css"/>
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
                    document.getElementById("submit-btn").style.opacity = 1;
                    document.getElementById("submit-btn").disabled = false;
                    document.forms['Register'].action = "registerClient";
                    
                    document.getElementById("warning_1").style.display = "none";
                    document.getElementById("warning_2").style.display = "none";
                    document.getElementById("warning_3").style.display = "none";
                    
                } else if  (role === "Technician") {
                    document.getElementById("technicianFields").style.display = "block";
                    document.getElementById("address").style.display = "block";
                    document.getElementById("submit-btn").style.opacity = 1;
                    document.getElementById("submit-btn").disabled = false;
                    document.forms['Register'].action = "registerTechnician";
                    
                    document.getElementById("warning_1").style.display = "none";
                    document.getElementById("warning_2").style.display = "none";
                    document.getElementById("warning_3").style.display = "none";
                    
                } else if (role === "ServiceAgent") {
                    document.getElementById("serviceAgentFields").style.display = "block";
                    document.getElementById("address").style.display = "none";
                    document.getElementById("submit-btn").style.opacity = 1;
                    document.getElementById("submit-btn").disabled = false;
                    document.forms['Register'].action = "registerServiceAgent";
                    
                    document.getElementById("warning_1").style.display = "none";
                    document.getElementById("warning_2").style.display = "none";
                    document.getElementById("warning_3").style.display = "none";
                    
                }
            }
        </script>
    </head>
    <body>
        <header>
            
            <div id="top-ribbon">
                <img src="./Images/Logo.png" alt="Logo" class="logo">
                <h1 class="title">Apex Care Solutions</h1>
            </div>
            
        </header>
        <section id="main-container">
            <div id="container">



                <div id="btn-container">

                    <button onclick="window.location.href='./'" class="return-login-button">&#9664 Return to Login</button>

                </div>

                <h1>Register:</h1>

                <!-- Display error message if registration fails -->
                <c:if test="${not empty error}">
                    <p class="warning" style="display: ${"block".equals(displayError) ? "block" : "none"}; color: red;" id="warning_3" >${error}</p>
                    <p class="warning" style="display: none; color: red;" id="warning_1">The phone number should have 10 digits</p>
                    <p class="warning" style="display: none; color: red;" id="warning_2">Please avoid using any letters or special characters</p>
                </c:if>

                <form name="Register" action="register" method="POST">

                    <div class="form-group">
                         <label for="roleSelect"> Select Role:</label>
                         <select name="role" id="roleSelect" onchange="showFields()">
                             <option value = "" disabled selected>Select a role</option>
                             <option value = "Client">Client</option>
                             <option value = "Technician">Technician</option>
                             <option value = "ServiceAgent">Service Agent</option>
                         </select><br/>            
                    </div>
                    <!-- Client Fields -->
                    <div id="clientFields" style="display:none;">

                        <div class="form-group">
                             <label for="name">Name:</label>
                             <input type="text" name="name" value="" />
                        </div>

                        <div class="form-group">
                            <label for="surname">Surname:</label>
                            <input type="text" name="surname" value="" />
                        </div>

                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" name="email" value="" />
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone Number:</label>
                            <input type="text" id="phone_C" name="phone" value="" placeholder="Enter number, i.e.: 0123456789" maxlength="10"/>
                        </div>

                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" name="password" value=""/>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password:</label>
                            <input type="password" name="confirmPassword" value="" />
                        </div>
                    </div>

                    <!-- Technician Fields -->
                    <div id="technicianFields" style="display:none;"> 

                        <div class="form-group">
                            <label for="name_Technician">Name:</label>
                            <input type="text" name="name_Technician" value="" />
                        </div>

                        <div class="form-group">
                            <label for="surname_Technician">Surname:</label>
                            <input type="text" name="surname_Technician" value="" />
                        </div>

                        <div class="form-group">
                            <label for="email_Technician">Email:</label>
                            <input type="text" name="email_Technician" value="" />
                        </div>

                        <div class="form-group">
                            <label for="phone_Technician">Phone Number:</label>
                            <input type="text" id="phone_T" name="phone_Technician" value="" placeholder="Enter number, i.e.: 0123456789" maxlength="10"/>
                        </div>

                        <div class="form-group">
                            <label for="specialization_Technician">Specialization:</label>
                            <select name="specialization_Technician" id="specialization">
                                <option value="0" disabled selected>Select a Specialisation</option>
                                <%
                                    TechServiceLogic ts = new TechServiceLogic();

                                    ArrayList<String[]> serviceTypes = ts.getServiceTypes();

                                    if(serviceTypes != null)
                                    {
                                        for(var item : serviceTypes)
                                        {
                                            out.println("<option value=\"" + item[0] + "\"dis >" + item[1] + "</option>");
                                        }

                                    }

                                %>
                        </select>
                        </div>

                         <div class="form-group">
                            <label for="password_Technician">Password:</label>
                            <input type="password" name="password_Technician" value="" />
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword_Technician">Confirm Password:</label>
                            <input type="password" name="confirmPassword_Technician" value="" />
                        </div>
                    </div>

                    <!-- Service Agent Fields -->
                    <div id="serviceAgentFields" style="display:none;">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" name="name_ServiceAgent" value="" />
                        </div>


                        <div class="form-group">
                            <label for="surname">Surname:</label>
                            <input type="text" name="surname_ServiceAgent" value="" />
                        </div>


                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" name="email_ServiceAgent" value="" />
                        </div>


                        <div class="form-group">
                            <label for="phone" >Phone Number:</label>
                            <input type="text" name="phone_ServiceAgent" id="phone_CSA" value="" placeholder="Enter number, i.e.: 0123456789" maxlength="10"/>
                        </div>


                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" name="password_ServiceAgent" value="" />
                        </div>


                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password:</label>
                            <input type="password" name="confirmPassword_ServiceAgent" value="" />
                        </div>
                    </div>

                    <div id="address" style="display:none;">

                        <h2 id="address-title">Your Address:</h2>    

                         <div class="form-group">
                            <label for="drop_country">Select a Country:</label>
                            <select name="drop_country" title="Select a Country">
                                <option value="None" disabled selected>Select a Country</option>
                                <option value="South Africa">South Africa</option>
                                <option value="Missing">My country is not listed</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="drop_state">State:</label>
                            <select name="drop_state" title="Select a State">
                                <option value="None" disabled selected>Select a State</option>
                                <option value="Gauteng">Gauteng</option>
                                <option value="Missing">My state is not listed</option>
                            </select>
                        </div>

                        <div class="form-group"> 
                            <label for="drop_city">City:</label>
                            <select name="drop_city" title="Select a City">
                                <option value="None" disabled selected>Select a City</option>
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
                        </div>

                        <div class="form-group">
                            <label for="txt_StreetName">Enter street name:</label>
                            <input type="text" name="txt_StreetName" size="15" placeholder="Enter street name..."/>
                        </div>
                    </div>

                    <div style="display: flex; justify-content: center; margin-top: 20px;">
                        <input type="submit" value="Register" name="Register"  disabled style="opacity: 0.5" id="submit-btn"/>
                    </div>

                </form>

                <script>

                    const error1 = document.getElementById('warning_1');
                    const error2 = document.getElementById('warning_2');

                    function checkPhone(number, e)
                    {
                        number = number.replace(/\s/g, "");


                           if(number.length === 10)
                           {
                               if((/^\d+$/.test(number)))
                               {
                                   error1.style.display = 'none';
                                   error2.style.display = 'none';
                                   let front = number.substring(0, 3);
                                   let middle = number.substring(3, 6);
                                   let end = number.substring(6, 10);

                                   e.target.value = "(" + front + ") " + middle + "-" + end;
                               }
                               else
                               {
                                   error1.style.display = 'none';
                                   error2.style.display = 'block';
                                   e.target.value = "";
                               }

                           }
                           else
                           {
                               error1.style.display = 'block';
                               error2.style.display = 'none';
                               e.target.value = "";

                           }
                    }


                    document.getElementById('phone_C').addEventListener('change', function (e){

                            checkPhone(e.target.value, e);
                        });
                    document.getElementById('phone_T').addEventListener('change', function (e){

                            checkPhone(e.target.value, e);
                        });
                    document.getElementById('phone_CSA').addEventListener('change', function (e){

                            checkPhone(e.target.value, e);
                        });

                </script> 
            </div>
        </section>
    </body>
</html>
