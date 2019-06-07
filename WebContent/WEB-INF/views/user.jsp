<%@page import="it.beije.erp.timesheet.service.TimetableService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 15px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
</head>
<body>

	<%-- <h3>Il tuo id è ${timetable.id_user}</h3>
<h3>La data da te inserita è ${timetable.date}</h3>
<h3>L'orario di inzizio inserito è ${timetable.start1}</h3>
<h3>L'orario di fine inserito è ${timetable.start2}</h3>
<h3>Il secondo orario di inizio da te inserito è ${timetable.end1}</h3>
<h3>Il secondo orario di fine da te inserito è ${timetable.end2}</h3>
<h3>Il totale delle ore è ${timetable.tot}</h3>


<h3>il totale di ore è xy ${timetable.getTot()}</h3> --%>

	<h1 align="center">Dati relativi agli utenti
		${timetable.id_user }</h1>

	<div style="overflow-x: auto;">
		<table>
			<tr>
				<th>ID utente</th>
				<th>Data</th>
				<th>Ora inizio</th>
				<th>Ora fine</th>
				<th>Ora inizio</th>
				<th>Ora fine</th>
				<th>Totale ore</th>
			</tr>
			<tr>
				<td>${timetable.id_user}</td>
				<td>${timetable.date}</td>
				<td>${timetable.start1}</td>
				<td>${timetable.end1}</td>
				<td>${timetable.start2}</td>
				<td>${timetable.end2}</td>
				<td>${timetable.tot}</td>
			</tr>
		</table>
	</div>


	<h3>Salva o modifica i dati:</h3>
	<table>
		<tr>
			<td>
				<form action="pagineDopoConferma" method="post">
					<input type="submit" value="Salva">
				</form>
			</td>
			<td>
				<form action="modifica" method="post">
					<input type="submit" value="Modifica">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>
