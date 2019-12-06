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
			<form class="col s12" action="registercomputer" method="post">
				
				<div class="row">
					
					<div class="input-field col s6">
						Marca:<input type="text" name="brand" value ="${computer.brand}" readonly>
					</div>
					
					<div class="input-field col s6">
						Modello:<input type="text" name="model" value ="${computer.model}" readonly>
					</div>
				</div>
				
				
				
				<div class="row">
					<div class="input-field col s6">
						CPU:<input type="text" name="cpu" value ="${computer.cpu}" readonly>
					</div>
					<div class="input-field col s6">
						Ram:<input type="number" name="ram" value ="${computer.ram}" readonly>
					</div>
				</div>		
					
				
				
				
		 <div class="row">
			<div class="input-field col s6">
		        Hard-Disk:<input type="text" name="hardDisk" value ="${computer.hardDisk}" readonly>
			  </div>
			  <div class="input-field col s6">
			    S/N: <input type="text" name="serialNumber" value ="${computer.serialNumber}" readonly>
			</div>
					
		  </div>
				
				
		  <div class="row">
			    <div class="input-field col s6">
					Sistema Operativo:<input type="text" name="operatingSystem" value ="${computer.operatingSystem}" readonly>
				</div>
				
				<div class="input-field col s6">
					Data acquisto<input type="text" name="purchaseDate" value ="${computer.purchaseDate}" readonly>
				</div>
		  </div>
				
				<div class="row">
				<div class="input-field col s12">
					Note:<input type="text" name="note" value ="${computer.note}" readonly>
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