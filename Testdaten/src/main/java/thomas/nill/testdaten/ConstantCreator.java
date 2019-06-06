package thomas.nill.testdaten;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a constant value.
 * 
 * @author tnill
 *
 * 
 */

public class ConstantCreator<K> implements ValueCreator<K>{
	private K value;
	
	public ConstantCreator(@NonNull K value) {
		super();
		this.value = value;
	}

	@Override
	public K generateValue(@NonNull Values v) {
		return value;
	}
	

}
