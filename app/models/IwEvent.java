package models;

import play.*;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.URL;
import play.data.validation.Unique;
import play.db.jpa.*;
import play.templates.JavaExtensions;

import javax.persistence.*;

import java.util.*;

/**
 * This class is named IwEvent (Iw is for ImagineWare) because
 * 'event' might be a reserved keyword in some Databases
 * @author alfongj
 *
 */
@Entity
public class IwEvent extends Model {
	public boolean isPublished = false;

	@Required
	@Unique
	public String evCode = null;

	@Required
	@MaxSize(60)
	public String title;

	/** Generates an unique event code (evCode) as a slugified version of the title */
	public void setTitle(String title) {
		this.title = title;

		// The evCode is a slugified version of the title
		// i.e., 'this-is-the-title'
		String evCode = JavaExtensions.slugify(title, true);

		if (this.evCode == null) {
			do {
				if (IwEvent.find("byEvCode", evCode).fetch().isEmpty()) {
					this.evCode = evCode;
					break;
				} else 
					evCode = evCode + 2;
			} while (this.evCode != null);

		} else {
			if (!this.evCode.equals(evCode)	&& IwEvent.find("byEvCode", evCode).fetch().isEmpty())
				this.evCode = evCode;
		}
	}
	
	@Required
	public Date publishDate;
	
	@Required
	public Date eventDate;
	
	@Required
	public String time; //e.g., "De 17:00 a 18:00"
	
	@Required
	@Lob
	@MaxSize(1000)
	public String location;
	
	@Required
	@Lob
	@MaxSize(10000)
	public String description; //This should include all HTML formatting and everything
	
	@Required
	@URL
	public String smallPhotoURL; //300x180 image for the Home page
	
	@Required
	@URL
	public String bigPhotoURL; //650x390 image for the event page
	
	
	public IwEvent() {
		publishDate = new Date();
	}
	
	public IwEvent(boolean isPublished, String title, Date eventDate, String time, String location, String description, String smallPhotoURL, String bigPhotoURL){
		this();
		
		this.isPublished = isPublished;
		setTitle(title);
		this.eventDate = eventDate;
		this.time = time;
		this.location = location;
		this.description = description;
		this.smallPhotoURL = smallPhotoURL;
		this.bigPhotoURL = bigPhotoURL;
	}
	
}
