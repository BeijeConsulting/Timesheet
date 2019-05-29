<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

	<title>Pagina di conferma</title>
</head>
<body>

	<div class="container">
		<h3 class="center">Dati inseriti </h3>      
		
		<div class="row">
			<form class="col s12" action="conferma" method="post">
				
				<div class="row">
					
					<div class="input-field col s6">
						Nome:<input type="text" name="firstName" value ="${user.firstName}">
					</div>
					<div class="input-field col s6">
						Cognome:<input type="text" name="lastName" value ="${user.lastName}">
					</div>
				</div>
				
				<div class="row">
					<div class="input-field col s6">
						Email di privato:<input type="text" name="personalEmail" value ="${user.personalEmail}">
					</div>
					<div class="input-field col s6">
						Email di lavoro:<input type="text" name="workEmail" value ="${user.workEmail}">
					</div>
				</div>	
				
				<div class="row">
					
					<div class="input-field col s6">
						Telefono:<input type="text" name="phone" value ="${user.phone}">
					</div>
					<div class="input-field col s6">
						Codice Fiscale:<input type="text" name="fiscalCode" value ="${user.fiscalCode}">
					</div>
					
				</div>
				
				<div class="row">
					<div class="input-field col s6">
						Password: <input type="password" name="password" value ="${user.password}">
					</div>
				</div>
				
				
				<input class="btn blue" type="submit" value="Conferma">
			</form>
		</div>
		

<!-- 	<div class="row">	
		<div class="input-field col s6 center">
			<form action="inserisciUtente" method="post">
				<input class="btn blue" type="submit" value="Annulla">
			</form>
		</div>
		
		<div class="input-field col s6 center">
			<form action="conferma" method="post">
				<input class="btn blue" type="submit" value="Conferma">
			</form>
		</div>
	</div> -->
	
	
</div>
</body>
</html>
