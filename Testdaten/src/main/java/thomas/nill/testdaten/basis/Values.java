package thomas.nill.testdaten.basis;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
/**
 * A Values Map contains the generated test values.
 * 
 * @author tnill
 *
 * Every value has a name. 
 */
public class Values {
	private Map<String, Object> map = new HashMap<>();


	public Values() {
		super();
	}

	public Object get(@NonNull String key) {
		return map.get(key);
	}

	public Object put(@NonNull String key, @NonNull Object value) {
		return map.put(key, value);
	}

	public Object remove(@NonNull String key) {
		return map.remove(key);
	}
	
	public Map<String, Object> getValues() {
		return map;
	}
}
