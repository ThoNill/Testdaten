package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a constant value.
 * 
 * @author tnill
 *
 * @param <K>
 */

public class ConstantCreator<K> implements ValueCreator<K>{
	private K value;
	
	public ConstantCreator(K value) {
		super();
		this.value = value;
	}

	@Override
	public K generateValue(Values v) {
		return value;
	}
	

}
