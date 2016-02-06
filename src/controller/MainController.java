package controller;

/*
 * MainController klasa
 * @Controller - allows for implementation classes to be autodetected through classpath scanning. 
 * It is typically used in combination with annotated handler methods based on the @RequestMapping annotation
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import service.KorisnikHandler;
import service.LoginHandler;
import dao.Korisnik;
import dao.LoginBean;

@Controller
public class MainController {

	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/
	 */
	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		//Metoda kreira ModelAndView nad Login.jsp stranicom
		ModelAndView modelandview = new ModelAndView("Login");
		//U ModelAndView se prosljeđuje prazan objekat login bean
		LoginBean loginBean = new LoginBean();
		modelandview.addObject("loginBean", loginBean);
		return modelandview;
	}

	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/login
	 * Napomena: /login će se prozvati jedino u slučaju RequestMethod.GET tj kada se učitava stranica
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletResponse response)throws IOException {
		ModelAndView model = new ModelAndView("Login");
		LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	}

	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/login
	 * Napomena: /login će se prozvati jedino u slučaju RequestMethod.POST 
	 * kada se šalju podaci sa stranice tj. kada korisnik klikne dugme
	 * 
	 * Metoda vrši kontrolu unešenih vrijednosti userid i passworda
	 * sa vrijednostima u bazi
	 * Ukoliko je login uspješan vrši se redirect
	 * Ukoliko login nije uspješan ostaje se na početnoj stranici
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") LoginBean loginBean)
	{
		ModelAndView model = null;
		LoginHandler lH = new LoginHandler();
		try
		{
			//preuzimanje unešenog userid-ja i passworda
			String username = loginBean.getName();
			String pwd = loginBean.getPwd();
			//provjera na bazi
			boolean isValidUser = lH.checkLogin(username, pwd);
			//provjera loginda true/false
			if (isValidUser)
			{
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", loginBean.getName());
				//redirect na stranicu UserList.jsp
				model = new ModelAndView("UserList");
				try {
					//popuni stranicu UserList.jsp sa svim userima iz baze
					KorisnikHandler lH1 = new KorisnikHandler();
					ArrayList<Korisnik> userList = new ArrayList<Korisnik>();
					userList = lH1.getAllUsers();
					model.addObject("userList", userList);

				} catch (Exception e) {
					System.out.println("Error: " + e.toString());
				}

			}
			else {
				System.out.println("Else: " + isValidUser);
				//redirect na stranicu Login.jsp
				model = new ModelAndView("Login");
				model.addObject("loginBean", loginBean);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	
	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/showList
	 * 
	 * Za razliku od /login POST metode /showList uvijek učitava sve korisnike
	 * iz baze. 
	 * Metoda se koristi za refresh u slučaju dodavanja, editovanja, brisanja usera iz liste
	 * Napomena: korisnik je već ulogovan u aplikaciju stoga 
	 * se u ovoj metodi ne vrši provjera logina
	 */
	@RequestMapping(value = "/showList")
	public ModelAndView showListData(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean") LoginBean loginBean)
	{
		ModelAndView model = null;
		LoginHandler lH = new LoginHandler();
		try
		{
				request.setAttribute("loggedInUser", loginBean.getName());
				//redirect na stranicu UserList.jsp
				model = new ModelAndView("UserList");
				try {
					//popuni stranicu UserList.jsp sa svim userima iz baze
					KorisnikHandler lH1 = new KorisnikHandler();
					ArrayList<Korisnik> userList = new ArrayList<Korisnik>();
					userList = lH1.getAllUsers();
					model.addObject("userList", userList);
				} catch (Exception e) {
					System.out.println("Error: " + e.toString());
				}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/insert
	 * 
	 * Metoda insert se poziva sa add.jps kada se klikne dugme
	 * Metoda se koristi za spašavanje usera u bazi
	 * @ModelAttribute cuva unešene vrijednosti sa weba i spašava ih u bazu
	 * Nakon inserta vrši se redirect na login stranicu
	 */
	 @RequestMapping("/insert")  
	 public String inserData(@ModelAttribute Korisnik user) {  
	  KorisnikHandler lH1 = new KorisnikHandler();
	  if (user != null)  
		  lH1.insertData(user);  
	  return "redirect:/login";  
	 }  
	
	/*
	 * Metoda se poziva kada se otvori link
	 * http://localhost:XXXX/Lab6_LoginPrimjer1/update
	 * 
	 * Metoda insert se poziva sa add.jps kada se klikne dugme
	 * Metoda se koristi za spašavanje usera u bazi
	 * @ModelAttribute cuva unešene vrijednosti sa weba i spašava ih u bazu
	 * Nakon inserta vrši se redirect na login stranicu
	 */
	 @RequestMapping("/update")  
	 public String updateUser(@ModelAttribute Korisnik user) {  
		 KorisnikHandler lH1 = new KorisnikHandler();
		 System.out.println("111: " + user.getName());
		 lH1.updateData(user);  
	  return "redirect:/showList";  
	  
	 } 

	 /*
	 * Metoda kreira novi ModelAndView nad add.jsp stranicom
	 * Učitavaju se prazna polja
	 */
	 @RequestMapping("/add")  
	 public ModelAndView addUser(@ModelAttribute LoginBean user) {  
		 return new ModelAndView("add");  
	 } 
	 
	 /*
	 * Metoda edit prima jedan ulazni parametar (id)
	 * Na stranici UserList.jsp definirano je link Edit na način: <a href="edit?id=${user.id}">Edit</a>
	 * 		edit?id=${user.id}
	 * 		metoda?parametar=vrijednost_parametra
	 * @RequestParam označava da metoda očekuje 1 ulazni parametar koji mora biti označen kao id
	 */
	 @RequestMapping("/edit")  
	 public ModelAndView editUser(@RequestParam String id, @ModelAttribute LoginBean user) {  
		 
	  KorisnikHandler lH1 = new KorisnikHandler();
	  Korisnik userData = lH1.getUser(Integer.valueOf(id));

	  //kreira se modelandview nad edit.jsp stranicom kojoj se prosljeđuje
	  //user koji je pronađen u bazi za proslijeđeni Id
	  ModelAndView v = new ModelAndView("edit");
	  v.addObject("loginBean", userData);  
	  //učitavaju se podaci o odabranom korisniku
	  return v;  
	  
	 }
	 @RequestMapping("/delete")  
	 public String deleteUser(@RequestParam String id,  
	   @ModelAttribute LoginBean user) {  
		 
	  KorisnikHandler lH1 = new KorisnikHandler();
	  Korisnik userData = lH1.getUser(Integer.valueOf(id));
	  lH1.delete(userData);
	  //kreira se modelandview nad edit.jsp stranicom kojoj se prosljeđuje
	  //user koji je pronađen u bazi za proslijeđeni Id
	  //ModelAndView v = new ModelAndView("edit");
	  //v.addObject("loginBean", getUserData);  
	  //učitavaju se podaci o odabranom korisniku
	  return "redirect:/showList"; 
	  
	 }
}
