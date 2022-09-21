package ch.hcuge.simed.foundation.component;

import org.apache.log4j.Logger;

import ch.hcuge.simed.foundation.interfaces.component.EditionMode;

import com.webobjects.appserver.WOContext;

public class SimedEditorComponent extends SimedComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Logger log = Logger.getLogger(SimedEditorComponent.class);

	public SimedEditorComponent(WOContext context) {
		super(context);
		this.setMode(EditionMode.EDIT);
	}

}
