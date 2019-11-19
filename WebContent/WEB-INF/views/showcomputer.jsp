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

	<h1 align="center">COMPUTER</h1>

	<div style="overflow-x: auto;">

		<table>
			<tr>
				<th>ID Computer</th>
				<th>Marca</th>
				<th>Modello</th>
				<th>CPU</th>
				<th>Ram</th>
				<th>Hard-Disk</th>
				<th>Numero Seriale</th>
				<th>Sistema Operativo</th>
				<th>Disponibilita'</th>
				<th>Data d'acquisto</th>
				<th>note</th>
				
			</tr>
				<c:forEach items="${computers}" var="temp">
				<tr>
    			<td>${temp.id}</td>
    			<td>${temp.brand}</td>
    			<td>${temp.model}</td>
   			 	<td>${temp.cpu}</td>
    			<td>${temp.ram}</td>
    			<td>${temp.hardDisk}</td>
    			<td>${temp.serialNumber}</td>
    			<td>${temp.operatingSystem}</td>
    			<td>${temp.availability}</td>
   			 	<td>${temp.purchaseDate}</td>
    			<td>${temp.note}</td>
    			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>