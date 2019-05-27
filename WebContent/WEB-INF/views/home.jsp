<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
 
 
<title>Home</title>
</head>
<body>
	<h1 class="center" >Benvenuto Utente</h1>

<div class="container">
	<P>Orario: ${serverTime}.</p>


    <div class="row">
      <div class="col s3">
	<form action="inserisciUtente" method="post">
        <input class="btn green" type="submit" value="Inserisci utente">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="modificaUtente" method="post">
        <input class="btn green" type="submit" value="modifica utente">
	</form>
	</div>
	
	  <div class="col s3">
		<form action="cancellaUtente" method="post">
        <input class="btn green" type="submit" value="cancella utente">
	</form>
	</div>
	
	  <div class="col s3">
			<form action="cercaUtente" method="post">
        <input class="btn green" type="submit" value="cerca utente">
	</form>
	</div>
	</div>
	
	
	</div>
</body>
</html>
