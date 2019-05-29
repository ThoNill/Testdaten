package thomas.nill.testdaten.basis;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class SimpleClassAnalyzer implements ClassAnalyzer {

	public SimpleClassAnalyzer() {
		super();
	}

	@Override
	public Creators analyse(Class<?> clazz, Creators original) {
		try {
			Map<String,String> properties = BeanUtils.describe(clazz.getConstructor().newInstance());
			Creators result = new Creators();
			for(String key : properties.keySet()) {
				ValueCreator<?> creator = original.get(key);
				if (creator != null) {
					result.put(key,creator);
				}
			}
			return result;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException
				| IllegalArgumentException | SecurityException e) {
			throw new TestdatenException(e);
		}
	}

}
