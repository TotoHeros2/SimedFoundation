package ch.hcuge.simed.foundation.utilities.validator;

import er.extensions.eof.ERXEnterpriseObject;

public class ConditionalUnansweredValidator extends AbstractValidator {

	public static final String KEY = Validator.CONDITIONAL_UNANSWERED_VALIDATOR;

	private ConditionalUnansweredValidator() {
		// NO Implemetation
	}

	@Override
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level) {
		ValidationProblem result = null;
		String attributeName = rule.getAttributeName();
		String objectValue = (String)eo.valueForKey(attributeName);
		if(objectValue == null || objectValue.trim().length() == 0){
			result = new ValidationProblem(key, "There is NO data in '" + attributeName + "'.", ValidationProblem.INFO);
		}
		return result;
	}

	public static AbstractValidator getInstance() {
		return ConditionalUnansweredValidatorHolder.INSTANCE;
	}

	private static class ConditionalUnansweredValidatorHolder {
		private final static ConditionalUnansweredValidator INSTANCE = new ConditionalUnansweredValidator();
	}

}
