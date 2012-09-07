package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller {

	// Main page
    public static void index() {
    	
    	List<IwEvent> incoming = Events.getIncomingEvents(3);
    	
    	List<IwEvent> past = Events.getPastEvents(3);
    	
        render(incoming, past);
    }

}