package ch.hcuge.simed.foundation.utilities.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eoaccess.ERXEOAccessHelper;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXEOAccessUtilities;
import er.extensions.eof.ERXEnterpriseObject;

import ch.hcuge.simed.foundation.extendableenum.ExtendableEnum;

public class ConditionalNotNull extends AbstractValidator {

	public static final String KEY = Validator.CONDITIONAL_NOT_NULL_VALIDATOR;

	private ConditionalNotNull() {
		// NO Implemetation
	}

	@Override
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level) {
		ValidationProblem result = null;
		String attributeName = rule.getAttributeName();
		Object o = eo.valueForKey(attributeName);
		if (o == null) {
			result = new ValidationProblem(key, "The field '" + attributeName + "' must not be null",
					ValidationProblem.WARN);
		}
		return result;
	}

	public static AbstractValidator getInstance() {
		return ConditionalNotNullHolder.INSTANCE;
	}

	private static class ConditionalNotNullHolder {
		private final static ConditionalNotNull INSTANCE = new ConditionalNotNull();
	}

}
