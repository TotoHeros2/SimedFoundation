package ch.hcuge.simed.foundation.utilities.validator;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
//import org.joda.time.LocalDate;
//import org.joda.time.base.AbstractPartial;
import org.joda.time.base.AbstractPartial;

public class JodaDateRangeValidator {

	public static Logger log = Logger.getLogger(JodaDateRangeValidator.class);

	public static boolean isValid(AbstractPartial value, HashMap<Object, Object> constraints) {
		//if (true) return true;
		ArrayList<Object> ks = new ArrayList<Object>(constraints.keySet());
		if (ks.size() == 2) {
			String key1 = (String) ks.get(0);
			AbstractPartial value1 = (AbstractPartial) constraints.get(key1);
			String key2 = (String) ks.get(1);
			AbstractPartial value2 = (AbstractPartial) constraints.get(key2);
			if ((key1.equalsIgnoreCase("lt") || key1.equalsIgnoreCase("lte")) && (key2.equalsIgnoreCase("gt") || key2.equalsIgnoreCase("gte"))) {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <isValid> key 1 = lt " + value2 + ">" + value + ">"
						+ value1);
				return value1.isAfter(value) && value2.isBefore(value);
			} else if ((key1.equalsIgnoreCase("gt") || key1.equalsIgnoreCase("gte")) && (key2.equalsIgnoreCase("lt") || key2.equalsIgnoreCase("lte"))) {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <isValid> key1 = gt " + value1 + ">" + value + ">"
						+ value2);
				return value2.isAfter(value) && value1.isBefore(value);
			} else {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.RangeValidator.JodaDateRangeValidator} <isValid> There is an inconstency in the operator");
				return false;
			}
		} else if (ks.size() == 1) {
			String key1 = (String) ks.get(0);
			AbstractPartial value1 = (AbstractPartial) constraints.get(key1);
			if (key1.equalsIgnoreCase("lt") || key1.equalsIgnoreCase("lte")) {
				return value1.isAfter(value);
			} else if (key1.equalsIgnoreCase("gt") || key1.equalsIgnoreCase("gte")) {
				return value1.isBefore(value);
			} else {
				log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.RangeValidator.JodaDateRangeValidator} <isValid> There is an inconstency in the operator");
				return false;
			}
		} else {
			log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.RangeValidator.JodaDateRangeValidator} <isValid> the is. '"
					+ constraints.keySet().size() + "instead of one or two.");
			return false;
		}
	}

	public static void main(String[] args) {

		LocalDate birthdate = new LocalDate(1961, 4, 16);
		LocalDate dateOfDignosis = new LocalDate(2000, 1, 1);

		HashMap<Object, Object> constraintMap = new HashMap<Object, Object>();

		constraintMap.put("gte", birthdate);
		constraintMap.put("lte", dateOfDignosis);
		LocalDate testDate = new LocalDate(1973, 6, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testDate, constraintMap));

		testDate = new LocalDate(1973, 6, 1);
		constraintMap.clear();
		constraintMap.put("lte", dateOfDignosis);
		constraintMap.put("gte", birthdate);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testDate, constraintMap));

		testDate = new LocalDate(1961, 01, 01);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testDate, constraintMap));

		testDate = new LocalDate(2016, 01, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testDate, constraintMap));

		constraintMap.clear();
		constraintMap.put("gte", birthdate);
		testDate = new LocalDate(1973, 6, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testDate, constraintMap));

		constraintMap.clear();
		constraintMap.put("lte", dateOfDignosis);
		testDate = new LocalDate(1973, 6, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is true real result is " + isValid(testDate, constraintMap));

		constraintMap.clear();
		constraintMap.put("lte", dateOfDignosis);
		testDate = new LocalDate(2016, 1, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testDate, constraintMap));

		constraintMap.clear();
		constraintMap.put("gte", birthdate);
		testDate = new LocalDate(1955, 1, 1);
		log.warn("[SIMED]{ch.hcuge.simed.foundation.utilities.validator.JodaDateRangeValidator} <main> for " + testDate + " and constraint: " + constraintMap
				+ " expeted result is false real result is " + isValid(testDate, constraintMap));

	}
}
