<%@page import="it.beije.timesheet.entities.methods.TimeSheetMethods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
</head>
<body>

<h3>La data da te inserita è ${timetable.getId_user()}</h3>
<h3>La data da te inserita è ${timetable.getDate()}</h3>
<h3>L'orario di inzizio inserito è ${timetable.getStart1()}</h3>
<h3>L'orario di fine inserito è ${timetable.getEnd1()}</h3>
<h3>Il secondo orario di inizio da te inserito è ${timetable.getStart2()}</h3>
<h3>Il secondo orario di fine da te inserito è ${timetable.getEnd2()}</h3>


<h3>il totale di ore è xy ${timetable.getTot()}</h3>

<form action="" method="post">
Vuoi confermare i valori inseriti?
<input type="submit" value="Conferma">
</form>

<form action="modifica" method="post">
<input type="submit" value="Modifica">
</form>

</body>
</html>
