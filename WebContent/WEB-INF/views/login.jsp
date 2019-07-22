
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<html>

<head>

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<title>Login</title>

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

			<h1>Login    <i class="medium material-icons prefix">account_circle</i><br><br></h1>


			<div class="row">



				<div class="row">
					<form class="col s12" action="conferma_dati" method="post">


						<div class="row">
							<div class="input-field col s12">
								<i class="material-icons prefix">mail</i>
								<input id="email" type="email" class="validate" name="personalEmail">
								<label for="email" class="black-text"><b>Inserisci email</b></label>
							</div>
							<div class="input-field col s12">
								<i class="material-icons prefix">vpn_key</i>
								<input id="password" type="password" name="password" required>
								<label for="password" class="black-text"><b>Inserisci password</b></label>

							</div>
</div>
						</form>
					</div>


						<div class="row">
							<form class="col s12 right" action="/timesheet/temporanea" method="get">
							<!-- 	<a class="waves-effect waves-light btn-large sfondo"><i class="material-icons left">person</i>Loggo</a> -->
								<input class="btn green" type="submit" value="LOG">
							</form>

						</div>

					</div>


				</div>
			</div>
		


		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	</body>
	</html>