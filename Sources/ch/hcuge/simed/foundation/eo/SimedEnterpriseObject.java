package ch.hcuge.simed.foundation.eo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.LocalDate;

import com.webobjects.foundation.NSTimestamp;

import ch.hcuge.simed.foundation.extendableenum.ExtendableEnum;
import ch.hcuge.simed.foundation.utilities.validator.ValidationProblemList;
import er.extensions.eof.ERXGenericRecord;
import er.extensions.foundation.ERXValueUtilities;

public class SimedEnterpriseObject extends ERXGenericRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ValidationProblemList _problems;

	//
	public ValidationProblemList problems() {
		if (this._problems == null) {
			this._problems = new ValidationProblemList();
		}
		return this._problems;
	}
	
	private ValidationProblemList _filteredProblems;
	public  ValidationProblemList filteredProblems(){
		if(_filteredProblems == null){
			this._filteredProblems = new ValidationProblemList();
			this._filteredProblems.addObjectsFromArray(problems().errors());
			this._filteredProblems.addObjectsFromArray(problems().warnings());
		}
		return this._filteredProblems;
	}

	public void clearProblems() {
		this._problems = null;
		this._filteredProblems = null;
	}

	public static HashMap<Object, Object> convertHashMap(SimedEnterpriseObject eo, HashMap<Object, Object> constraint,
			boolean convertMap) {
		if (convertMap) {
			HashMap<Object, Object> convertedConstraint = new HashMap<Object, Object>();
			HashSet<Object> keys = new HashSet<Object>(constraint.keySet());
			for (Object key : keys) {
				String value = (String) constraint.get(key);
				if (NumberUtils.isNumber(value)) {
					convertedConstraint.put(key, NumberUtils.createNumber(value));
				} else if (value.equals("TODAY")) {
					convertedConstraint.put(key, LocalDate.now());
				} else if (value.equals("TODAYTS")) {
					convertedConstraint.put(key, new NSTimestamp());
				} else {
					convertedConstraint.put(key, eo.valueForKeyPath(value));
				}
			}
			return convertedConstraint;
		} else

		{
			return constraint;
		}
	}

	public static boolean respectDependency(SimedEnterpriseObject eo, HashMap<String, ArrayList<Object>> dependMap) {
		if (dependMap == null || dependMap.size() == 0) {
			return true;
		} else {
			ArrayList<String> keys = new ArrayList<String>(dependMap.keySet());
			ArrayList<Boolean> results = new ArrayList<Boolean>();
			for (String key : keys) {
				boolean result = false;
				Object value = eo.valueForKeyPath(key);
				ArrayList<Object> dependencies = new ArrayList<Object>(dependMap.get(key));
				for (Object dependency : dependencies) {
					if (dependency instanceof ExtendableEnum) {
						if (value.equals(dependency)) { // no need to change ?
							result = true;
							break;
						}
					} else if (dependency instanceof String) {
						Boolean booleanValue;
						Double doubleValue;
						boolean notTreated = true;
						// parse string as number
						try {
							doubleValue = ERXValueUtilities.DoubleValueWithDefault(dependency, null);
							if (doubleValue != null) {
								notTreated = false;
								if (doubleValue.equals(value)) { // pegn
									result = true;
									break;
								}
							}
						} catch (Exception e) {
							// Not really an exception
						}
						// Parse string as boolean
						if (notTreated) {
							try {
								booleanValue = ERXValueUtilities.BooleanValueWithDefault(dependency, null);
								if (booleanValue != null) {
									notTreated = false;
									// TODO
									//to do aussi sur les autres types - Was dependency instead of value
									if (booleanValue.equals(value)) {
										result = true;
										break;
									}
								}
							} catch (Exception e) {
								// Not really an exception
							}
						}
						// use string as string
						if (notTreated) {
							if (((String) value).equalsIgnoreCase((String) dependency)) {
								result = true;
								break;
							}
						}
					} else {
						log.warn(
								"[SIMED]{ch.hcuge.simed.foundation.eo.SimedEnterpriseObject} <respectDependecy> unkown class of type: '"
										+ dependency.getClass().getName() + "' treated as object");
						if (value.equals(dependency)) {
							result = true;
							break;
						}
					}
				}
				results.add(Boolean.valueOf(result));
			}
			boolean result = true;
			for (Boolean partialResult : results) {
				result = result && partialResult.booleanValue();
			}
			// Object realValue = eo.valueForKey(key)
			return result;
		}
	}
}
