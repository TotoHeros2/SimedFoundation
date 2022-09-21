package ch.hcuge.simed.foundation.utilities.validator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class NumberRangeValidator {

	public static Logger log = Logger.getLogger(NumberRangeValidator.class);

	public static boolean isValid(Number value, HashMap<Object, Object> constraints) {
		ArrayList<Object> ks = new ArrayList<Object>(constraints.keySet());
		if (ks.size() == 2) {
			String key1 = (String) ks.get(0);
			Number value1 = (Number) constraints.get(key1);
			String key2 = (String) ks.get(1);
			Number value2 = (Number) constraints.get(key2);
			if ((key1.equalsIgnoreCase("lt") || key1.equalsIgnoreCase("lte")) && (key2.equalsIgnoreCase("gt") || key2.equalsIgnoreCase("gte"))) {
//				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <isValid> key 1 = lt " + value2 + ">" + value + ">"
//						+ value1);
				boolean part1;
				boolean part2;
				if (key1.equalsIgnoreCase("lt")) {
					part1 = value.doubleValue() > value1.doubleValue();
				} else {
					part1 = value.doubleValue() >= value1.doubleValue();
				}
				if (key1.equalsIgnoreCase("gt")) {
					part2 = value.doubleValue() < value2.doubleValue();
				} else {
					part2 = value.doubleValue() <= value2.doubleValue();
				}
				return part1 && part2;
			} else if ((key1.equalsIgnoreCase("gt") || key1.equalsIgnoreCase("gte")) && (key2.equalsIgnoreCase("lt") || key2.equalsIgnoreCase("lte"))) {
//				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <isValid> key1 = gt " + value1 + ">" + value + ">"
//						+ value2);
				boolean part1;
				boolean part2;
				if (key1.equalsIgnoreCase("lt")) {
					part1 = value.doubleValue() > value1.doubleValue();
				} else {
					part1 = value.doubleValue() >= value1.doubleValue();
				}
				if (key1.equalsIgnoreCase("gt")) {
					part2 = value.doubleValue() < value2.doubleValue();
				} else {
					part2 = value.doubleValue() <= value2.doubleValue();
				}
				return part1 && part2;
			} else {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.RangeValidator.NumberRangeValidator} <isValid> There is an inconstency in the operator");
				return false;
			}
		} else if (ks.size() == 1) {
			String key1 = (String) ks.get(0);
			Number value1 = (Number) constraints.get(key1);
			if (key1.equalsIgnoreCase("lt")) {
				return value1.doubleValue() > value.doubleValue();
			} else if (key1.equalsIgnoreCase("lte")) {
				return value1.doubleValue() >= value.doubleValue();
			} else if (key1.equalsIgnoreCase("gt")) {
				return value1.doubleValue() < value.doubleValue();
			} else if (key1.equalsIgnoreCase("gte")) {
				return value1.doubleValue() <= value.doubleValue();
			} else {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.RangeValidator.NumberRangeValidator} <isValid> There is an inconstency in the operator");
				return false;
			}
		} else {
			log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <isValid> the is. '"
					+ constraints.keySet().size() + "' instead of one or two.");
			return false;
		}
	}

	public static void main(String[] args) {

		Number loweBound = new Double(0);
		Number higherBound = new Integer(100);

		HashMap<Object, Object> constraintMap = new HashMap<Object, Object>();

		//Different type de number
		constraintMap.put("gte", loweBound);
		constraintMap.put("lte", higherBound);
		Number testNum = new Float(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new AtomicInteger(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new AtomicLong(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new BigInteger("50");
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Byte("50");
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Integer(50);
		constraintMap.put("gte", loweBound);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Short("50");
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		//bound test for 2 boundary et gte lte
		testNum = new Integer(0);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Integer(100);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(-1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		testNum = new Double(101);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		//bound test for 2 boundary et gt lt
		testNum = new Integer(0);
		constraintMap.clear();
		constraintMap.put("lt", higherBound);
		constraintMap.put("gt", loweBound);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Integer(100);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));


		//bound test for 1 boundary lte
		constraintMap.clear();
		constraintMap.put("lte", higherBound);
		testNum = new Double(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(100);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(101);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		//bound test for 1 boundary lt
		constraintMap.clear();
		constraintMap.put("lt", higherBound);
		testNum = new Double(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(100);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		testNum = new Double(101);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		testNum = new Integer(100);
		constraintMap.put("gte", loweBound);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		//bound test for 1 boundary gte
		constraintMap.clear();
		constraintMap.put("gte", loweBound);
		testNum = new Double(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(0);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(-1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		//bound test for 1 boundary gt
		constraintMap.clear();
		constraintMap.put("gt", loweBound);
		testNum = new Double(50);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testNum, constraintMap));

		testNum = new Double(0);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

		testNum = new Double(-1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.NumberRangeValidator} <main> for " + testNum + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testNum, constraintMap));

	}
}
