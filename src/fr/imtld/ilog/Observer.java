package fr.imtld.ilog;

import javax.security.auth.Subject;

public abstract class Observer {
	protected Subject subject;
	public abstract void update();

}
