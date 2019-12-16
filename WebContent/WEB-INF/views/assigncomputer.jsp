<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Assegna computer</title>
</head>

<body>
  <div class="container">
   
    <h2 class="center"> <i class="medium material-icons prefix">mode_edit</i> Assegna pc</h2> <br>

    <div class="row">
      <form class="col s12" action="assigncomputer" method="post">
      <div class="row">
    	<div class="input-field s6">      
    	<select class="browser-default waves-effect waves-light btn" name="idComputer">
      		<option value="">Scegli il computer</option>
          <c:forEach items="${computers}" var="temp">
          <option value="${temp.id}" >ID: ${temp.id} MARCA: ${temp.brand} MODELLO: ${temp.model}
          							 CPU: ${temp.cpu} RAM: ${temp.ram} HDD: ${temp.hardDisk} S/N: ${temp.serialNumber}</option>
          </c:forEach>
       </select>
    </div>
    </div>
          
          <div class="row">
          <div class="input-field col s12">
            <input id="start_date" type="date" name="startDate" required>
            <label for="start_date" class="pink-text"><b>Data consegna:</b></label>
          </div>
		</div>
		
        <div class="row">
          <div class="input-field col s12">
            <input id="end_date" type="date" name="endDate">
            <label for="end_date"><b>Data restituzione:</b></label>
          </div>
          </div>
        
        <div class="row">
          <div class="input-field col s6">
            <input id="id_user" type="number" name="idUser" required>
            <label for="id_user" class="pink-text"><b>ID Utente: </b></label>
          </div>
          
          <div class="input-field col s6">
            <input id="note" type="text" name="note" value="">
            <label for="note" ><b>Note:</b></label>
          </div>
          </div>

		<div class="row">
          <div class="input-field col s6">
            <input id="mouse" type="text" name="mouse" value="">
            <label for="mouse"><b>Mouse:</b></label>
          </div>
        
          <div class="input-field col s6">
            <label>
        	<input type="checkbox" name="lanAdapter" value="1" />
        	<span><b>Adattatore LAN: </b></span>
      	</label>
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