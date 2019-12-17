<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	<title>Cerca computer</title>
</head>
<body>

	<script>
	function ShowHideDiv() {
        var chkYes = document.getElementById("chkYes");
        var dvPassport = document.getElementById("form");
        var dvPassport2 = document.getElementById("button");
        dvPassport.style.display = chkYes.checked ? "block" : "none";
        dvPassport2.style.display = chkYes.checked ? "none" : "block";
    }
	</script>
	
	
	<div class="row2">

	<div class="container">
		<h3 class="center">Cerca Computer </h3>      
				
			<form action="showcomputer" method="post">
				<div class="row">
				<div class="center">
				<label>
      			<input class="with-gap" id="chkYes" name="check" type="radio" onclick="ShowHideDiv()" value="null" />
      			<span><b>Tutti</span>
    			</label>
				<label>
     			 <input class="with-gap" id="chkNo" name="check" type="radio" onclick="ShowHideDiv()" value="true" />
      			<span><b>Disponibili</span>
    			</label>
    			<label>
     			 <input class="with-gap" id="chkNo" name="check" type="radio" onclick="ShowHideDiv()" value="false" />
      			<span><b>Indisponibili</span>
    			</label>
				</div>
				</div> 
				
				<div class="row">
				<div class="center">
				<center>
				<input class="btn blue" id="button" type="submit" value="Conferma" style="display: none">
				</center>
				</div>
				</div>
			</form>
			
			<form class="col s12" id="form" action="searchcomputer" method="post" style="display: none">
				<div class="row">
					<div class="input-field col s6">
						S/N:<input type="text" name="serialNumber" value="">
					</div>

					<div class="input-field col s6">
						Ram: <input type="number" name="ram" value="0">
					</div>
				</div>	
				
				<div class="row">
					<div class="input-field col s6">
						CPU:<input type="text" name="cpu" value="">
					</div>

					<div class="input-field col s6">
						Hard-Disk: <input type="text" name="hardDisk" value="">
					</div>
				</div>
				
				<div class="row">
				<div class="center">
				<input class="btn blue" type="submit" value="Conferma">
				</div>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>