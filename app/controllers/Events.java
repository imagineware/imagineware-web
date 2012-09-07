package controllers;

import java.util.Date;
import java.util.List;

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
    
    /**
     * Returns a list of the most close incoming events to the current date,
     * ordered by closest first.
     * 
     * @param limit
     * 		Number of incoming events to return, -1 for all.
     */
    public static List<IwEvent> getIncomingEvents(int limit) {
    	List<IwEvent> incomingEvents;
    	
    	if(limit > 0)
    		incomingEvents = IwEvent.find("select e from IwEvent e where e.eventDate > CURRENT_TIME and e.isPublished = true order by eventDate asc").fetch(limit);
    	else 
    		incomingEvents = IwEvent.find("select e from IwEvent e where e.eventDate > CURRENT_TIME and e.isPublished = true order by eventDate asc").fetch();
    	
    	// Although the queries are correct, the CURRENT_TIME variable seems not to have the correct time under
    	// all scenarios, so we now manually remove any event which doesn't fulfill the time condition
    	
    	for (int i = 0; i < incomingEvents.size(); i++) {
			if(!incomingEvents.get(i).eventDate.after(new Date())) {
				incomingEvents.remove(i);
				i--;
	    	}
		}
    	
    	return incomingEvents;
    }
    
    /**
     * Returns a list of the most close past events to the current date,
     * ordered by closest first.
     * 
     * @param limit
     * 		Number of past events to return, -1 for all.
     */
    public static List<IwEvent> getPastEvents(int limit) {
    	List<IwEvent> pastEvents;
    	
//    	if(limit > 0)
//    		pastEvents = IwEvent.find("select e from IwEvent e where e.eventDate < CURRENT_TIME and e.isPublished = true order by eventDate desc").fetch(limit);
//    	else 
//    		pastEvents = IwEvent.find("select e from IwEvent e where e.eventDate < CURRENT_TIME and e.isPublished = true order by eventDate desc").fetch();
//    	
    	// Although the queries are correct, the CURRENT_TIME variable seems not to have the correct time under
    	// all scenarios, so we manually add them
    	
    	pastEvents = IwEvent.find("SELECT e from IwEvent e WHERE e.isPublished = true ORDER BY e.eventDate DESC").fetch();
    	
    	for (int i = 0; i < pastEvents.size(); i++) {
			if(!pastEvents.get(i).eventDate.before(new Date())) {
				pastEvents.remove(i);
				i--;
			}
		}
    	
    	return pastEvents;
    }

}
