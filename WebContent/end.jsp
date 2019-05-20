<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fine</title>
</head>
<body>
<b>HO SALVATO I DATI IN RUBRICA</b>

<jsp:useBean id="user" class="it.beije.web.bean.User" scope="session"></jsp:useBean>

<br><br>
Nome: <jsp:getProperty property="firstName" name="user"/><br>  
Cognome: <jsp:getProperty property="lastName" name="user"/><br> 
<jsp:getProperty property="phone" name="user"/><br> 

</body>
</html>