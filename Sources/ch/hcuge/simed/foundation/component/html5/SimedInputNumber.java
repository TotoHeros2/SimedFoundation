package ch.hcuge.simed.foundation.component.html5;

import java.text.DecimalFormat;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components.html5.ERXWONumberField;

/**
 * <span class="en">
 * 

 * </span>
 * 
 * @author ishimoto
 */
public class SimedInputNumber extends ERXWONumberField {

  //********************************************************************
  //  Binding Properties
  //********************************************************************

  private static final String INTEGER_PATTERN = "^\\\\d+$";

  protected WOAssociation _pattern;


  //********************************************************************
  //  Constructor
  //********************************************************************

  public SimedInputNumber(String tagname, NSDictionary<String, WOAssociation> nsdictionary, WOElement woelement) {
    super("input", nsdictionary, woelement);
    _pattern = _associations.removeObjectForKey("pattern");
//    _pattern.valueInComponent(aComponent)
//    _pattern.bindingInComponent(woelement);


//    _min = _associations.removeObjectForKey("min");
//    _max = _associations.removeObjectForKey("max");
//    _step = _associations.removeObjectForKey("step");
//
//    _autofocus = _associations.removeObjectForKey("autofocus");
  }



  @Override
  protected void _appendValueAttributeToResponse(WOResponse woresponse, WOContext wocontext) {
	    WOComponent component = wocontext.component();
	    String formatter = (String) _pattern.valueInComponent(component);
	    Object valueInComponent = _value.valueInComponent(component);
		if (valueInComponent != null) {

				DecimalFormat df = new DecimalFormat(formatter);
				String stringValue = df.format(valueInComponent);
				woresponse._appendTagAttributeAndValue("value", stringValue, true);

		}

	    if(_min != null) {
	      Object minInComponent = _min.valueInComponent(component);
	      if(minInComponent != null) {
	        String stringValue = minInComponent.toString();
	        woresponse._appendTagAttributeAndValue("min", stringValue, true);
	      }     
	    }

	    if(_max != null) {
	      Object maxInComponent = _max.valueInComponent(component);
	      if(maxInComponent != null) {
	        String stringValue = maxInComponent.toString();
	        woresponse._appendTagAttributeAndValue("max", stringValue, true);
	      }
	    }

	    if(_step != null) {
	      Object stepInComponent = _step.valueInComponent(component);
	      if(stepInComponent != null) {
	        String stringValue = stepInComponent.toString();
	        woresponse._appendTagAttributeAndValue("step", stringValue, true);
	      }
	    }   

	    if (isAutofocusInContext(wocontext)) {
	      woresponse._appendTagAttributeAndValue("autofocus", "autofocus", false);
	    }

	    if (isRequiredInContext(wocontext)) {
	      woresponse._appendTagAttributeAndValue("required", "required", false);
	    }

	    if (isReadonlyInContext(wocontext)) {
	      woresponse._appendTagAttributeAndValue("readonly", "readonly", false);
	    }

//    if(_pattern != null) {
//        Object patternInComponent = _pattern.valueInComponent(component);
//        if(patternInComponent != null) {
//          String stringValue = patternInComponent.toString();
//          woresponse._appendTagAttributeAndValue("pattern", stringValue, true);
//        }     
//      } else {
//        woresponse._appendTagAttributeAndValue("pattern", INTEGER_PATTERN, true);
//      }


  }


}