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

	<h1 align="center">Informazioni utente "${utente.firstName}"</h1>

	<div style="overflow-x: auto;">

		<table>
			<tr>
				<th>ID utente</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Email Lavoro</th>
				<th>Email</th>
				<th>Cell</th>
				<th>Codice Fiscale</th>
				<th>Data di Nascita</th>
				<th>Luogo di Nascita</th>
				<th>Nazionalità</th>
				<th>Numero Documento</th>
				<th>Skype ID</th>
				<th>Utente admin</th>
				<th>Data di archiviazione</th>
				<th>Note</th>
				
			</tr>
			<tr>
				<td>${utente.id}</td>
				<td>${utente.firstName}</td>
				<td>${utente.lastName}</td>
				<td>${utente.email}</td>
				<td>${utente.secondaryEmail}</td>
				<td>${utente.phone}</td>
				<td>${utente.fiscalCode}</td>
				<td>${utente.birthDate}</td>
				<td>${utente.birthPlace}</td>
				<td>${utente.nationality}</td>
				<td>${utente.document}</td>
				<td>${utente.idSkype}
				<td>${utente.admin}</td>
				<td>${utente.archiveDate}</td>
				<td>${utente.note}</td>
			</tr>
		</table>
	</div>

	<%-- <div>
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
	</div>--%>
</body>
</html>