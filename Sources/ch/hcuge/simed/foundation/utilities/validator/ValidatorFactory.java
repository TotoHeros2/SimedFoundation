package ch.hcuge.simed.foundation.utilities.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSMutableDictionary;

public class ValidatorFactory {

	private static NSMutableDictionary<String, AbstractValidator> _validatorCache = new NSMutableDictionary<String, AbstractValidator>();

	private static Logger log = Logger.getLogger(ValidatorFactory.class);

	private ValidatorFactory() {
		_validatorCache.setObjectForKey(validatorInstance(UnansweredValidator.class, UnansweredValidator.KEY),
				UnansweredValidator.KEY);
		_validatorCache.setObjectForKey(validatorInstance(RangeValidator.class, RangeValidator.KEY),
				RangeValidator.KEY);
		_validatorCache.setObjectForKey(validatorInstance(ConditionalNotNull.class, ConditionalNotNull.KEY),
				ConditionalNotNull.KEY);
		_validatorCache.setObjectForKey(validatorInstance(ConditionalUnansweredValidator.class, ConditionalUnansweredValidator.KEY),
				ConditionalUnansweredValidator.KEY);
		_validatorCache.setObjectForKey(validatorInstance(OtherValidator.class, OtherValidator.KEY),
				OtherValidator.KEY);
	}

	public static AbstractValidator getClass(String key) {
		return (AbstractValidator) getInstance().validatorCache().valueForKey(key);
	}

	private static AbstractValidator validatorInstance(Class<? extends AbstractValidator> aClass, String key) {
		AbstractValidator v = null;
		if (aClass == null) {
			log.error("No validator class for key: '" + key + "'.");
		}
		try {
			Class<?>[] param = null;
			System.err.println();
			Method method = aClass.getMethod("getInstance", param);
			Object o = method.invoke(null, param);
			v = (AbstractValidator) o;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			log.error("Pb with validator with key: '" + key + "'.", e);
		}
		return v;
	}

	private static NSMutableDictionary<String, AbstractValidator> validatorCache() {
		return _validatorCache;
	}
	
	private static ValidatorFactory getInstance() {
		return ValidatorFactoryHolder.INSTANCE;
	}

	private static class ValidatorFactoryHolder {
		private final static ValidatorFactory INSTANCE = new ValidatorFactory();
	}

}
