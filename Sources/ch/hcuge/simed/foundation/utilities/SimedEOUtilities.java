package ch.hcuge.simed.foundation.utilities;

import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;

public class SimedEOUtilities {

	/**
	 * @param eo
	 * 
	 *            Utility method to help invalidate an EnterpriseObject
	 */
	public static void invalidateEnterpriseObject(EOEnterpriseObject eo) {
		NSArray<EOGlobalID> gids = new NSArray<EOGlobalID>(eo.editingContext().globalIDForObject(eo));
		eo.editingContext().invalidateObjectsWithGlobalIDs(gids);
	}
}
