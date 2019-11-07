<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="it.beije.erp.entity.Address"%>
<%@ page import="java.util.List" %>

<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Address for User</title>
</head>
<body>
<h1>INDIRIZZI PER UTENTE</h1>
<ol>
	<c:forEach var="a" items="${addresses}">
		
		<li>${a}</li>
			
	</c:forEach>
</ol>

</body>
</html>