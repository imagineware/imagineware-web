package controllers.security;

import play.Play;
import controllers.Secure;

public class SecurityController extends Secure.Security{

	static boolean authenticate(String username, String password) {
        
		if(username == null || password == null) return false;
		
		if(!username.equals("admin")) return false;
		
		if(!password.equals(Play.configuration.getProperty("admin.password"))) return false;
		
		return true;
    }
	
}
