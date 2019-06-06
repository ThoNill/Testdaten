package thomas.nill.testdaten;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.basis.Creators;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;
/**
 * A {@link ResourceCreatorFabric} uses {@link ResourceBundle} to create {@link ValueCreator}.
 * 
 * @author tnill
 *
 * The {@link ResourceBundle} properties files uses a script language.	
 */
@Slf4j
public class ResourceCreatorFabric implements ValueCreatorFabrik {
	private static final String INIT_DISTRIBUTION = "initDistribution_";
	private ResourceBundle labels;
	private Creators creators = new Creators();

	// testwords
	public ResourceCreatorFabric(String name) {
		this(name, Locale.getDefault());
	}

	public ResourceCreatorFabric(String name, Locale locale) {
		labels = ResourceBundle.getBundle(name, locale);
		initDistributions(new Values());
	}

	@Override
	public ValueCreator<?> searchCreator(String name) {
		log.debug("Suche nach name= <" + name + ">");
		ValueCreator<?> c = creators.get(name);
		if (c == null) {
			log.debug("Suche nach name= <" + name + ">");
			final String text = (name.contains("{")) ? name : labels.getString(name);
			c = createCreator(name, text);
			creators.put(name, c);
			log.debug("Setze name= <" + name + "> auf " + c.toString());
		} else {
			log.debug("name= <" + name + "> ist " + c.toString());
		}
		return c;
	}

	private ValueCreator<?> createCreator(String name, String text) {
		if (text.contains("{") || text.contains("(") || text.contains("[") || text.contains("}") || text.contains(")")
				|| text.contains("]")) {
			if (text.contains("|")) {
				List<ValueCreator<?>> liste = new ArrayList<>();
				String[] texte = text.split("\\|");
				for (String t : texte) {
					liste.add(new ScriptCreator(name, t, this));
				}
				return new LookupCreator<Object>(name, new SwitchCreator(liste));
			} else {
				return new LookupCreator<Object>(name, new ScriptCreator(name, text, this));
			}
		}
		return new LookupCreator<String>(name, new StringListCreator(text));
	}

	public Values getValues(Values values) {
		generateValues(values);
		return values;
	}

	private void generateValues(Values values) {
		Enumeration<String> e = labels.getKeys();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			if (!key.startsWith(INIT_DISTRIBUTION)) {
				searchCreator(key).generateValue(values);
			}
		}
		;
	}

	private void initDistributions(Values values) {
		Enumeration<String> e = labels.getKeys();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			initDistribution(values, e, key);
		}
	}

	private void initDistribution(Values values, Enumeration<String> e, String key) {
		if (key.startsWith(INIT_DISTRIBUTION)) {
			ValueCreator c = searchCreator(key.substring(INIT_DISTRIBUTION.length()));
			if (!(c instanceof HasDistribution)) {
				throw new TestdataException("Class " + c.getClass().getSimpleName() + " does not implement "
						+ HasDistribution.class.getSimpleName());
			}
			Object obj = searchCreator(key).generateValue(values);
			if (!(obj instanceof Distribution)) {
				throw new TestdataException(
						"Class " + obj.getClass().getSimpleName() + " is not a " + Distribution.class.getSimpleName());
			}
			Distribution distribution = (Distribution) obj;
			((HasDistribution) c).setDistribution(distribution);
		}
	}

}
