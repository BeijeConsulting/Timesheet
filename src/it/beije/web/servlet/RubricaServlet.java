package it.beije.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.beije.utils.CSVutils;
import it.beije.utils.DButils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Servlet implementation class RubricaDB
 */
@WebServlet("/rubrica")
public class RubricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//////////////////////////////
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Beije05";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/beije?serverTimezone=CET";
	/////////////////////////////

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> rows = null;
		String tipo = request.getParameter("tipo");

		if (tipo != null) {
			if (tipo.equalsIgnoreCase("csv")) {
				rows = CSVutils.getFileAsStrings("D:\\rubrica.txt");
			} else if (tipo.equalsIgnoreCase("db")) {
				rows = DButils.getRubrica();
			}
		}

		response.setContentType("text/html");

		if (rows != null) {
			for (String r : rows) {
				response.getWriter().append(r).append("<br>");
			}
		} else {
			response.getWriter().append("SPECIFICARE IL TIPO");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//prendo dati da jsp
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String fiscalcode = request.getParameter("fiscalcode");
		String password = request.getParameter("password");

		//		System.out.println("firstname : " + firstname);
		//		System.out.println("lastname : " + lastname);
		//		System.out.println("phone : " + phone);
		System.out.println("Dati inseriti: " + firstname +", " + lastname +", " 
				+ email +", "  + phone +", "  + fiscalcode +", " + password);

		StringBuilder row = new StringBuilder();
		row.append(firstname).append(';');
		row.append(lastname).append(';');
		row.append(email).append(';');
		row.append(phone).append(';');
		row.append(fiscalcode).append(';');
		row.append(password);

		CSVutils.appendRowsInFile("D:\\rubrica.txt", row.toString());
		
		inserisciUtente(firstname, lastname, email, phone, fiscalcode, password);


//				response.setContentType("text/html");
//				response.getWriter()
//				.append("Dati inseriti" + "<br>" )
//				.append("firstname : " + firstname + "<br>")
//				.append("lastname : " + lastname + "<br>")
//				.append("email : " + email + "<br>")
//				.append("Dati salvati");

		request.setAttribute("firstname", firstname);
//		response.sendRedirect("process.jsp");
//		response.sendRedirect("landing.jsp");
		request.getRequestDispatcher("process.jsp").forward(request, response);
	}

	//////////////////////////////////////////////////////////////////////////////////
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		return conn;
	}

	public static void inserisciUtente(String...info) {

		Connection conn = null;
		Statement stmt = null;
//		ResultSet rset= null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();


			String query = "INSERT INTO user (`first_name`, `last_name`, `personal_email`, `phone`, `fiscal_code`, `admin`,`password`) \r\n" + 
					"VALUES (' " + info[0]+ "','" + info[1] + "', '" +info[2]+ "','" + info[3] + "', '" +info[4] + 
					"', " + 0  + ",'" + info[5] + "');";
			
			stmt.execute(query);
			

		}catch (SQLException se) {
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

	}




}
