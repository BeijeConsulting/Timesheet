<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifica Dati</title>
</head>
<body>

	<form action="user" method="post">
		id_user: <br> <input type="text" name="id_user"
			value=${timetable.id_user}><br> Password: <br>
		<input type="password" name="password"><br> Tipo di dato:<br>
		<input type="radio" name="type" value="g" checked> Giornata
		lavorativa<br> <input type="radio" name="type" value="p">
		Permeso <br> <input type="radio" name="type" value="f">
		Ferie<br> <input type="radio" name="type" value="m">
		Malattia<br> Data (yyyy/MM/dd): <br> <input type="date"
			name="date" value=${timetable.getDate()}> <br> Orario di
		inzio: <br> <input type="time" name="start1" maxlength="5"
			value=${timetable.getStart1()}><br> Orario di fine: <br>
		<input type="time" name="end1" value=${timetable.getEnd1()}><br>
		Secondo orario di inizio: <br> <input type="time" name="start2"
			value=${timetable.getStart2()}><br> Secondo orario di
		fne: <br> <input type="time" name="end2"
			value=${timetable.getEnd2()}><br> <input type="submit"
			value="Inserisci">
	</form>

</body>
</html>