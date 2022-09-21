/**
 * 
 */
package ch.hcuge.simed.foundation.utilities.validator;

import com.webobjects.foundation.NSMutableDictionary;

/**
 * @author dban
 * 
 */
public class ValidationProblem extends NSMutableDictionary<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String KEY = "key";
	public static final String PROBLEM = "problem";
	public static final String SEVERITY = "severity";

	public static final String STYLE = "style";
	public static final String STYLE_FOR_LIST = "styleForList";
	public static final String CLASS_NAME = "className";

	public static final Integer CLEANUP = Integer.valueOf(0);
	public static final Integer INFO = Integer.valueOf(1);
	public static final Integer WARN = Integer.valueOf(10);
	public static final Integer ERROR = Integer.valueOf(50);

	/**
	 * 
	 */
	public ValidationProblem(String key, String problem, Integer severity) {
		this.setObjectForKey(key, KEY);
		this.setObjectForKey(problem, PROBLEM);
		this.setObjectForKey(severity, SEVERITY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webobjects.foundation.NSDictionary#valueForKey(java.lang.String)
	 */
	@Override
	public Object valueForKey(String key) {
		if (key.equals(STYLE)) {
			return this.style();
		}
		if (key.equals(STYLE_FOR_LIST)) {
			return styleForList();
		}
		if (key.equals(CLASS_NAME)) {
			return className();
		}
		return super.valueForKey(key);
	}

	/**
	 * @return the key
	 */
	public final String key() {
		return (String) this.valueForKey(KEY);
	}

	/**
	 * @return the problem
	 */
	public final String problem() {
		return (String) this.valueForKey(PROBLEM);
	}

	/**
	 * @return the severity
	 */
	public final Integer severity() {
		return (Integer) this.valueForKey(SEVERITY);
	}

	public boolean isInfo() {
		return this.severity().equals(INFO);
	}

	public boolean isWarning() {
		return this.severity().equals(WARN);
	}

	public boolean isError() {
		return this.severity().equals(ERROR);
	}
// will return standard class data -> no more in wo. no more conflict 
	public String style() {
		String style = "";
		if (this.severity().equals(ERROR)) {
			style = "." + this.key() + "_error {background-color: red !important;}"; //important sinon marche pas
		} else if (this.severity().equals(WARN)) {
			style = "." + this.key() + "_warn {background-color: orange !important;}";
		} else if (this.severity().equals(INFO)) {
			style = "." + this.key() + "_info {background-color: cyan;}";
		}
		return style;
	}

	public String styleForList() {
		String style = "";
		if (this.severity().equals(ERROR)) {
			style = "font-weight:bold; color: red;";
		} else if (this.severity().equals(WARN)) {
			style = "background-color: orange; color:black";
		} else if (this.severity().equals(INFO)) {
			style = "color: cyan;";
		}
		return style;
	}

	public String className() {
		String style = "";
		if (this.severity().equals(ERROR)) {
			style = this.key() + "_error";
		} else if (this.severity().equals(WARN)) {
			style = this.key() + "_warn";
		} else if (this.severity().equals(INFO)) {
			style = this.key() + "_info";
		}
		return style;
	}

}
