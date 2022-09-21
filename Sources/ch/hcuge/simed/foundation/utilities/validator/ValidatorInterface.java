package ch.hcuge.simed.foundation.utilities.validator;

import java.security.InvalidParameterException;
import java.util.HashMap;

public interface ValidatorInterface {

	public boolean isValid(Object value, HashMap<Object, Object> constraints);

	public void checkConstraint(HashMap<Object, Object> constraints) throws InvalidParameterException;
}
