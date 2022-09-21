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

public class UnansweredValidator extends AbstractValidator {

	public static final String KEY = Validator.UNANSWERED_VALIDATOR;

	private UnansweredValidator() {
		// NO Implemetation
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level) {
		ValidationProblem result = null;
		String attributeName = rule.getAttributeName();
		EOEntity entity = EOUtilities.entityForObject(eo.editingContext(), eo);
		EOAttribute attr = entity.attributeNamed(attributeName);
		String valueTypeClassName = attr.valueTypeClassName();
		Object value = eo.valueForKey(attributeName);
		Class<?>[] param = null;
		try {
			Class attrClass = Class.forName(valueTypeClassName );
			// year are integers !!
			/* No more as done in oe Commorbidity
			if (attrClass == Integer.class)
			{
				if (value == null)
				{
					return new ValidationProblem(key, "The field '" + attributeName + "' must be set",ValidationProblem.INFO);
				}
				else
				{
					return null;
				}
			}
			*/
			
			
			
			Method method = attrClass.getMethod("unansweredList", param);
			NSArray<? extends ExtendableEnum> list = (NSArray<? extends ExtendableEnum>) method.invoke(attrClass,
					param);
			//pegn remove null value
			if (value != null && list.contains(value)) {
				result = new ValidationProblem(key, "The field '" + attributeName + "' must be set",
						ValidationProblem.INFO);
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException|ClassNotFoundException e) {
			log.error("Pb checkForProblem with key: '" + key + "'.", e);
		}
		return result;
	}

	public static AbstractValidator getInstance() {
		return UnansweredValidatorHolder.INSTANCE;
	}

	private static class UnansweredValidatorHolder {
		private final static UnansweredValidator INSTANCE = new UnansweredValidator();
	}

}
