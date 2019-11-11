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
          <div class="input-field col s6">
            <input id="email" type="email" class="validate" name="email" value ="${user.email}" >
            <label for="email">Email</label>
        </div>
        
          <div class="input-field col s6">
            <input id="secondary_email" type="email" class="validate" name="secondaryEmail" value ="${user.secondaryEmail}" >
            <label for="secondary_email">Email secondaria</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="phone" type="text" name="phone" value ="${user.phone}" >
            <label for="phone">Telefono</label>
          </div>
          
          

          <div class="input-field col s6">
            <input id="fiscal_code" type="text" name="fiscalCode" minlength="16" maxlength="16" value ="${user.fiscalCode}" required>
            <label for="fiscal_code" class="pink-text"><b>Codice fiscale</b></label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="birth_date" type="date" name="birthDate" value="${user.birthDate }">
            <label for="birth_date">Inserisci data di nascita</label>
        </div>
        
          <div class="input-field col s6">
            <input id="birth_place" type="text" name="birthPlace" value="${user.birthPlace }">
            <label for="birth_place">Inserisci luogo di nascita</label>
          </div>
        </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="nationality" type="text" name="nationality" value="${user.nationality }">
            <label for="nationality">Nazionalità</label>
        </div>
        
          <div class="input-field col s6">
            <input id="document" type="text" name="document" value="${user.document }">
            <label for="document">Documento</label>
          </div>
		</div>
		
		<div class="row">
          <div class="input-field col s6">
            <input id="id_skype" type="text" name="idSkype" value="${user.idSkype }">
            <label for="id_skype">ID Skype</label>
          </div>
        
          <div class="input-field col s6">
            <input id="note" type="text" name="note" value="${user.note }">
            <label for="note">Note</label>
          </div>
        </div>

        <div class="row">
          <div class="input-field col s6">
            <input id="password" type="password" name="password" value ="${user.password}" required>
            <label for="password" class="pink-text"><b>Password</b></label>
        </div>
        </div>
            <input type="hidden" name="id" value ="${user.id}" required>
           
        
        
        <div class="center">
        <input class="btn-large blue" type="submit" value="INVIA MODIFICHE">
		</div>
		
      </form>
    </div>
    
    
</div>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>