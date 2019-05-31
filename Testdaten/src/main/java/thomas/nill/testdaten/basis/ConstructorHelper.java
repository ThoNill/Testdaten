package thomas.nill.testdaten.basis;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.ConstructorUtils;

public class ConstructorHelper {

	public Object searchConstructorAndCreate(Class<?> clazz, String[] args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		for (Constructor<?> ctor : clazz.getConstructors()) {
			final Class<?>[] ctorParams = ctor.getParameterTypes();
			if (ctorParams.length == args.length && noArryParameters(ctorParams, ctorParams.length)) {
				Object obj = correctTypesAndCallConstructor(clazz, args, ctorParams);
				if (obj != null) {
					return obj;
				}
			}
		}
		for (Constructor<?> ctor : clazz.getConstructors()) {
			final Class<?>[] ctorParams = ctor.getParameterTypes();
			if (ctorParams.length < args.length && noArryParameters(ctorParams, ctorParams.length - 1)
					&& ctorParams[ctorParams.length - 1].isArray()) {
				Object obj = correctTypesAndCallConstructor(clazz, args, ctorParams);
				if (obj != null) {
					return obj;
				}
			}
		}

		throw new TestdatenException("No Constructor found in " + clazz.getSimpleName());
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
		Object oargs[] = new Object[ctorParams.length];
		boolean canCorrected = correctTypesOfObjects(oargs, args, ctorParams);
		if (canCorrected) {
			try {
				return ConstructorUtils.invokeConstructor(clazz, oargs);
			} catch (NoSuchMethodException e) {
				throw new TestdatenException("No Constructor found in " + clazz.getSimpleName(), e);
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
		return convertRestOfArgumentlistIntoArray(oargs, args, n,ctorParams[n].getComponentType().getSimpleName(),orest);
	}

	private boolean convertRestOfArgumentlistIntoArray(Object[] oargs, final String[] args, int n, final String className,Object[] orest) {
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
			try {
				oargs[tn] = Thread.currentThread().getContextClassLoader().loadClass(s);
			} catch (ClassNotFoundException e) {
				throw new TestdatenException("Can not load Class " + s, e);
			}
			break;

		case "String":
			oargs[tn] = s;
			break;
		default:
			// System.out.println("can not convert a String to Class " + className);
			return false;
		}
		return true;
	}

}
