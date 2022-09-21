package ch.hcuge.simed.foundation.utilities.validator;

import java.util.ArrayList;
import java.util.HashMap;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

public class Validator {

	public static final String RANGE_VALIDATOR = "RangeValidator";
	public static final String UNANSWERED_VALIDATOR = "UnansweredValidator";
	public static final String OTHER_VALIDATOR = "OtherValidator";
	public static final String CONDITIONAL_UNANSWERED_VALIDATOR = "ConditionalUnansweredValidator";
	public static final String CONDITIONAL_NOT_NULL_VALIDATOR = "ConditionalNotNull";

	public static final NSArray<String> KNOWN_VALIDATORS = new NSArray<String>(RANGE_VALIDATOR, UNANSWERED_VALIDATOR, OTHER_VALIDATOR,
			CONDITIONAL_UNANSWERED_VALIDATOR, CONDITIONAL_NOT_NULL_VALIDATOR);

	private String _validatorName;
	private String _attributeName;
	private ArrayList<HashMap<Object, Object>> _constraints = new ArrayList<HashMap<Object, Object>>();
	private HashMap<String, ArrayList<Object>> _dependMap;

	public Validator(String validatorName, String attributeName) {
		super();
		this._validatorName = validatorName;
		this._attributeName = attributeName;
	}

	public ArrayList<HashMap<Object, Object>> getConstraints() {
		return this._constraints;
	}

	public void setConstraints(ArrayList<HashMap<Object, Object>> newConstraints) {
		this._constraints = newConstraints;
	}

	public void addConstraint(HashMap<Object, Object> aConstraint) {
		_constraints.add(aConstraint);
	}
	
	public HashMap<Object, Object>getConstraint() {
		return getConstraints().get(0);
	}

	public HashMap<String, ArrayList<Object>> getDependency() {
		return this._dependMap;
	}

	public void setDependency(HashMap<String, ArrayList<Object>> newDependency) {
		this._dependMap = newDependency;
	}

	public String getValidatorName() {
		return this._validatorName;
	}

	public void setValidatorName(String validatorName) {
		this._validatorName = validatorName;
	}

	public String getAttributeName() {
		return this._attributeName;
	}

	public void setAttributeName(String attributeName) {
		this._attributeName = attributeName;
	}

	/**
	 * @return alias method for getValidatorName
	 */
	public String getType() {
		return getValidatorName();
	}

	public boolean isKnownValidator() {
		return KNOWN_VALIDATORS.contains(getValidatorName());
	}

	public boolean isRangeValidator() {
		return getValidatorName().equals(RANGE_VALIDATOR);
	}

	public boolean isUnansweredValidator() {
		return getValidatorName().equals(UNANSWERED_VALIDATOR);
	}

	public boolean isOtherValidator() {
		return getValidatorName().equals(OTHER_VALIDATOR);
	}

	public boolean isConditionalUnansweredValidator() {
		return getValidatorName().equals(CONDITIONAL_UNANSWERED_VALIDATOR);
	}

	public boolean isConditionalNotNull() {
		return getValidatorName().equals(CONDITIONAL_NOT_NULL_VALIDATOR);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\n");
		sb.append("\t'name': '").append(getValidatorName()).append("', \n");
		sb.append("\t'constraint': (\n");
		for (HashMap<Object, Object> constraint : getConstraints()) {
			sb.append("\t\t").append(constraint.toString()).append(",\n");
		}
		sb.append("\t)\n");
		sb.append("\t'dependency': '");
		if (getDependency() != null) {
			sb.append(getDependency().toString());
		} else {
			sb.append("No dependency");
		}
		sb.append("',\n");
		sb.append("}").append("\n");
		return sb.toString();
	}

	private static NSDictionary<String, Integer> _errorLevelDict =null;
	
	private static NSDictionary<String, Integer> ErrorLevelDict() {
		if(_errorLevelDict == null) {
			NSMutableDictionary<String, Integer> tmp = new NSMutableDictionary<String, Integer>();
			tmp.setObjectForKey(ValidationProblem.ERROR,"NULL");
			tmp.setObjectForKey(ValidationProblem.ERROR,"RangeValidator");
			tmp.setObjectForKey(ValidationProblem.WARN,"UnansweredValidator");
			tmp.setObjectForKey(ValidationProblem.ERROR,"OtherValidator");
			tmp.setObjectForKey(ValidationProblem.WARN,"ConditionalUnansweredValidator");
			tmp.setObjectForKey(ValidationProblem.ERROR,"ConditionalNotNull");
			_errorLevelDict=tmp.immutableClone();
		}
		return _errorLevelDict;
	}
	
	public static Integer ErrorLevel(String key){
		return (Integer)ErrorLevelDict().valueForKey(key);
	}

}
