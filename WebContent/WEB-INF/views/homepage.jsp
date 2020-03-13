<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>

<head>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

  <title>Benvenuti</title>

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

</head>

<body>

 <nav>
  <div class="nav-wrapper sfondo">
    <a href="#"><img class="responsive-img beije-logo" src="https://i.postimg.cc/3JbPt5ZZ/logo-beije.png"></a>
  </div>
</nav>

<div class="container">
  <div class="center">

    <h1>Benvenuto Admin</h1>
    <i class="large material-icons prefix">face</i><br><br><br><br>

    <div class="row">
<!-- PARTE USER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
      <div class="col s6">
         <form class="col s12" action="home" method="get">
<!--         <a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">person</i>Gestione Utenti</a> -->
        <input class="btn green" type="submit" value="Gestione Utenti">
      </form>
      </div>

      <div class="col s6">
         <form class="col s12" action="prehome" method="get">
   <!--      <a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">domain</i>Gestione Timetable</a> -->
          <input class="btn green" type="submit" value="Gestione TimeTable">
      </form>
      </div>
      
      <div class="col s6">
         <form class="col s12" action="homecomputer" method="get">
   <!--      <a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">domain</i>Gestione Timetable</a> -->
          <input class="btn green" type="submit" value="Gestione Computer">
      </form>
      </div>

    </div>
  </div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>