package thomas.nill.testdaten;

import java.util.List;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.HasVerteilung;
import thomas.nill.testdaten.random.Verteilung;

public class SwitchCreator<K> implements ValueCreator<K>, HasVerteilung {
	private List<ValueCreator<K>> liste;
	private NummernBereichCreator bereich;
	
	public SwitchCreator(List<ValueCreator<K>> liste) {
		super();
		this.liste = liste;
		bereich = new NummernBereichCreator(liste.size()-1);
	}

	@Override
	public K generateValue(Values v) {
		return liste.get(bereich.generateValue(v)).generateValue(v);
	}

	@Override
	public void setVerteilung(Verteilung verteilung) {
		bereich.setVerteilung(verteilung);
	}

}
