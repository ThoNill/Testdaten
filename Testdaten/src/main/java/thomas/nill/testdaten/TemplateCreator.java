package thomas.nill.testdaten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.stringtemplate.v4.ST;

import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

public class TemplateCreator implements ValueCreator<String>{
	private String text;
	private List<String> keys = new ArrayList<>();
	private HashMap<String,ValueCreator<?>> creators = new HashMap<String, ValueCreator<?>>();
	private ValueCreatorFabrik fabric;
	
	public TemplateCreator(String text,ValueCreatorFabrik fabric) {
		super();
		this.fabric = fabric;
		this.text = text;
		while(text.length()>0) {
			if (text.contains("{")) {
				int start = text.indexOf('{');
				int end = endIndex(start,text);
				keys.add(text.substring(start+1, end));
				text = text.substring(end+1);
			} else {
				text="";
			}
		}
		for(String key : keys) {
			if (key.contains("{")) {
				creators.put(key, new IndirectCreator(fabric,key));
			} else {
				creators.put(key, fabric.searchCreator(key));
			}
		};
	}

	private int endIndex(int start, String text) {
		int depth = 0;
		for(int i=start;i< text.length();i++) {
			char c = text.charAt(i);
			switch(c) {
			case '{' : depth++; break;
			case '}' : depth--; break;
			default : break;
			}
			if (depth==0) {
				return i;
			}
		}
		throw new TestdatenException("The Text " + text + " is without closing }");
	}

	@Override
	public String generateValue(Values v) {
		String text = this.text;
		for(String key : keys) {
			text = text.replace("{"+key+"}",creators.get(key).generateValue(v).toString());
		}
		return text;
	}

	
}
