package thomas.nill.testdaten.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;

public class SimpleBeanSetter implements BeanSetter {

	public SimpleBeanSetter() {
		super();
	}

	public void updateValues(Object object, Values values) {
		try {
			BeanUtils.populate(object, values.getValues());

			for (String key : values.getValues().keySet()) {
				if (key.startsWith("add")) {
					Object o = values.get(key);
					if (o instanceof List) {
						List<?> l = (List<?>) o;
						for (Object lo : l) {
							MethodUtils.invokeMethod(object, key, new Object[] { lo });
						}
					} else {
						MethodUtils.invokeMethod(object, key, new Object[] { o });
					}
				}
			}

		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new TestdatenException(e);
		}
	}
}
