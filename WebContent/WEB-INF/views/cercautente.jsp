<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<title>Cerca Utente</title>
</head>
<body>

	<div class="container">
		<h3 class="center">Cerca utente </h3>      
		
		<div class="row">
			<form class="col s12" action="utentitrovati" method="get">
				
				<div class="row">
					<div class="input-field col s6">
						Nome:<input type="text" name="firstName" value="">
					</div>

					<div class="input-field col s6">
						Cognome: <input type="text" name="lastName" value="">
					</div>
				</div>	
				
				<div class="row">
					<div class="input-field col s6">
						Email:<input type="text" name="email" value="">
					</div>

					<div class="input-field col s6">
						Codice Fiscale: <input type="text" name="fiscalCode" value="">
					</div>
				</div>
				
					<div class="row">
				<div class="center">
				<input class="btn blue" type="submit" value="Conferma">
				</div>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>
