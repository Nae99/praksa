package dao;

import java.util.Date;

public class Ocitanja {

	private int id;
	private Korisnik korisnik;
	private double donjiP;
	private double gornjiP;
	private Date vrijeme;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public double getDonjiP() {
		return donjiP;
	}
	public void setDonjiP(double donjiP) {
		this.donjiP = donjiP;
	}
	public double getGornjiP() {
		return gornjiP;
	}
	public void setGornjiP(double gornjiP) {
		this.gornjiP = gornjiP;
	}
	public Date getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}

}
