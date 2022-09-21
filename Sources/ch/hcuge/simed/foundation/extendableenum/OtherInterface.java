package ch.hcuge.simed.foundation.extendableenum;

import com.webobjects.foundation.NSArray;

public interface OtherInterface {

	// must implements a static method with this signature
	// public static NSArray<? extends ExtendableEnum> othersList();

	/**
	 * @return the name of the related field when other is chosen in the
	 *         enumeration the best way is to use _EOClass.ATTRIBUTE_NAME_KEY
	 */
	public String otherFieldName();
}
