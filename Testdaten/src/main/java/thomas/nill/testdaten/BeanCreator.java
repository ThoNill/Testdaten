package thomas.nill.testdaten;

import java.lang.reflect.InvocationTargetException;

import thomas.nill.testdaten.basis.BeanSetter;
import thomas.nill.testdaten.basis.SimpleBeanSetter;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a bean
 * 
 * @author tnill
 *
 * 
 */
public class BeanCreator<K> implements ValueCreator<K> {
	private Class<?> clazz;
	private BeanSetter setter;
	private ResourceCreatorFabric fabric;

	public BeanCreator(Class<?> clazz) {
		this(clazz,new SimpleBeanSetter());
	}
	
	public BeanCreator(Class<?> clazz, BeanSetter setter) {
		super();
		this.clazz = clazz;
		this.setter = setter;
		this.fabric = new ResourceCreatorFabric(clazz.getCanonicalName());
	}
	
	public static <K> K create(Class<?> clazz) {
		BeanCreator<K> bc = new BeanCreator<>(clazz);
		return bc.generateValue(new Values());
	}

	@Override
	public K generateValue(Values v) {
		try {
			Values beanValues = fabric.getValues(v);
			K bean = (K) clazz.getConstructor().newInstance();
			setter.updateValues(bean, beanValues);
			return bean;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new TestdataException(e);
		}
	}

}
