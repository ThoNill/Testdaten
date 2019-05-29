package thomas.nill.testdaten.basis;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.SkriptCreator;
import thomas.nill.testdaten.TemplateCreator;
import thomas.nill.testdaten.StringListeCreator;
import thomas.nill.testdaten.SwitchCreator;

public class ResourceCreatorFabric implements ValueCreatorFabrik {
	private ResourceBundle labels;
	private Creators creators = new Creators();

	// testwords
	public ResourceCreatorFabric(String name) {
		this(name,Locale.getDefault());
	}

	public ResourceCreatorFabric(String name,Locale locale) {
		labels = ResourceBundle.getBundle("thomas.nill.testdaten."+name, locale);
	}

	@Override
	public ValueCreator<?> searchCreator(String name) {
		ValueCreator<?> c = creators.get(name);
		if (c == null) {
			c = createCreatorWithName(name);
			creators.put(name, c);
		}
		return c;
	}

	
	private ValueCreator<String> createCreatorWithName(String name) {
		String text = (name.contains("{")) ? name : labels.getString(name);
		return createCreator(name, text);
	}

	private ValueCreator<String> createCreator(String name, String text) {
		if (text.contains("{") || text.contains("(")) {
			if (text.contains("|")) {
				List<ValueCreator<String>> liste = new ArrayList<>();
				String[] texte = text.split("\\|");
				for (String t : texte) {
					liste.add(new TemplateCreator(t, this));
				}
				return new LookupCreator<String>(name, new SwitchCreator(liste));
			} else {
				return new LookupCreator<String>(name, new TemplateCreator(text, this));
			}
		}
		return new LookupCreator<String>(name, new StringListeCreator(text));
	}


	public ValueCreator<?> searchCreator(String name, Skript skript) {
		ValueCreator<?> c = creators.get(name);
		if (c == null) {
			
			System.out.println("name= <" + name + ">");
			String text = (name.contains("{")) ? name : labels.getString(name);
			c = createCreatorWithScript(name,text,skript);
			creators.put(name, c);
		}
		return c;
	}

	private ValueCreator<String> createCreatorWithScript(String name, String text, Skript skript) {
		if (text.contains("{") || text.contains("(")) {
			if (text.contains("|")) {
				List<ValueCreator<String>> liste = new ArrayList<>();
				String[] texte = text.split("\\|");
				for (String t : texte) {
					liste.add(new SkriptCreator(skript,t));
				}
				return new LookupCreator<String>(name, new SwitchCreator(liste));
			} else {
				return new LookupCreator<String>(name, new SkriptCreator(skript,text));
			}
		}
		return new LookupCreator<String>(name, new StringListeCreator(text));
	}
	
	public Values getValues(Values values,Skript skript) {
		Enumeration<String> e = labels.getKeys();
		while(e.hasMoreElements()) {
			searchCreator(e.nextElement(),skript).generateValue(values);
		}
		return values;
	}
}
