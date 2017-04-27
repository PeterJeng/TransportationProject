<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Staff Account</title> <%-- Title names the tab--%>
</head>
<body>
Create Staff Account <%-- Actual title user see on the webpage --%>
<br> <%-- New line, essentially a /n --%>


<%-- Action needs to link to a jsp page that pulls information from database and check if the information is valid--%>
<form action = "CreateStaffAccountController" method = "POST"> 
	First Name*: <input type = "text" name = "fname">
	<br>
	Last Name*: <input type = "text" name = "lname">
	<br>
	Username*: <input type = "text" name = "username">
	<br>
	Password*: <input type = "password" name = "password">
	<br>
	Confirm Password*: <input type = "password" name = "confirmPassword">
	<br>
	RUID*: <input type = "text" name = "ruid">
	<br>
	Address: <input type = "text" name = "address">
	<br>
	Email: <input type = "text" name = "email">
	<br>
	<br>
	
	
<input type = "submit" value = "Create Staff Account">	
<br>
<a href="AdministratorPage.jsp">Return to Admin Main Page</a><br>
</form>


<br>
* Indicates required field

</body>
</html>