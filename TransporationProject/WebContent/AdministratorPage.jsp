<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
Welcome, Admin!
<br>

<form method = "post" action = "AdministratorController"> 

	<input type = "submit" name="action" value = "Create System Support Staff Account">
	<br>
	<input type = "submit" name="action" value = "Run Query On Statistics">
	<br>
	<a href="EditInformation.jsp">Edit Information</a><br>
	<a href="LoginPage.jsp">Return to Log In</a><br>
</form>


</body>
</html>