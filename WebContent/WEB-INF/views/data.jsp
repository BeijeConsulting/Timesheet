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

	<h1 align="center">Informazioni utenti per la data: ${stampa}</h1>

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

			<c:forEach items="${listaUtenti}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.first_name}</td>
					<td>${u.last_name}</td>
					<td>${u.personal_email}</td>
					<td>${u.work_email}</td>
					<td>${u.phone}</td>
					<td>${u.fiscal_code}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>