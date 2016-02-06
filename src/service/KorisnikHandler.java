package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.DBConnection;
import dao.Korisnik;
import dao.LoginBean;

public class KorisnikHandler {
	
	/*
	 * Metoda vraća listu svih usera u tabeli users
	 */
	public ArrayList<Korisnik> getAllUsers() {
		ArrayList<Korisnik> result = new ArrayList<Korisnik>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.users");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Korisnik d = new Korisnik();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setGornji(rs.getInt("gornjiP"));
				d.setDonji(rs.getInt("donjiP"));
				d.setPassword(rs.getString("password"));
				result.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Metoda vraća određenog usera za vrijednost id kolone
	 */
	public Korisnik getUser(int id) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.users where id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Korisnik d = new Korisnik();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				d.setGornji(rs.getInt("gornjiP"));
				d.setDonji(rs.getInt("donjiP"));
				d.setPassword(rs.getString("password"));
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/*
	 * Metoda za dodavanje novog usera u tabelu
	 */
	public void insertData(Korisnik user) {  

		String sql = "INSERT INTO users "  
				+ "(id, name, password, donji, gornji) VALUES (?, ?, ?,?, ?)";  
		Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getDonji());
			stmt.setInt(5, user.getGornji());
			
			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  

	/*
	 * Metoda za editovanje usera na osnovu Id kolone
	 */
	public void updateData(Korisnik user) {  

		String sql = "UPDATE users SET name = ?, userid = ?, pwd = ? WHERE id = ?";
		Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getDonji());
			stmt.setInt(5, user.getGornji());
			
			int rs = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public void delete(Korisnik user) {  

		String sql = "DELETE FROM ssmkdb.users WHERE id = ?";
		Connection conn;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			boolean rs = stmt.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
