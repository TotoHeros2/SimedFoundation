package ch.hcuge.simed.foundation.extendableenum;

import java.io.Serializable;

public abstract class ExtendableEnum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor to be used during Serialization
	 */
	public ExtendableEnum() {
		// Nothing only for serialization
	}

	/**
	 * @return the code to be inserted in the database
	 */
	public abstract String getCode();

	/**
	 * @param newCode
	 *            set the code for the database
	 */
	public abstract void setCode(String newCode);

	/**
	 * @return the string to be displayed in the UI
	 */
	public abstract String getDisplayValue();

	/**
	 * @param newDisplayValue
	 *            set the string for the UI
	 */
	public abstract void setDisplayValue(String newDisplayValue);

}
