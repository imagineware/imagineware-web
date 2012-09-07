package controllers.admin;

import play.mvc.With;
import models.IwEvent;
import controllers.CRUD;
import controllers.Secure;

@CRUD.For(IwEvent.class)
@With(Secure.class)
public class EventsCRUD extends CRUD {
	
}
