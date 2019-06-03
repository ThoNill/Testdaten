package thomas.nill.testdaten.basis;
/**
 * A BeanSetter transfers the values in a {@link Values} in a bean.
 * 
 * @author tnill
 *
 */
public interface BeanSetter {
	void updateValues(Object bean,Values values);
}
