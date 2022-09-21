package ch.hcuge.simed.foundation.utilities.validator;

import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXEnterpriseObject;

public interface ValidatorNewInterface {
	
	public ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, NSDictionary<String, Object> validationRule);

}
