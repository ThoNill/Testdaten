package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.Skript;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class SkriptCreator implements ValueCreator<String>{
	private Skript skript;
	private String expression;
	
	@Override
	public String generateValue(Values v) {
		return skript.auswerten(expression);
	}
	
	public SkriptCreator(Skript skript, String expression) {
		super();
		this.skript = skript;
		this.expression = expression;
	}
	
}
