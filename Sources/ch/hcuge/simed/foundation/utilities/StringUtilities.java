package ch.hcuge.simed.foundation.utilities;

public class StringUtilities {

	/**
	 * @param key
	 * @return the class to use
	 * 
	 * Should be used in a Wognl binding of the form
	 * <pre>
	 * <wo:str value="~@ch.hcuge.simed.foundation.utilities.StringUtilities@errorLevel('aFieldName')">
	 * </pre>
	 */
	public static String errorLevel(String key) {
		if (key.equalsIgnoreCase("Error")) {
			return "Red";
		} else if (key.equalsIgnoreCase("Warn")) {
			return "Orange";
		} else {
			return "";
		}
	}

}
