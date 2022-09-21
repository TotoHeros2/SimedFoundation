package ch.hcuge.simed.foundation.security;

import java.util.Map;


import er.extensions.crypting.BCrypt;

public class SimedBCrypt extends BCrypt implements ActionAuthenticate {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#authenticate
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticate(String credential, String password, String login) {
		return BCrypt.checkpw(password, credential);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#
	 * canRetrievePassword()
	 */
	@Override
	public boolean canRetrievePassword() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#decodeCredential
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public String decodeCredential(String credential, String login) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#encryptPassword
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public String encryptPassword(String password, String login) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#
	 * setAdditionalInformation(java.util.Map)
	 * 
	 * Not used
	 */
	@Override
	public void setAdditionalInformation(Map<String, Object> userInfo) {
		// nothing to do.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * 
	 * @return name of the class
	 */
	@Override
	public String toString() {
		return "Authentication strategy:" + this.getClass().getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.hcuge.simed.cohort.transverse.security.ActionAuthenticate#copy()
	 */
	@Override
	public ActionAuthenticate copy() {
		return this;
	}

}
