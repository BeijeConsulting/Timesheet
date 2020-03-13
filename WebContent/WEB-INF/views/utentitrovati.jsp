<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List"
	import="it.beije.mgmt.entity.User" %>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	
<meta charset="ISO-8859-1">
<title>Ricerca Utente</title>
</head>
<body>

<%
	List<User> users=(List<User>)request.getSession().getAttribute("users");
%>

<h1 class="center teal accent-3 white-text"> <b>Utenti trovati</b></h1>

<div class="container">


<div class="center">
<!-- <a class="btn-floating btn-large pulse"><i class="material-icons">cloud</i></a> -->
		<form action="homepage" method="get">
				<input class="btn green" type="submit" value="Home">
			</form>
</div>

<div class="center">
	<form action="visualizzautente" method="post" id="form">
<%
	for (User u:users){
%>
	<button type="submit" name="userid" form="form" value="<%=u.getId() %>"><%=u.getFirstName()+" "+
	u.getLastName()+" "+u.getEmail()%></button>
<%
	}
%>
	</form>
</div>
<br><br><br>
</div>
</body>
</html>