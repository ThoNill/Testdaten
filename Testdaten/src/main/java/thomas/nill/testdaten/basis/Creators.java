package thomas.nill.testdaten.basis;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
import thomas.nill.testdaten.ResourceCreatorFabric;

/**
 * Creators is a map of {@link ValueCreator} it is used in
 * {@link ResourceCreatorFabric}
 * 
 * @author tnill
 *
 *         Instances of this class stores {@link ValueCreator}
 */
public class Creators {
	private Map<String, ValueCreator> values = new HashMap<>();

	public Creators() {
		super();
	}

	public ValueCreator get(@NonNull String key) {
		return values.get(key);
	}

	public Object put(@NonNull String key, @NonNull ValueCreator value) {
		return values.put(key, value);
	}

	/**
	 * Generate new values, eventually stored in {@link Values}
	 * 
	 * @param v Values Map
	 * @return generated value
	 */
	public Object generateNewFieldValue(@NonNull Values v) {
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
