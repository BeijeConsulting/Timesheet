package it.beije.web.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.beije.utils.CSVutils;
import it.beije.utils.DButils;
import it.beije.utils.HDButils;
import it.beije.web.bean.User;

/**
 * Servlet implementation class RubricaDB
 */
@WebServlet("/rubricaH")
public class RubricaHibernateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		List<String> rows = null;
		
		Session session = HDButils.getSession();
		
		Criteria criteria = session.createCriteria(User.class);
		List users = criteria.list();

		Iterator itr = users.iterator();
		while (itr.hasNext()) {
			User u = (User) itr.next();
			System.out.println(u.getId());
			System.out.println(u.getLastName());
			
			response.getWriter().append(u.getFirstName()).append("<br>");
		}
		
		session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String firstname = request.getParameter("firstname");
//		String lastname = request.getParameter("lastname");
//		String phone = request.getParameter("phone");
//		
//		User u = new User();
//		u.setFirstName(firstname);
//		u.setLastName(lastname);
//		u.setPhone(phone);
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("utente", u);
//		
//		session.setAttribute("saluto", "ciao");
//		
//		System.out.println("firstname : " + firstname);
//		System.out.println("lastname : " + lastname);
//		System.out.println("phone : " + phone);
//		
//		StringBuilder row = new StringBuilder();
//		row.append(firstname).append(';');
//		row.append(lastname).append(';');
//		row.append(phone).append(';');
//		
//		CSVutils.appendRowsInFile("C:\\temp\\rubrica.txt", row.toString());
//		
////		response.setContentType("text/html");
////		response.getWriter()
////		.append("firstname : " + firstname + "<br>")
////		.append("lastname : " + lastname + "<br>")
////		.append("phone : " + phone + "<br>");
//		
//		response.sendRedirect("end.jsp");
//	}

}
