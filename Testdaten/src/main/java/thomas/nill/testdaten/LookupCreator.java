package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class LookupCreator<K> implements ValueCreator<K> {
	private String key;
	private ValueCreator<K> creator;

	
	public LookupCreator(String key, ValueCreator<K> creator) {
		super();
		this.key = key;
		this.creator = creator;
	}


	@Override
	public K generateValue(Values v) {
		Object o = v.get(key);
		if (o != null) {
			return (K)o;
		} else {
			K k = creator.generateValue(v);
			v.put(key, k);
			return k;
		}
	}


}
