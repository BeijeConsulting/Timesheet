package it.beije.erp.timesheet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.erp.importuser.DbTool;
import it.beije.erp.importuser.DemoRow;


@Controller
public class ImportDocumentsController {

	@RequestMapping(value = "/insertdata", method = RequestMethod.POST)
	public String insertData(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		try { 

			DbTool dbTool = new DbTool(); // Istanza di tipo DbTool
			
			String desc = request.getParameter("description"); // Ricevi in input stringa inserita da utente
			String doc_type = request.getParameter("doctype");
			String file_ext = request.getParameter("file_ext");
			dbTool.Insert(desc, doc_type, file_ext); // metodo Insert() per inserimento stringa su DB
			
			PrintWriter out = response.getWriter(); // da stampare se successo
			out.println("<html>"
					+ "<style>h2 {"
					+ "text-align: center;"
					+ "	color: #c9d0d4; font-family: 'Helvetica Neue', sans-serif; font-size: 46px; font-weight: 100; line-height: 50px; letter-spacing: 1px; padding: 0 0 40px;\r\n" 
					+ "}</style>" 
					+ "<body><b><h2><br/><br/><br/>Successfully Inserted"
						+ "</h2></b></body></html>"); 
			out.println("<script>" + 
					"         setTimeout(function(){" + 
					"            window.location.href = 'importdocuments.jsp';" + 
					"         }, 2000);" + 
					"      </script>");
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return "importdocuments";
	}
	
	@RequestMapping(value = "/updatedata", method = RequestMethod.POST)
	public String updateData(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		try { 

			DbTool dbTool = new DbTool();
			
			int id = Integer.valueOf(request.getParameter("id"));
			
			PrintWriter out = response.getWriter(); // da stampare se non c'è l'ID
			if(!dbTool.Exists(id)) {
				out.println("<html><body><b>Id not found</b></body></html>");
				
			}
			
			String newValue = request.getParameter("description");
			dbTool.Update(id, newValue); // aggiornamento dei dati su DB
			
			out.println("<html>"
					+ "<style>h2 {"
					+ "text-align: center;"
					+ "	color: #c9d0d4; font-family: 'Helvetica Neue', sans-serif; font-size: 46px; font-weight: 100; line-height: 50px; letter-spacing: 1px; padding: 0 0 40px;\r\n" 
					+ "}</style>" 
					+ "<body><b><h2><br/><br/><br/>Successfully Updated"
						+ "</h2></b></body></html>");  
			out.println("<script>" + 
					"         setTimeout(function(){" + 
					"            window.location.href = 'importdocuments.jsp';" + 
					"         }, 2000);" + 
					"      </script>");
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return "importdocuments";
	}
	
	@RequestMapping(value = "/deletedata", method = RequestMethod.POST)
	public String deleteData(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		try { 

			DbTool dbTool = new DbTool();
			
			int id = Integer.valueOf(request.getParameter("id"));
			
			PrintWriter out = response.getWriter(); // da stampare se non c'è l'ID
			if(!dbTool.Exists(id)) {
				out.println("<html><body><b>Id not found</b></body></html>");
			}
			
			dbTool.Delete(id); // cancellazione dei dati su DB
			
			out.println("<html>"
					+ "<style>h2 {"
					+ "text-align: center;"
					+ "	color: #c9d0d4; font-family: 'Helvetica Neue', sans-serif; font-size: 46px; font-weight: 100; line-height: 50px; letter-spacing: 1px; padding: 0 0 40px;\r\n" 
					+ "}</style>" 
					+ "<body><b><h2><br/><br/><br/>Successfully Deleted"
						+ "</h2></b></body></html>");  
			out.println("<script>" + 
					"         setTimeout(function(){" + 
					"            window.location.href = 'importdocuments.jsp';" + 
					"         }, 2000);" + 
					"      </script>");
			
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		return "importdocuments";
	}
	
	@RequestMapping(value = "/selectdata", method = RequestMethod.POST)
	public String selectData(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
	try { 

		DbTool dbTool = new DbTool();
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		PrintWriter out = response.getWriter(); // da stampare se non c'è l'ID
		if(!dbTool.Exists(id)) {
			out.println("<html>"
					+ "<style>h2 {"
					+ "text-align: center;"
					+ "	color: #c9d0d4; font-family: 'Helvetica Neue', sans-serif; font-size: 46px; font-weight: 100; line-height: 50px; letter-spacing: 1px; padding: 0 0 40px;\r\n" 
					+ "}</style>" 
					+ "<body><b><h2><br/><br/><br/>ID Not Found"
						+ "</h2></b></body></html>"); 
			out.println("<script>" + 
					"         setTimeout(function(){" + 
					"            window.location.href = 'insert.html';" + 
					"         }, 2000);" + 
					"      </script>");
		}
		
		DemoRow dr = dbTool.Select(id); // interrogazione dei dati su DB
		
		out.println("<html><body>"
				+ "<table>"
				+ "  <tr>"
				+ "    <th>ID</th>"
				+ "    <th>Value</th>"
				+ "  </tr>"
				+ "  <tr>"
				+ "    <td>"+ dr.GetID() +"</td>"
				+ "    <td>"+ dr.GetValue() +"</td>"
				+ "  </tr>"
				+ "</table>"
				+ "</body></html>"); 
		
	} 
	catch (Exception e) { 
		e.printStackTrace(); 
	}
	
	return "importdocuments";
	}
	
	@RequestMapping(value = "/selectalldata", method = RequestMethod.POST)
	public String selectAllData(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		try { 

			DbTool dbTool = new DbTool();
			
			DemoRow[] drs = dbTool.Select(); // interrogazione dei dati su DB
			
			PrintWriter out = response.getWriter(); // da stampare
			
			out.println("<html><body>"
					+ "<table>"
					+ "  <tr>"
					+ "    <th>ID</th>"
					+ "    <th>Value</th>"
					+ "  </tr>"); 

			for (DemoRow dr : drs)
			{
				out.println("  <tr>"
						+ "    <td>"+ dr.GetID() +"</td>"
						+ "    <td>"+ dr.GetValue() +"</td>"
						+ "  </tr>"); 
			}
			
			out.println("</table>"
					+ "</body></html>"); 

		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		return "importdocuments";
	}
}