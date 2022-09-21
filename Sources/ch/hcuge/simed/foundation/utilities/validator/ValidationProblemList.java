/**
 * 
 */
package ch.hcuge.simed.foundation.utilities.validator;

import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

/**
 * @author dban
 *
 */
public class ValidationProblemList extends NSMutableArray<ValidationProblem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NSArray<ValidationProblem> errors(){
		return EOQualifier.filteredArrayWithQualifier(this, new EOKeyValueQualifier(ValidationProblem.SEVERITY, EOQualifier.QualifierOperatorEqual, ValidationProblem.ERROR));
	}
	
	public NSArray<ValidationProblem> warnings(){
		return EOQualifier.filteredArrayWithQualifier(this, new EOKeyValueQualifier(ValidationProblem.SEVERITY, EOQualifier.QualifierOperatorEqual, ValidationProblem.WARN));
	}
	public NSArray<ValidationProblem> infos(){
		return EOQualifier.filteredArrayWithQualifier(this, new EOKeyValueQualifier(ValidationProblem.SEVERITY, EOQualifier.QualifierOperatorEqual, ValidationProblem.INFO));
	}
	public NSArray<ValidationProblem> cleanups(){
		return EOQualifier.filteredArrayWithQualifier(this, new EOKeyValueQualifier(ValidationProblem.SEVERITY, EOQualifier.QualifierOperatorEqual, ValidationProblem.CLEANUP));
	}
	
	public boolean hasBlockingProblem(){
		if(errors().count()>0){
			return true;
		}
		/* pegn
		if(warnings().count()>0){
			return true;
		}
		*/
		return false;
	}
	
}
