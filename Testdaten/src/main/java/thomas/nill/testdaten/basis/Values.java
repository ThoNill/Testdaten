package thomas.nill.testdaten.basis;

import java.util.HashMap;

import lombok.NonNull;
/**
 * A Values Map contains the generated test values.
 * 
 * @author tnill
 *
 * Every value has a name. 
 */
public class Values {
	HashMap<String, Object> values = new HashMap<>();


	public Values() {
		super();
	}

	public Object get(@NonNull String key) {
		return values.get(key);
	}

	public Object put(@NonNull String key, @NonNull Object value) {
		return values.put(key, value);
	}

	public Object remove(@NonNull String key) {
		return values.remove(key);
	}
	
	public HashMap<String, Object> getValues() {
		return values;
	}
}
