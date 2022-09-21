package ch.hcuge.simed.foundation.utilities.validator;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXEnterpriseObject;

public abstract class AbstractValidator {
	
	protected static Logger log = Logger.getLogger(AbstractValidator.class);
	
	//public abstract AbstractValidator getInstance();
	
	public abstract ValidationProblem checkForProblem(ERXEnterpriseObject eo, String key, Validator rule, Integer level);

}
