package thomas.nill.testdaten.basis;

import java.util.HashMap;
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

	public Object get(String key) {
		return values.get(key);
	}

	public Object put(String key, Object value) {
		return values.put(key, value);
	}

	public Object remove(String key) {
		return values.remove(key);
	}
	
	public HashMap<String, Object> getValues() {
		return values;
	}
}
