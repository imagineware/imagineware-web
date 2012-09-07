package controllers;

import models.IwEvent;
import play.data.validation.Required;
import play.mvc.*;

public class Events extends Controller {

	// Event page
    public static void index(@Required String evCode) {
    	
    	IwEvent event = IwEvent.find("byEvCode", evCode).first();
    	
    	if(event != null && event.isPublished) {
    		render(event);
    	} else {
    		notFound();
    	}    	
    }

}
