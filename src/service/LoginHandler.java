package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.DBConnection;
import dao.Korisnik;
import dao.LoginBean;

/**
 * LoginHandler klasa - upravljajne akcijama nad tabelom users
 * 
 * Metode: checkLogin, getLogin, getAllUsers, getUser, deleteUser, insertData, updateData
 */
public class LoginHandler {
	/*
	 * Metoda za provjeru ispravnosti unešenog username-a i passworda
	 * Ukoliko u bazi ne postoji ni jedan red sa odgovarajućim username-om vrati false
	 * Ukoliko je pronašao usera i ukoliko unešeni password odgovara passwordu u bazi, vrati true
	 */
	public boolean checkLogin (String username, String pass)  {
		boolean l = false;
		try {
			LoginBean lB = new LoginBean();
			lB = getLogin(username);
			if (lB != null){
				if (lB.getPwd().equals(pass)) l = true;
			}
		}
		catch (Exception e) {
			l = false;
			System.out.println("Check login: " + e.toString());
		}
		return l;
	}
	public LoginBean getLogin(String username) {
		LoginBean loginBean = new LoginBean();	
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ssmkdb.users WHERE name = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {	
				loginBean.setName(rs.getString("name"));
				loginBean.setPwd(rs.getString("password"));
				return loginBean;
			}
		} catch (Exception e) {   
			System.out.println("Nije uspjela konekcija prema bazi podataka: " + e.toString());
		}
		return null;
	}

	

}
