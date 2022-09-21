/**
 * 
 */
package ch.hcuge.simed.foundation.utilities.validator;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.joda.time.base.AbstractPartial;

import ch.hcuge.simed.foundation.eo.SimedEnterpriseObject;
import er.extensions.eof.ERXEnterpriseObject;

/**
 * @author dban
 * 
 */
public class RangeValidator extends AbstractValidator implements ValidatorInterface {

	public static final String KEY = Validator.RANGE_VALIDATOR;
	public static Logger log = Logger.getLogger(RangeValidator.class);

	private RangeValidator() {
		// NO direct access
	}

	public static AbstractValidator getInstance() {
		return RangeValidatorHolder.INSTANCE;
	}

	@Override
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level) {
		// TODO Auto-generated method stub
		ValidationProblem pb = null;
		String attributeName = rule.getAttributeName();
		HashMap<Object, Object> constraintMap = SimedEnterpriseObject.convertHashMap((SimedEnterpriseObject) eo,
				rule.getConstraint(), true);
		if (!validate(eo.valueForKey(attributeName), rule.getValidatorName(), constraintMap)) {
			pb = new ValidationProblem(key, "'" + attributeName + "' must respect '" + constraintMap + "'",
					ValidationProblem.WARN);
		}
		return pb;
	}

	public static boolean validate(Object value, String validator, HashMap<Object, Object> constraint) {
		RangeValidator v = RangeValidatorHolder.INSTANCE;
		v.checkConstraint(constraint);
		return v.isValid(value, constraint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hcuge.simed.foundation.utilities.validator.Validator#isValid(java.
	 * lang.Object, java.util.HashMap)
	 */
	@Override
	public boolean isValid(Object value, HashMap<Object, Object> constraints) {
		if (value == null) {
			return true;
		}
		if (value instanceof AbstractPartial) {
//	wtf
			return JodaDateRangeValidator.isValid((AbstractPartial) value, constraints);
//			return true;
		} else if (value instanceof Number) {
			return NumberRangeValidator.isValid((Number) value, constraints);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.hcuge.simed.foundation.utilities.validator.Validator#checkConstraint()
	 */
	@Override
	public void checkConstraint(HashMap<Object, Object> constraints) throws InvalidParameterException {
		HashSet<Object> operators = new HashSet<Object>(constraints.keySet());
		if (!ValidatorUtlities.rangeOperatorsAreValid(operators)) {
			throw new InvalidParameterException("one of the operator for the constraint is invalid");
		}
	}

	private static class RangeValidatorHolder {
		private final static RangeValidator INSTANCE = new RangeValidator();
	}

}
