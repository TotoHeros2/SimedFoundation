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

public class OtherValidator extends AbstractValidator {

	public static final String KEY = Validator.OTHER_VALIDATOR;

	private OtherValidator() {
		// NO Implemetation
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level) {
		ValidationProblem result = null;
		String attributeName = rule.getAttributeName();
		String otherAttributeName = (String) rule.getConstraint().get("name");
		EOEntity entity = EOUtilities.entityForObject(eo.editingContext(), eo);
		EOAttribute attr = entity.attributeNamed(attributeName);
		Object popupValue = eo.valueForKey(attributeName);
		String otherFieldValue = (String) eo.valueForKey(otherAttributeName);
		Class<?>[] param = null;
		String valueTypeClassName = attr.valueTypeClassName();
		try {
			Class attrClass = Class.forName(valueTypeClassName);
			Method method = attrClass.getMethod("othersList", param);
			NSArray<? extends ExtendableEnum> otherList = (NSArray<? extends ExtendableEnum>) method.invoke(attrClass, param);
			// pegn gui shit bug
			if (popupValue == null) return result;
			if (otherList.contains(popupValue) && (otherFieldValue == null || otherFieldValue.trim().length() == 0)) {
				result = new ValidationProblem(key, "There is NO data in '" + otherAttributeName + "' while '" + attributeName + "' demands an Other value", ValidationProblem.INFO);
			}
			if ((!otherList.contains(popupValue)) && otherFieldValue != null && otherFieldValue.trim().length() > 0) {
				result = new ValidationProblem(key, "There is data in '" + otherAttributeName + "' while '" + attributeName + "' doesn't demands an Other value", ValidationProblem.CLEANUP);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
			log.error("Pb checkForProblem with key: '" + key + "'.", e);
		}
		return result;

	}

	public static AbstractValidator getInstance() {
		return OtherValidatorHolder.INSTANCE;
	}

	private static class OtherValidatorHolder {
		private final static OtherValidator INSTANCE = new OtherValidator();
	}

}
