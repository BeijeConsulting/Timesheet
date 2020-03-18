package it.beije.mgmt.tool.importuser;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 


public class DatabaseConnection { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		
		String dbURL = "jdbc:mysql://localhost:3306/timesheet?serverTimezone=CET"; 		
		String dbUsername = "root"; 
		String dbPassword = "Beije06"; 

		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
		return con; 
	} 
} 
