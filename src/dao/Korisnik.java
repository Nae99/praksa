package dao;

public class Korisnik {
	private int id;
	private String password;
	private String name;
	private int gornji;
	private int donji;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGornji() {
		return gornji;
	}
	public void setGornji(int gornji) {
		this.gornji = gornji;
	}
	public int getDonji() {
		return donji;
	}
	public void setDonji(int donji) {
		this.donji = donji;
	}

	
}
