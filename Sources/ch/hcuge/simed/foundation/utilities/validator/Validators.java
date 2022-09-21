package ch.hcuge.simed.foundation.utilities.validator;

import java.util.ArrayList;

public class Validators extends ArrayList<Validator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private ArrayList<Validator> _validator = new ArrayList<Validator>();

	public Validators() {
		super();
	}

	public ArrayList<Validator> getValidators() {
		return this;// ._validator;
	}

	// public void setValidators(ArrayList<Validator> newParameters) {
	// this = newParameters;
	// // this._validator = newParameters;
	// }

	public void addValidator(Validator newParameter) {
		this.add(newParameter);
	}

	public int count() {
		return this.size();
	}
	
	public Validator objectAtIndex(int i){
		return this.get(i);
	}

	public boolean hasRangeValidator() {
		for (Validator validator : getValidators()) {
			if (validator.getValidatorName().equals(Validator.RANGE_VALIDATOR)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasUnansweredValidator() {
		for (Validator validator : getValidators()) {
			if (validator.getValidatorName().equals(Validator.UNANSWERED_VALIDATOR)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasOtherValidator() {
		for (Validator validator : getValidators()) {
			if (validator.getValidatorName().equals(Validator.OTHER_VALIDATOR)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasConditionalUnansweredValidator() {
		for (Validator validator : getValidators()) {
			if (validator.getValidatorName().equals(Validator.CONDITIONAL_UNANSWERED_VALIDATOR)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasConditionalNotNull() {
		for (Validator validator : getValidators()) {
			if (validator.getValidatorName().equals(Validator.CONDITIONAL_NOT_NULL_VALIDATOR)) {
				return true;
			}
		}
		return false;
	}

}
