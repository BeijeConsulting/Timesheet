package it.beije.timesheet.entities.methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.beije.erp.entity.User;

public class DBImportExport {
	
	public static void main(String... args) throws Exception {
		
		importCsv("/Users/davidezacchino/Documents/Corso Java Beije/eclipse-workspace/primo/prova.csv");
	}
	
	//IMPORT CSV METHOD
	public static void importCsv(String pathFile) throws Exception{
		
		SessionFactory factory = DBImportExport.getFactory();
		Session session = factory.openSession();

		
		List<String> rows = new ArrayList<String>();

		rows = getFileAsStrings(pathFile);
//		rows.remove(0);
		
		String[] insert = new String[rows.size()]; //ritorna tutte le query necessarie

		List <User> user = new ArrayList<User>();//.add(Restrictions.eq("nome", "pippo"))

		int i=0;
		//INSERT
		for (String conversione : rows) {
			Transaction transaction = session.beginTransaction();
			User utente = new User();
		    String[] parts = conversione.split(";");
		    
		    String first_name = parts[1];
		    String last_name = parts[2];
		    String personal_email = parts[3];
		    String work_email = parts[4];
		    String phone = parts[5];
		    String fiscal_code = parts[6];
		    String admin = parts[7];
		    String password = parts[8];
		    
		    utente.setFirstName(first_name);
//		    utente.setLast_name(last_name);
//		    utente.setPersonal_email(personal_email);
//		    utente.setWork_email(work_email);
//		    utente.setPhone(phone);
//		    utente.setFiscal_code(fiscal_code);
//		    utente.setAdmin(Boolean.parseBoolean(admin));
//		    utente.setPassword(password);
//		    
//		    if (UserMethods.getUser(utente.getFiscal_code()) == null) {
//		    	session.save(utente);
//		    } else {
//		    	System.out.println("Utente già esistente");
//		    }
		    
		    transaction.commit();;
		}
			
		session.close();
		factory.close();
		
		System.out.println("CSV importato");
} //Chiusura Metodo
	
	//EXPORT CSV METHOD
	public static void exportCsv(String pathfile) throws Exception {
		
		SessionFactory factory = DBImportExport.getFactory();
		Session session = factory.openSession();
				
		Criteria criteria = session.createCriteria(User.class);
		
		List<User> user = null;
		
        try {
            FileWriter fw = new FileWriter(pathfile);
			
	        user = criteria.list();

//            for (User i : user) {
//                fw.append(String.valueOf(i.getId()));
//                fw.append(';');
//                fw.append(i.getFirst_name());
//                fw.append(';');
//                fw.append(i.getLast_name());
//                fw.append(';');
//                fw.append(i.getPersonal_email());
//                fw.append(';');
//                fw.append(i.getWork_email());
//                fw.append(';');
//                fw.append(i.getPhone());
//                fw.append(';');
//                fw.append(i.getFiscal_code());
//                fw.append(';');
//                fw.append(String.valueOf(i.getAdmin()));
//                fw.append(';');
//                fw.append(i.getPassword());
//                fw.append('\n');
//               }
            fw.flush();
            fw.close();
            System.out.println("CSV File creato correttamente.");
            }
    	catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}       
	} //End exportCsv
	
	//CREATE XML FILE 
	public static void exportXml(String pathfile) throws Exception {
		
		SessionFactory factory = DBImportExport.getFactory();
		Session session = factory.openSession();
				
		Criteria criteria = session.createCriteria(User.class);
		
		List<User> user = null;
		
		 DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();

	        Element root = doc.createElement("User");
	        doc.appendChild(root);
	        
			ResultSet rset = null;
			
		try {
	        user = criteria.list();

//	        for (User i : user) {
//            	String row = String.valueOf(i.getId()) + ";" + i.getFirst_name() + ";" + i.getLast_name() + ";" 
//            				+ i.getPersonal_email() + ";" + i.getWork_email() + ";" + i.getPhone() 
//            				+ ";" + i.getFiscal_code() + ";" + String.valueOf(i.getAdmin()) + ";" + i.getPassword() + ";";
//            	String[] parts = row.split(";");
//            	
//            	Element Details = doc.createElement("Dipendente");
//		        root.appendChild(Details);	
//		        
//		        String id_element = parts[0];
//		        String first_name_element = parts[1];
//		        String last_name_element = parts[2];
//		        String personal_email_element = parts[3];
//		        String work_email_element = parts[4];
//		        String phone_element = parts[5];
//		        String fiscal_code_element = parts[6];
//		        String admin_element = parts[7];
//		        String password_element = parts[8];
//
//	            Element id = doc.createElement("id");
//	            id.appendChild(doc.createTextNode(id_element));
//	            Details.appendChild(id);
//
//		        
//	            Element first_name = doc.createElement("first_name");
//	            first_name.appendChild(doc.createTextNode(first_name_element));
//	            Details.appendChild(first_name);
//
//	            
//	            Element last_name = doc.createElement("last_name");
//	            last_name.appendChild(doc.createTextNode(last_name_element));
//	            Details.appendChild(last_name);
//
//	            
//	            Element personal_email = doc.createElement("personal_mail");
//	            personal_email.appendChild(doc.createTextNode(personal_email_element));
//	            Details.appendChild(personal_email);
//
//	            
//	            Element work_email = doc.createElement("work_mail");
//	            work_email.appendChild(doc.createTextNode(work_email_element));
//	            Details.appendChild(work_email);
//	            
//	            
//	            Element phone = doc.createElement("phone");
//	            phone.appendChild(doc.createTextNode(phone_element));
//	            Details.appendChild(phone);
//
//	            
//	            Element fiscal_code = doc.createElement("fiscal_code");
//	            fiscal_code.appendChild(doc.createTextNode(fiscal_code_element));
//	            Details.appendChild(fiscal_code);
//
//	            
//	            Element admin = doc.createElement("admin");
//	            admin.appendChild(doc.createTextNode(admin_element));
//	            Details.appendChild(admin);
//
//
//	            Element password = doc.createElement("password");
//	            password.appendChild(doc.createTextNode(password_element));
//	            Details.appendChild(password);
//               } //end while
		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}

	         // Save the document to the disk file
	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();
	        
	        // format the XML nicely
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

	        aTransformer.setOutputProperty(
	                "{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

	        DOMSource source = new DOMSource(doc);
	        try {
	            FileWriter fos = new FileWriter(pathfile);
	            StreamResult result = new StreamResult(fos);
	            aTransformer.transform(source, result);
	            System.out.println("XML File creato correttamente.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	} //End exportXml
	
	
	//GET FILE AS STRING metodo usato per import CSV
	private static List<String> getFileAsStrings(String pathFile) throws IOException {
		List<String> content = new ArrayList<String>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pathFile));
			
			while(reader.ready()) {
				content.add(reader.readLine());
			}
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;
		} finally {
			try {
				reader.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return content;
	}
	
	
	//CONNESSIONE AL FACTORY
	private static SessionFactory getFactory() throws Exception {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(User.class)
				.buildSessionFactory();		
		return factory;
	}
	
} //END CLASS