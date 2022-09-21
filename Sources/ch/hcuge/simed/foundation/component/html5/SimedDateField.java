package ch.hcuge.simed.foundation.component.html5;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestampFormatter;

import er.extensions.appserver.ERXSession;
import er.extensions.components.html5.ERXWOInput;
import er.extensions.formatters.ERXNumberFormatter;
import er.extensions.formatters.ERXTimestampFormatter;
import er.extensions.validation.ERXValidationException;

/**
 * 
 * @author dban
 * 
 *         based on ERXWONumberField by ken ishimoto ADDED takeValuesFromRequest
 *         for formatter WOAssociation from ERXWOTextField
 */
@SuppressWarnings("deprecation")
public class SimedDateField extends ERXWOInput {

	public static Logger log = Logger.getLogger(SimedDateField.class);

	// ********************************************************************
	// Binding Properties
	// ********************************************************************

	protected WOAssociation _min;
	protected WOAssociation _max;
	protected WOAssociation _step;
	protected WOAssociation _autofocus;

	protected WOAssociation _formatter;
	protected WOAssociation _dateFormat;
	protected WOAssociation _numberFormat;
	protected WOAssociation _useDecimalNumber;

	// ********************************************************************
	// Constructor
	// ********************************************************************

	public SimedDateField(String tagname, NSDictionary<String, WOAssociation> nsdictionary, WOElement woelement) {
		super("input", nsdictionary, woelement);

		_min = _associations.removeObjectForKey("min");
		_max = _associations.removeObjectForKey("max");
		_step = _associations.removeObjectForKey("step");

		_formatter = _associations.removeObjectForKey("formatter");
		_dateFormat = _associations.removeObjectForKey("dateformat");
		_numberFormat = _associations.removeObjectForKey("numberformat");
		_useDecimalNumber = _associations.removeObjectForKey("useDecimalNumber");

		_autofocus = _associations.removeObjectForKey("autofocus");

		// WOAssociation formatter =
		// _associations.removeObjectForKey("formatter");
		// log.warn("[SIMED]{" + this.getClass().getName() +
		// "} <SimedDateField> formatter class is: '" +
		// formatter.getClass().getName() + "'.");
	}

	@Override
	public String type() {
		return "date";
	}

	protected boolean isAutofocusInContext(WOContext context) {
		return _autofocus != null && _autofocus.booleanValueInComponent(context.component());
	}

