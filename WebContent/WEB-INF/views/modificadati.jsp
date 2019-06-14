<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 class="center"> Sono in modifica Dati</h1>

<div class="container">

<div class="row">
      <form class="col s12" action="confermamodificadati" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input id="first_name" type="text" name="firstName" value ="${user.firstName}" required>
            <label for="first_name" class="pink-text"><b>Nome</b></label>
          </div>
          <div class="input-field col s6">
            <input id="last_name" type="text" name="lastName" value ="${user.lastName}" required>
            <label for="last_name" class="pink-text"><b>Cognome</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="email" type="email" class="validate" name="personalEmail" value ="${user.personalEmail}" >
            <label for="email">Email personale</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="wemail" type="email" class="validate" name="workEmail" value ="${user.workEmail}" >
            <label for="wemail">Email di lavoro</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="phone" type="text" name="phone" value ="${user.phone}" >
            <label for="phone">Telefono</label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="cod_fisc" type="text" name="fiscalCode" minlength="16" maxlength="16" value ="${user.fiscalCode}" required>
            <label for="cod_fisc" class="pink-text"><b>Codice fiscale</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="password" type="password" name="password" value ="${user.password}" required>
            <label for="password" class="pink-text"><b>Password</b></label>
          </div>
        </div>
        
                <div class="row">
          <div class="input-field col s12">
            <input id="password" type="text" name="id" value ="${user.id}" required>
            <label for="id" class="pink-text"><b>ID User</b></label>
          </div>
        </div>
        
        
        <div class="center">
        <input class="btn-large blue" type="submit" value="INVIA MODIFICHE">
		</div>
		
      </form>
    </div>
    
    
</div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>