package thomas.nill.testdaten.basis;

import java.util.HashMap;

public class Creators {
	HashMap<String, ValueCreator<?>> values = new HashMap<>();

	public Creators() {
		super();
	}

	public ValueCreator<?> get(String key) {
		return values.get(key);
	}

	public Object put(String key, ValueCreator<?> value) {
		return values.put(key, value);
	}

	public Object generateNewFieldValue(Values v) {
		for (String key : values.keySet()) {
			generateNewFieldValue(key, v);
		}
		return v;
	}

	private void generateNewFieldValue(String key, Values v) {
		Object value = get(key).generateValue(v);
		v.put(key, value);
	}
	
}
