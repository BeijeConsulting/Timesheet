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
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Inserisci i tuoi dati</h2> <br>

    <div class="row">
      <form class="col s12" action="confirmdata" method="post">
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
          <div class="input-field col s6">
            <input id="email" type="email" class="validate" name="email" required>
            <label for="email" class="pink-text"><b>Inserisci email</b></label>
          </div>
        
          <div class="input-field col s6">
            <input id="secondary_email" type="email" class="validate" name="secondaryEmail" value="">
            <label for="secondary_email">Inserisci email secondaria</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="phone" type="text" name="phone" value="">
            <label for="phone">Inserisci numero di telefono</label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="fiscal_code" type="text" name="fiscalCode" minlength="16" maxlength="16" required>
            <label for="fiscal_code" class="pink-text"><b>Inserisci codice fiscale</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="birth_date" type="date" name="birthDate" value="1900-01-01">
            <label for="birth_date">Inserisci data di nascita</label>
        </div>
        
          <div class="input-field col s6">
            <input id="birth_place" type="text" name="birthPlace" value="">
            <label for="birth_place">Inserisci luogo di nascita</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="nationality" type="text" name="nationality" value="">
            <label for="nationality">Nazionalità</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s12">
            <input id="document" type="text" name="document" value="">
            <label for="document">Documento</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="id_skype" type="text" name="idSkype" value="">
            <label for="id_skype">ID Skype</label>
          </div>
        
          <div class="input-field col s6">
            <input id="note" type="text" name="note" value="">
            <label for="note">Note</label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s12">
            <input id="password" type="password" name="password" required>
            <label for="password" class="pink-text"><b>Inserisci password</b></label>
          </div>
        </div>
        
        <div class="center">
        <input class="btn-large blue" type="submit" value="INVIO">
		</div>
		
      </form>
    </div>

  </div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>