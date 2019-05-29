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
	<input type="text" name="id_user" value="12345"><br>
	Password: <br><input type="password" name="password" value="ciao"><br>
	Tipo di dato:<br>
	<input type="radio" name="type" value="g" checked > Giornata lavorativa<br>
	<input type="radio" name="type" value="p"> Permeso <br>
	<input type="radio" name="type" value="f"> Ferie<br>
	<input type="radio" name="type" value="m"> Malattia<br>
	Data (yyyy/MM/dd): <br>
	<input type="date" name="date" value="2019-1-1">
    <br>
	Orario di inzio: <br>
	<input type="time" name="start1" maxlength="5" value="09:00"><br>
	Orario di fine: <br>
	<input type="time" name="end1" value="13:00"><br>
	Secondo orario di inizio: <br>
	<input type="time" name="start2" value="14:00"><br>
	Secondo orario di fne: <br>
	<input type="time" name="end2" value="18:00"><br>
	<input type="submit" value="Inserisci">
	<%
	
	
	%>
	
	</form>
</body>





















</html>
