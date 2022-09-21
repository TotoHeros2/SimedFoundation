package ch.hcuge.simed.foundation;

import org.apache.log4j.Logger;

import ch.hcuge.simed.foundation.interfaces.eo.GenericPatient;
import ch.hcuge.simed.foundation.interfaces.eo.GenericUser;

import er.extensions.appserver.ERXSession;

public class SimedSession extends ERXSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String KEY_USER_PRIVILEGES = "userPrivileges";
	public static final String KEY_USER_ROLES = "userRoles";

	public static Logger log = Logger.getLogger(SimedSession.class);

	private GenericUser _user;
	private GenericPatient _patient;

	// private User

	public SimedSession() {
		super();
	}

	public GenericUser user() {
		return this._user;
	}

	public void setUser(GenericUser newUser) {
		this._user = newUser;
	}

	public GenericPatient patient() {
		return this._patient;
	}

	public void setPatient(GenericPatient newPatient) {
		this._patient = newPatient;
	}

	public boolean hasPatient() {
		return this.patient() != null;
	}

}
