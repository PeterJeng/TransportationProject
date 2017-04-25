<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Page</title>
</head>
<body>

<form action = "QueryPageController" method = "POST">
	Query: <input type = "text" name = "queryText">
	<br>
	<input type = "submit" value = "Submit Query">
	<br>
	Query Results:
	<br>
	</form>
       <table border="1">
           <!-- column headers -->
           <tr>
               <c:forEach var="columnName" items="${result.columnNames}">
                   <th><c:out value="${columnName}"/></th>
               </c:forEach>
           </tr>
           <!-- column data -->
           <c:forEach var="row" items="${result.rowsByIndex}">
               <tr>
                   <c:forEach var="column" items="${row}">
                       <td><c:out value="${column}"/></td>
                   </c:forEach>
               </tr>
           </c:forEach>
       </table>


</body>
</html>