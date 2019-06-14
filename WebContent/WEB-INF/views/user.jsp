<%@page import="it.beije.erp.timesheet.service.TimetableService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 15px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
</head>
<body>

	<%-- <h3>Il tuo id è ${timetable.id_user}</h3>
<h3>La data da te inserita è ${timetable.date}</h3>
<h3>L'orario di inzizio inserito è ${timetable.start1}</h3>
<h3>L'orario di fine inserito è ${timetable.start2}</h3>
<h3>Il secondo orario di inizio da te inserito è ${timetable.end1}</h3>
<h3>Il secondo orario di fine da te inserito è ${timetable.end2}</h3>
<h3>Il totale delle ore è ${timetable.tot}</h3>


<h3>il totale di ore è xy ${timetable.getTot()}</h3> --%>

	<h1 align="center">Dati relativi agli utenti
		${timetable.idUser }</h1>

<div class="container">

<div class="row">
      <form class="col s12" action="confermadatitimetable" method="post">
        <div class="row">
          
          <div class="input-field col s6">
            <input id="id_utente" type="text" name="idUser" value ="${timetable.idUser}" required>
            <label for="id_utente" class="pink-text"><b>Id Utente</b></label>
          </div>
          <div class="input-field col s6">
            <input id="date" type="date" name="date" value ="${timetable.date}" required>
            <label for="date" class="pink-text"><b>Date</b></label>
          </div>
        </div>
        
<label class="w3-text-grey">Tipo:</label> <br> 
			<select class="w3-text-grey" name="type">
				<option value="g">Giornata lavorativa</option>
				<option value="p">Permesso</option>
				<option value="f">Ferie</option>
				<option value="m">Malattia</option>
				<option value="h">Festività</option>
			</select>
			
	<div class="row">
          <div class="input-field col s12">
            <input id="start1" type="time" class="validate" name="start1" value ="${timetable.start1}" >
            <label for="start1">Orario inizio:</label>
          </div>
        </div>
         <div class="input-field col s12">
            <input id="end1" type="time" class="validate" name="end1" value ="${timetable.end1}" >
            <label for="end1">Orario fine:</label>
          </div>
        </div>
        
        
        <div class="row">
          <div class="input-field col s12">
            <input id="start2" type="time" class="validate" name="start2" value ="${timetable.start2}" >
            <label for="start2">Orario inizio:</label>
          </div>
        </div>
         <div class="input-field col s12">
            <input id="end2" type="time" class="validate" name="end2" value ="${timetable.end2 }" >
            <label for="end2">Orario fine:</label>
          </div>
        </div>
        <div class="input-field col s12">
            <input id="tot" type="time" class="validate" name="tot" value ="${timetable.tot}" >
            <label for="tot">Totale:</label>
          </div>
        <input type="submit" value="conferma">
        
      </form>
    </div>
    
    
</div>

			<td>
				<!-- <form action="user" method="post">
				</form> -->
			</td>
		</tr>
	</table>

</body>
</html>
