<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</p>

	<form action="user" method="post">
	
	id_user: <br>
	<input type="text" name="id_user"><br>
	Password: <br><input type="password" name="password"><br>
	Tipo di dato:<br>
	<input type="radio" name="type" value="w" checked> Giornata lavorativa<br>
	<input type="radio" name="type" value="p"> Permeso <br>
	<input type="radio" name="type" value="h"> Ferie<br>
	<input type="radio" name="type" value="s"> Malattia<br>
	Data (yyyy-MM-dd): <br>
	<input type="date" name="date"><br>
       
	Orario di inzio: <br>
	<input type="time"  name="start1"><br>
	Orario di fine: <br>
	<input type="time" name="end1"><br>
	Secondo orario di inizio: <br>
	<input type="time" name="start2"><br>
	Secondo orario di fne: <br>
	<input type="time" name="end2"><br>
	<input type="submit" value="Inserisci">
	<%
	
	
	%>
	
	</form>

</body>
</html>
