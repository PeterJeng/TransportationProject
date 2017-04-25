<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title> <%-- Title names the tab--%>
</head>
<body>
Please enter your username and password: <%-- Actual title user see on the webpage --%>
<br> <%-- New line, essentially a /n --%>


<%-- Action needs to link to a jsp page that pulls information from database and check if the information is valid--%>
<form method = "post" action = "LoginAccountController"> 
	Username: <input type = "text" name = "username">
	<br>
	Password: <input type = "password" name = "password">
	<br>
	
<input type = "submit" value = "Log In">	
</form>

<br>

</body>
</html>