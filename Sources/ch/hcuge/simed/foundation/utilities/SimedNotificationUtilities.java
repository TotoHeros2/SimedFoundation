package ch.hcuge.simed.foundation.utilities;

import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelector;

public class SimedNotificationUtilities {

	/**
	 * @param observer
	 * @param notificationName
	 * @param callbackFunction
	 * 
	 *            Utility method to help register a Notification
	 */
	public static void registerObserverForNotification(Object observer, String notificationName, String callbackFunction) {
		Class<?> args[] = { NSNotification.class };
		NSSelector<Void> selector = new NSSelector<Void>(callbackFunction, args);
		NSNotificationCenter.defaultCenter().addObserver(observer, selector, notificationName, null);
	}

}
