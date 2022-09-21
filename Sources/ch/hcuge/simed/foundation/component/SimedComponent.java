package ch.hcuge.simed.foundation.component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import org.apache.log4j.Logger;

import ch.hcuge.simed.foundation.interfaces.component.EditionMode;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.ERXRequest;
import er.extensions.appserver.ERXWOContext;
import er.extensions.components.ERXComponent;
import er.extensions.formatters.ERXJodaDateTimeFormatter;
import er.extensions.formatters.ERXJodaLocalDateFormatter;
import er.extensions.formatters.ERXJodaLocalDateTimeFormatter;
import inputvalidation.SafeHTMLValidator;

public class SimedComponent extends ERXComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VISIBILITY_HIDDEN = "Hidden";
	public static final String VISIBILITY_VISIBLE = "Visible";

	public static Logger log = Logger.getLogger(SimedComponent.class);

	private ERXJodaLocalDateFormatter _myLocalDateFormatter;
	private ERXJodaLocalDateFormatter _myStandardLocalDateFormatter;

	private ERXJodaLocalDateFormatter _myLocalDateMonthFormatter;
	private ERXJodaDateTimeFormatter _myDateTimeFormatter;
	private ERXJodaLocalDateTimeFormatter _myLocalDateTimeFormatter;
	private ERXJodaLocalDateTimeFormatter _myLocalDateTimeFormatterWithLocale;

	private EditionMode _mode;

	public SimedComponent(WOContext context) {
		super(context);
		this.setMode(EditionMode.READ);
	}
	
    // pegn add security  here
	@Override
	protected void checkAccess() throws SecurityException {
		ERXRequest request = (ERXRequest) context().request();
//		System.err.println("\n" + request.method());
//
//		for (String key:request.formValueKeys())
//		{
//			System.err.println("formValue : " + key + " / " + request.formValueForKey(key));
//		}

		if (request._hasFormValues())
		{
			// crsf wrong
//			String wosid = null;
//			if (!isAjaxRequest(request)) // from form values
//			{
//				wosid = (String) request.formValueForKey("wosid");
//			}
//			else // ajax - use value set in header from js
//			{
//				wosid = request.headerForKey("csrftoken");
//			}
//			if (wosid == null) 
//			{
//				throw new SecurityException("XSS attack !");				
//			}
//			if (!session().sessionID().equals(wosid))
//			{
//				throw new SecurityException("CSRF attack !");
//			}  
			
			for (String key:request.formValueKeys())
			{
					String formValue = (String) request.formValueForKey(key);
					if (formValue != null)
					{
						// check here XSS
						formValue = formValue.replaceAll("%", "°/o");

						try {
							SafeHTMLValidator.validate(URLDecoder.decode(formValue,"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							throw new SecurityException(e.getCause());
						}

					}
			}



		}
		super.checkAccess();
	}
	
	
	/**
	 * @return the myLocalDateFormatter
	 */
	public ERXJodaLocalDateFormatter myLocalDateFormatter() {
		if (this._myLocalDateFormatter == null) {
			this._myLocalDateFormatter = new ERXJodaLocalDateFormatter("yyyy-MM-dd");
		}
		return this._myLocalDateFormatter;
	}





	public ERXJodaLocalDateFormatter myStandardLocalDateFormatter() {
		if (this._myStandardLocalDateFormatter == null) {
			this._myStandardLocalDateFormatter = new ERXJodaLocalDateFormatter("dd.MM.yyyy");
		}
		return this._myStandardLocalDateFormatter;
	}
	
	public ERXJodaLocalDateFormatter myLocalDateMonthFormatter() {
		if (this._myLocalDateMonthFormatter == null) {
			this._myLocalDateMonthFormatter = new ERXJodaLocalDateFormatter("yyyy-MM");
		}
		return this._myLocalDateMonthFormatter;
	}
	

	/**
	 * @return the myLocalDateFormatter
	 */
	public ERXJodaDateTimeFormatter myDateTimeFormatter() {
		if (this._myDateTimeFormatter == null) {
			this._myDateTimeFormatter = new ERXJodaDateTimeFormatter("yyyy-MM-dd");
		}
		return this._myDateTimeFormatter;
	}

	/**
	 * @return the myLocalDateTimeFormatter
	 */
	public ERXJodaLocalDateTimeFormatter myLocalDateTimeFormatter() {
		if (this._myLocalDateTimeFormatter == null) {
			this._myLocalDateTimeFormatter = new ERXJodaLocalDateTimeFormatter("yyyy-MM-dd");
		}
		return this._myLocalDateTimeFormatter;
	}

	/**
	 * @return the myLocalDateTimeFormatter
	 */
	public ERXJodaLocalDateTimeFormatter myLocalDateTimeFormatterWithLocale() {
		if (this._myLocalDateTimeFormatterWithLocale == null) {
			this._myLocalDateTimeFormatterWithLocale = new ERXJodaLocalDateTimeFormatter(new Locale("fr", "ch"), "S-");
		}
		return this._myLocalDateTimeFormatterWithLocale;
	}

	/**
	 * @return the mode
	 */
	public EditionMode mode() {
		return this._mode;
	}

	/**
	 * @param newMode
	 *            set the mode
	 */
	public void setMode(EditionMode newMode) {
		this._mode = newMode;
	}

	public boolean isReadMode() {
		return this.mode().equals(EditionMode.READ);
	}

	public boolean isEdidMode() {
		return this.mode().equals(EditionMode.EDIT);
	}

	public boolean isCreateMode() {
		return this.mode().equals(EditionMode.CREATE);
	}

	public boolean isfalse() {
		return false;
	}

	public boolean istrue() {
		return true;
	}
	// pegn security
	private  boolean isAjaxRequest(ERXRequest request) {
		String requestedWith = request.headerForKey("x-requested-with");
		return "XMLHttpRequest".equals(requestedWith);
	}
	

}
