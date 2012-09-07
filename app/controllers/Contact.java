package controllers;

import models.IwEvent;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.data.validation.Email;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.*;

public class Contact extends Controller {

    public static void index() {
        render();
    }
    
    public static void sendEmail(@Required String name, 
    								@Required @Email String email,
    								String url,
    								String message) {
    	
    	if(validation.hasErrors()) badRequest();
    	else {
        	try {
	        	SimpleEmail msg = new SimpleEmail();
	        	msg.setFrom("user-enquiry@imagineware.org");
	        	msg.addTo("asociacion.imagineware@gmail.com");
	        	msg.setSubject("Contacto vía web");
	        	
	        	StringBuilder content = new StringBuilder();
	        	content.append("NAME: ").append(name).append("\n");
	        	content.append("EMAIL: ").append(email).append("\n");
	        	if(url != null && url.length() > 0)
	        		content.append("URL: ").append(url).append("\n");
	        	content.append("_____________________________\n");
	        	if(message != null && message.length() > 0)
	        		content.append(message);
	        	
				msg.setMsg(content.toString());
				Mail.send(msg);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        	
        	ok();
    	}
    }

}
