package it.beije.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DButils {
	
	private static final String DB_USER = "privategriffe";
	private static final String DB_PASSWORD = "privategriffe";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gestionale?serverTimezone=CET";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
		return conn;
	}

	public static List<String> getRubrica() {
		List<String> rows = new ArrayList<String>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset= null;

		try {
			conn = DButils.getConnection();
			
			stmt = conn.createStatement();

			//SELECT
			String query = "SELECT * from utenti";
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				StringBuilder builder = new StringBuilder();
				int id = rset.getInt("id");
				String nome = rset.getString("nome");
				String cognome = rset.getString("cognome");
				String email = rset.getString(4);

				//System.out.println("" + id + ", " + nome + ", " + cognome + ", " +  email);
				builder.append(id).append(';')
				.append(cognome).append(';')
				.append(nome).append(';')
				.append(email);
				
				rows.add(builder.toString());
			}

		}
		catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
//				rset.close();
				stmt.close();
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return rows;
	}

}