package controllers;

import play.data.validation.Email;
import play.data.validation.Required;
import play.mvc.*;

public class Contact extends Controller {

    public static void index() {
    	
        render();
    }
    
    public static void sendEmail(@Required String name, 
    								@Required @Email String email,
    								String url,
    								String message) {
    	System.out.println(name + ":" + email);
    	
    	ok();
    }

}
