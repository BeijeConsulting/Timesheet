
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
 
<!--   <link rel="stylesheet" type="text/css" href="CSS/MyCSS.css">  -->


  <meta charset="ISO-8859-1">



  <style type="text/css">
    .beije-logo {
      height: 5.5em;
      width: 5.5em;
    }

    .sfondo {
      background-color: #201D1D;
    }
    
    footer {
      position: sticky;
      top: 1600px;
      right: 0;
    }
    
    body{
      display: flex;
      min-height: 100vh;
      flex-direction: column;}
      
      main{
        flex: 1 0 auto;}
        
        input {
          width: 30em;
        }
        
        .barra-menu {
          border-style: solid;
          border-color:#201D1D;
          height: 6em;
          width: 100%;
        }
        
        .material-icons.bianco {
          color: white;
        }
        
        .margine{
          margin-top: 30px;
          
        }
        
        .marginefaccina{
          margin-top: 15px;
          
        }
        
      </style>

      <title>Benvenuto</title>
    </head>
    <body>
    
<!--  <nav>
  <div class="nav-wrapper sfondo">
    <a href="http://localhost:8080/Timesheet/homepage"><img class="responsive-img beije-logo" src="https://i.postimg.cc/3JbPt5ZZ/logo-beije.png"> 
    </a>
    <i class="large material-icons prefix md-light right beije-logo">face</i>
  </div>
</nav> -->

<div class="barra-menu sfondo white-text"> 
	<div class="row">
		<div class="col s10">
      <a href="http://localhost:8080/Timesheet/homepage"><img class="responsive-img beije-logo" src="https://i.postimg.cc/3JbPt5ZZ/logo-beije.png"></a></div>
      <div class="col s1">
        <i class="medium material-icons prefix beije-logo bianco marginefaccina">face</i></div>
        <div class="col s1 margine">Ivo Mosca</div>
      </div>
    </div>
    <h1 align="center">Bentornato! Scegli cosa fare: </h1>

    
    <div align="center">
     <form action="hometimetable" method="post">
      <input class="btn green" type="submit" value="Inserisci">
    </form>
    <br><br><br>
    
    <form action="ricerca" method="post">
      <input class="btn green" type="submit"value="Ricerca/Modifica Utente">
    </form>
  </div>	
  <footer>
   <p align="left">Data: ${serverTime}</p>
 </footer>
</body>
</html>