<%@page import="it.beije.web.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina di conferma</title>
</head>
<body>

<jsp:useBean id="user" class="it.beije.web.bean.User" scope="session"></jsp:useBean>


  
Dati inseriti:<br><br>
<%-- GET 
Nome: <jsp:getProperty property="firstName" name="user"/><br>--%>
Cognome: <jsp:getProperty property="lastName" name="user"/><br> 
Nome1: <% System.out.println("ciao"); user.setFirstName(request.getParameter("firstname")); %>
Nome2: <%= request.getParameter("firstname") %>
<% System.out.println("ciao"); %>

<%String name = (String)request.getAttribute("firstname"); %>
<%= name%>

<br>

 <form action="index.jsp" method="post">
    <input type="submit" value="Modifica">
</form>

<br>
 <form action="rubrica" method="post">
 
 
    <input type="submit" value="Conferma">
</form>


</body>
</html>