package controllers;

import play.data.validation.Required;
import play.mvc.*;

public class Events extends Controller {

	// Event page
    public static void index(@Required String evName) {
        renderText(evName);
    }

}
