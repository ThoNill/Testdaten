package thomas.nill.testdaten;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.ScriptCreator;
import thomas.nill.testdaten.StringListeCreator;
import thomas.nill.testdaten.SwitchCreator;
import thomas.nill.testdaten.basis.Creators;
import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.HasVerteilung;
import thomas.nill.testdaten.random.Verteilung;

public class ResourceCreatorFabric implements ValueCreatorFabrik {
	private static final String INIT_DISTRIBUTION = "initDistribution_";
	private ResourceBundle labels;
	private Creators creators = new Creators();

	// testwords
	public ResourceCreatorFabric(String name) {
		this(name, Locale.getDefault());
	}

	public ResourceCreatorFabric(String name, Locale locale) {
		labels = ResourceBundle.getBundle("thomas.nill.testdaten." + name, locale);
	}

	@Override
	public ValueCreator<?> searchCreator(String name) {
		System.out.println("Suche nach name= <" + name + ">");
		ValueCreator<?> c = creators.get(name);
		if (c == null) {
			System.out.println("Suche nach name= <" + name + ">");
			String text = (name.contains("{")) ? name : labels.getString(name);
			c = createCreator(name, text);
			creators.put(name, c);
			System.out.println("Setze name= <" + name + "> auf " + c.toString());
		} else {
			System.out.println("name= <" + name + "> ist " + c.toString());
		}
		return c;
	}

	private ValueCreator<?> createCreator(String name, String text) {
		if (text.contains("{") || text.contains("(") || text.contains("[")) {
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
		return new LookupCreator<String>(name, new StringListeCreator(text));
	}

	public Values getValues(Values values) {
		initDistributions(values);
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
		};
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
			if (!(c instanceof HasVerteilung)) {
				throw new TestdatenException("Class " + c.getClass().getSimpleName() + " does not implement "+ HasVerteilung.class.getSimpleName());
			}
			Object obj = searchCreator(key).generateValue(values);
			if (!(obj instanceof Verteilung)) {
				throw new TestdatenException("Class " + obj.getClass().getSimpleName() + " is not a "+ Verteilung.class.getSimpleName());
			}
			Verteilung verteilung = (Verteilung) obj;
			((HasVerteilung) c).setVerteilung(verteilung);
		}
	}

}
