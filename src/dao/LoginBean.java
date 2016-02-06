package dao;

/**
* LoginBean klasa predstavlja entitet
* za upravljanje entitetom na bazi 
* 
* Tabela: users
* Kolone: id, userid, name, pwd
*/
public class LoginBean
{
		private String pwd;
		private String name;
		
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
}
