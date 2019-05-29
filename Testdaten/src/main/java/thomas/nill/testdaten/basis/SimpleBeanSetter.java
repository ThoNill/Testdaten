package thomas.nill.testdaten.basis;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class SimpleBeanSetter implements BeanSetter{

	public SimpleBeanSetter() {
		super();
	}

	public void updateValues(Object object,Values values){
		try {
			BeanUtils.populate(object, values.getValues());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new TestdatenException(e);
		}
	}
}
