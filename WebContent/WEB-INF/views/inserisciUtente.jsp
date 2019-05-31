<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>
<head>

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Inserimento dati</title>
</head>

<body>
  <div class="container">
   
    <h2 class="center"> <i class="material-icons prefix">mode_edit</i> Inserisci i tuoi dati</h2> <br>

    <div class="row">
      <form class="col s12" action="confermaDati" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input id="first_name" type="text" name="firstName" required>
            <label for="first_name" class="pink-text"><b>Inserisci nome</b></label>
          </div>
          <div class="input-field col s6">
            <input id="last_name" type="text" name="lastName" required>
            <label for="last_name" class="pink-text"><b>Inserisci cognome</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="email" type="email" class="validate" name="personalEmail">
            <label for="email">Inserisci email</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="wemail" type="email" class="validate" name="workEmail">
            <label for="wemail">Inserisci email di lavoro</label>
          </div>
        </div>
        
        
        <div class="row">
          <div class="input-field col s12">
            <input id="phone" type="text" name="phone">
            <label for="phone">Inserisci numero telefono</label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="cod_fisc" type="text" name="fiscalCode" required>
            <label for="cod_fisc" class="pink-text"><b>Inserisci codice fiscale</b></label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="password" type="password" name="password" required>
            <label for="password" class="pink-text"><b>Inserisci password</b></label>
          </div>
        </div>
        
        <input class="btn blue" type="submit" value="INVIO">

      </form>
    </div>

  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


</body>
</html>