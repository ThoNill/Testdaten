package thomas.nill.testdaten;

import java.util.List;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class SwitchCreator implements ValueCreator<String> {
	private List<ValueCreator<String>> liste;
	private NummernBereichCreator bereich;
	
	public SwitchCreator(List<ValueCreator<String>> liste) {
		super();
		this.liste = liste;
		bereich = new NummernBereichCreator(liste.size()-1);
	}

	@Override
	public String generateValue(Values v) {
		return liste.get(bereich.zufälligeZahlBisZurGrenze()).generateValue(v);
	}


}