	@Override
	public void takeValuesFromRequest(WORequest worequest, WOContext wocontext) {
		WOComponent component = wocontext.component();
		if (!isDisabledInContext(wocontext) && wocontext.wasFormSubmitted() && !isReadonlyInContext(wocontext)) {
			String name = nameInContext(wocontext, component);
			if (name != null) {
				String stringValue;
				boolean blankIsNull = _blankIsNull == null || _blankIsNull.booleanValueInComponent(component);
				if (blankIsNull) {
					stringValue = worequest.stringFormValueForKey(name);
				} else {
					Object objValue = worequest.formValueForKey(name);
					stringValue = (objValue == null) ? null : objValue.toString();
				}
				Object result = stringValue;
				if (stringValue != null) {
					Format format = null;
					boolean hasFormatter = false;
					if (stringValue.length() != 0) {
						if (_formatter != null) {
							format = (Format) _formatter.valueInComponent(component);
						}
						if (format == null) {
							if (_dateFormat != null) {
								String formatString = (String) _dateFormat.valueInComponent(component);
								if (formatString != null) {
									format = ERXTimestampFormatter.dateFormatterForPattern(formatString);
								}
							} else if (_numberFormat != null) {
								String formatString = (String) _numberFormat.valueInComponent(component);
								if (formatString != null) {
									format = ERXNumberFormatter.numberFormatterForPattern(formatString);
								}
							}
						} else {
							hasFormatter = true;
						}
					}
					if (format != null) {
						if (ERXSession.autoAdjustTimeZone() && !hasFormatter && format instanceof NSTimestampFormatter && wocontext.hasSession()
								&& ERXSession.class.isAssignableFrom(wocontext.session().getClass())) {

							synchronized (format) {
								ERXSession session = (ERXSession) wocontext.session();
								NSTimeZone zone = NSTimeZone._nstimeZoneWithTimeZone(session.timeZone());
								NSTimestampFormatter tsFormat = (NSTimestampFormatter) format;
								NSTimeZone parseZone = tsFormat.defaultParseTimeZone();
								NSTimeZone formatZone = tsFormat.defaultFormatTimeZone();
								tsFormat.setDefaultFormatTimeZone(zone);
								tsFormat.setDefaultParseTimeZone(zone);
								try {
									Object parsedObject = format.parseObject(stringValue);
									String reformatedObject = format.format(parsedObject);
									result = format.parseObject(reformatedObject);
								} catch (ParseException parseexception) {
									String keyPath = _value.keyPath();
									ERXValidationException validationexception = new ERXValidationException(ERXValidationException.InvalidValueException,
											parseexception, keyPath, stringValue);
									component.validationFailedWithException(validationexception, stringValue, keyPath);
									return;
								} finally {
									tsFormat.setDefaultFormatTimeZone(formatZone);
									tsFormat.setDefaultParseTimeZone(parseZone);
								}
							}
						} else {
							try {
								Object parsedObject = format.parseObject(stringValue);
								String reformatedObject = format.format(parsedObject);
								result = format.parseObject(reformatedObject);
							} catch (ParseException parseexception) {
								String keyPath = _value.keyPath();
								ERXValidationException validationexception = new ERXValidationException(ERXValidationException.InvalidValueException,
										parseexception, keyPath, stringValue);
								component.validationFailedWithException(validationexception, stringValue, keyPath);
								return;
							}
						}
						if (result != null && _useDecimalNumber != null && _useDecimalNumber.booleanValueInComponent(component)) {
							result = new BigDecimal(result.toString());
						}
					} else if (blankIsNull && result.toString().length() == 0) {
						result = null;
					}
				}
				_value.setValue(result, component);
			}
		}
	}

	@Override
	protected void _appendValueAttributeToResponse(WOResponse woresponse, WOContext wocontext) {
		WOComponent component = wocontext.component();

		Object valueInComponent = _value.valueInComponent(component);
		if (valueInComponent != null) {
			String stringValue = valueInComponent.toString();
			woresponse._appendTagAttributeAndValue("value", stringValue, true);
		}

		if (_min != null) {
			Object minInComponent = _min.valueInComponent(component);
			if (minInComponent != null) {
				String stringValue = minInComponent.toString();
				woresponse._appendTagAttributeAndValue("min", stringValue, true);
			}
		}

		if (_max != null) {
			Object maxInComponent = _max.valueInComponent(component);
			if (maxInComponent != null) {
				String stringValue = maxInComponent.toString();
				woresponse._appendTagAttributeAndValue("max", stringValue, true);
			}
		}

		if (_step != null) {
			Object stepInComponent = _step.valueInComponent(component);
			if (stepInComponent != null) {
				String stringValue = stepInComponent.toString();
				woresponse._appendTagAttributeAndValue("step", stringValue, true);
			}
		}

		if (isAutofocusInContext(wocontext)) {
			woresponse._appendTagAttributeAndValue("autofocus", "autofocus", false);
		}

		if (isRequiredInContext(wocontext)) {
			woresponse._appendTagAttributeAndValue("required", "required", false);
		}

		if (isReadonlyInContext(wocontext)) {
			woresponse._appendTagAttributeAndValue("readonly", "readonly", false);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringbuffer = new StringBuilder();
		stringbuffer.append('<');
		stringbuffer.append(getClass().getName());
		stringbuffer.append(" min=");
		stringbuffer.append(_min);
		stringbuffer.append(" max=");
		stringbuffer.append(_max);
		stringbuffer.append(" step=");
		stringbuffer.append(_step);
		stringbuffer.append('>');
		return stringbuffer.toString();
	}
}