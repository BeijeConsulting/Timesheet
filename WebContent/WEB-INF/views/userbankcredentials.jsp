<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="it.beije.erp.entity.BankCredentials"%>
<%@ page import="java.util.List" %>

<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of User's Bank Credentials</title>
</head>
<body>
<h1>CREDENZIALI BANCARIE PER UTENTE</h1>
<ol>

<%

	List<BankCredentials> bankcredentials = (List<BankCredentials>)request.getAttribute("bankcredentials");
	for(BankCredentials bc : bankcredentials)
		out.println(bc);

%>

<!--  
	<c:forEach var="bc" items="${bank_credentials}">
		
		<li>${bc}</li>
			
	</c:forEach>
	-->
</ol>

</body>
</html>