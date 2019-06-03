package thomas.nill.testdaten.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
/**
 * The SimpleBeanSetter is a implementation of {@link BeanSetter}
 * @author tnill
 *
 * It transfers values from a {@link Values} hash to an bean.
 * Sub Bean in the bean are added with a addXXXX Method.
 */
public class SimpleBeanSetter implements BeanSetter {

	public SimpleBeanSetter() {
		super();
	}

	@Override
	public void updateValues(Object object, Values values) {
		try {
			// For the java beans standard, use BeanUtils
			BeanUtils.populate(object, values.getValues());
			// addXXXX Methods
			addSubBean(object, values);

		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new TestdataException(e);
		}
	}

	/**
	 * For the addXXXXXX  Methods
	 * 
	 * @param object
	 * @param values
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void addSubBean(Object object, Values values)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (String key : values.getValues().keySet()) {
			if (isAddMethod(key)) {
				Object o = values.get(key);
				addListOrOneBean(object, key, o);
			}
		}
	}

	private boolean isAddMethod(String key) {
		return key.startsWith("add");
	}

	private void addListOrOneBean(Object object, String key, Object o)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (o instanceof List) {
			addListOfBeans(object, key, (List<?>) o);
		} else {
			addOneSubBean(object, key, o);
		}
	}

	private void addListOfBeans(Object object, String key, List<?> l)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (Object lo : l) {
			addOneSubBean(object, key, lo);
		}
	}

	private void addOneSubBean(Object object, String key, Object o)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		MethodUtils.invokeMethod(object, key, new Object[] { o });
	}
}
