package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;
import thomas.nill.testdaten.basis.Values;

public class IndirectCreator implements ValueCreator<String> {
	private ValueCreatorFabrik fabric;
	private ValueCreator<?> creator;
	
	public IndirectCreator(ValueCreatorFabrik fabric, String name) {
		this.fabric = fabric;
		this.creator = fabric.searchCreator(name);
	}

	@Override
	public String generateValue(Values v) {
		String referenz  = creator.generateValue(v).toString();
		ValueCreator<?> referenzCreator = fabric.searchCreator(referenz);
		return referenzCreator.generateValue(v).toString();
	}

}
