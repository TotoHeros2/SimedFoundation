package ch.hcuge.simed.foundation.utilities.validator;

import java.util.Set;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSArray;

public class ValidatorUtlities {

	public static Logger log = Logger.getLogger(ValidatorUtlities.class);

	private static final NSArray<String> RANGE_OPERATOR = new NSArray<String>("gt", "gte", "lt", "lte");
	private static final NSArray<String> OTHER_OPERATOR = new NSArray<String>("name");

	public static boolean rangeOperatorsAreValid(Set<Object> operators) {
		for (Object operator : operators) {
			if (!RANGE_OPERATOR.contains(operator)) {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.ValidatorUtlities} <rangeOperatorsAreValid> operator: '" + operator
						+ "' is not in [" + RANGE_OPERATOR.componentsJoinedByString(",") + "].");
				return false;
			}
		}
		return true;
	}

	public static boolean otherOperatorsAreValid(Set<Object> operators) {
		for (Object operator : operators) {
			if (!OTHER_OPERATOR.contains(operator)) {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.ValidatorUtlities} <otherOperatorsAreValid> operator: '" + operator
						+ "' is not in [" + OTHER_OPERATOR.componentsJoinedByString(",") + "].");
				return false;
			}
		}
		return true;
	}

}
