package ch.hcuge.simed.foundation;

import ch.hcuge.simed.foundation.security.ActionAuthenticate;
import ch.hcuge.simed.foundation.security.SimedBCrypt;
import er.extensions.appserver.ERXApplication;

public class SimedApplication extends ERXApplication {

	private static ActionAuthenticate _actionAuthenticate;

	static {
		_actionAuthenticate = new SimedBCrypt();
	}

	/**
	 * @return the actionauthenticate
	 */
	public static final ActionAuthenticate actionauthenticate() {
		return _actionAuthenticate;
	}

}
