<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
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
</head>
<body>

	<p></p>

	<h1 align="center">Informazioni utente "${utente.first_name}"
		dalla data: ${data}</h1>

	<div style="overflow-x: auto;">

		<table>
			<tr>
				<th>ID utente</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Email</th>
				<th>Email Lavoro</th>
				<th>Cell</th>
				<th>Codice Fiscale</th>
			</tr>
			<tr>
				<td>${utente.id}</td>
				<td>${utente.first_name}</td>
				<td>${utente.last_name}</td>
				<td>${utente.personal_email}</td>
				<td>${utente.work_email}</td>
				<td>${utente.phone}</td>
				<td>${utente.fiscal_code}</td>
			</tr>
		</table>
	</div>

	<div>
	<br><br><br>
		<table>
			<tr>
				<th>Data</th>
				<th>Tipo</th>
				<th>Inizio1</th>
				<th>Fine1</th>
				<th>Inizio2</th>
				<th>Fine2</th>
				<th>Totale ore</th>
			</tr>
			<c:forEach items="${recordsUser}" var="utente">
			<tr>
				<td>${utente.date}</td>
				<td>${utente.type}</td>
				<td>${utente.start1}</td>
				<td>${utente.end1}</td>
				<td>${utente.start2}</td>
				<td>${utente.end2}</td>
				<td>${utente.tot}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>