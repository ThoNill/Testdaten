package thomas.nill.testdaten.basis;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.apache.commons.beanutils.ConstructorUtils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Helper class for the call of constructors.
 * 
 * @author tnill
 *
 */
@Slf4j
public class ConstructorHelper {

	public Object searchConstructorAndCreate(@NonNull Class<?> clazz,@NonNull  String[] args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Optional<Object> opt = argumentCountIsParameterCount(clazz, args);
		if (opt.isEmpty()) {
			opt = lastArgumentIsArray(clazz, args);
		}
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new TestdataException("No Constructor found in " + clazz.getSimpleName());
	}

	private Optional<Object> lastArgumentIsArray(Class<?> clazz, String[] args)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		for (Constructor<?> ctor : clazz.getConstructors()) {
			final Class<?>[] ctorParams = ctor.getParameterTypes();
			if (ctorParams.length < args.length && noArryParameters(ctorParams, ctorParams.length - 1)
					&& ctorParams[ctorParams.length - 1].isArray()) {
				Object obj = correctTypesAndCallConstructor(clazz, args, ctorParams);
				if (obj != null) {
					return Optional.of(obj);
				}
			}
		}
		return Optional.empty();
	}

	private Optional<Object> argumentCountIsParameterCount(Class<?> clazz, String[] args)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		for (Constructor<?> ctor : clazz.getConstructors()) {
			final Class<?>[] ctorParams = ctor.getParameterTypes();
			if (ctorParams.length == args.length && noArryParameters(ctorParams, ctorParams.length)) {
				Object obj = correctTypesAndCallConstructor(clazz, args, ctorParams);
				if (obj != null) {
					return Optional.of(obj);
				}
			}
		}
		return Optional.empty();
	}

	private boolean noArryParameters(Class<?>[] ctorParams, int max) {
		for (int i = 0; i < max; i++) {
			if (ctorParams[i].isArray()) {
				return false;
			}
		}
		return true;
	}

	private Object correctTypesAndCallConstructor(Class<?> clazz, String[] args, final Class<?>[] ctorParams)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Object[] oargs = new Object[ctorParams.length];
		boolean canCorrected = correctTypesOfObjects(oargs, args, ctorParams);
		if (canCorrected) {
			try {
				return ConstructorUtils.invokeConstructor(clazz, oargs);
			} catch (NoSuchMethodException e) {
				throw new TestdataException("No Constructor found in " + clazz.getSimpleName(), e);
			}
		}
		return null;
	}

	private boolean correctTypesOfObjects(Object[] oargs, String[] args, final Class<?>[] ctorParams) {
		for (int n = 0; n < ctorParams.length; n++) {
			String className = ctorParams[n].getSimpleName();
			boolean isArray = ctorParams[n].isArray();
			if (isArray) {
				if (!convertToArray(oargs, args, ctorParams, n)) {
					return false;
				}
			} else {
				if (!convert2Type(oargs, n, args[n], className)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean convertToArray(Object[] oargs, final String[] args, final Class<?>[] ctorParams, int n) {
		Object[] orest = (Object[]) Array.newInstance(ctorParams[n].getComponentType(), args.length - n);
		return convertRestOfArgumentlistIntoArray(oargs, args, n, ctorParams[n].getComponentType().getSimpleName(),
				orest);
	}

	private boolean convertRestOfArgumentlistIntoArray(Object[] oargs, final String[] args, int n,
			final String className, Object[] orest) {
		int nArray = n;
		for (int m = 0; m < orest.length; m++) {
			if (!convert2Type(orest, m, args[nArray], className)) {
				return false;
			}
			nArray++;
		}
		oargs[n] = orest;
		return true;
	}

	private boolean convert2Type(Object[] oargs, int tn, String s, String className) {
		try {
			switch (className) {
			case "int":
			case "Integer":
				oargs[tn] = Integer.valueOf(s);
				break;
			case "long":
			case "Long":
				oargs[tn] = Long.valueOf(s);
				break;
			case "float":
			case "Float":
				oargs[tn] = Float.valueOf(s);
				break;
			case "double":
			case "Double":
				oargs[tn] = Double.valueOf(s);
				break;
			case "Class":
				oargs[tn] = Thread.currentThread().getContextClassLoader().loadClass(s);
				break;

			case "String":
				oargs[tn] = s;
				break;
			default:
				log.debug("can not convert a String to Class " + className);
				return false;
			}			
		} catch (ClassNotFoundException e) {
			throw new TestdataException("Can not load Class " + s, e);
		}
		return true;
	}

}
