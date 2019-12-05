package it.beije.erp.importuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTool {

	private Connection _con;

	public DbTool() throws ClassNotFoundException, SQLException
	{
		_con = DatabaseConnection.initializeDatabase(); // inizializzare connessione al DB
	}

	public void finalize() throws SQLException { // se la connessione è aperta, chiudila
		if(_con != null) {
			_con.close();
		}
	}
	
	public boolean Exists(int id) throws SQLException // metodo per verificare esistenza dell'ID
	{
		boolean res = false;
		PreparedStatement st = _con.prepareStatement("SELECT COUNT(ID) AS RESULT FROM demo WHERE ID=?"); 
		st.setInt(1, id); 
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			res = rs.getInt("RESULT") != 0;
		}
		st.close(); 
		return res;
	}
	
	public void Insert(String desc, String doc_type, String file_ext) throws SQLException // metodo Insert
	{
		int userId = 0;
		String filename = doc_type + "." + file_ext;
		
		PreparedStatement st = _con.prepareStatement("INSERT INTO demo(description,doc_type,file_ext,user_id,filename) VALUES(?,?,?,?,?)"); 
		st.setString(1, desc); 
		st.setString(2, doc_type); 
		st.setString(3, file_ext);
		st.setInt(4, userId);
		st.setString(5, filename);
		st.executeUpdate(); 
		st.close(); 
	}
	
	public void Update(int id, String newValue) throws SQLException // metodo Update
	{
		PreparedStatement st = _con.prepareStatement("UPDATE demo SET description=? WHERE id=?"); 
		st.setString(1, newValue); 
		st.setInt(2, id); 
		st.executeUpdate(); 
		st.close(); 
	}
	
	public void Delete(int id) throws SQLException // metodo Delete
	{
		PreparedStatement st = _con.prepareStatement("DELETE FROM demo WHERE id=?"); 
		st.setInt(1, id); 
		st.executeUpdate(); 
		st.close();
	}
	
	public DemoRow Select(int id) throws SQLException // metodo Select
	{
		DemoRow res = null;
		PreparedStatement st = _con.prepareStatement("SELECT * FROM demo WHERE id=?"); 
		st.setInt(1, id); 
		ResultSet rs = st.executeQuery(); 
		while (rs.next()) {
			res = new DemoRow(rs.getInt("id"), rs.getString("description"));
		}
		st.close();
		return res;
	}

	public DemoRow[] Select() throws SQLException // metodo Select
	{
		List<DemoRow> res = new ArrayList<>();
		PreparedStatement st = _con.prepareStatement("SELECT * FROM demo"); 
		ResultSet rs = st.executeQuery(); 
		while (rs.next()) {
			res.add(new DemoRow(rs.getInt("id"), rs.getString("description")));
		}
		st.close();
		
		DemoRow[] rows = new DemoRow[res.size()];
		rows = res.toArray(rows);
		return rows;
	}
}
