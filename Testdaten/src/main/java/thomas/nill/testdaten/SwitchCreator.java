package thomas.nill.testdaten;

import java.util.List;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;

/**
 * Uses a List of {@link ValueCreator} an switch between these {@link ValueCreator}
 * 
 * @author tnill
 *
 * 
 */
public class SwitchCreator<K> implements ValueCreator<K>, HasDistribution {
	private List<ValueCreator<K>> liste;
	private RangeCreator bereich;
	
	public SwitchCreator(List<ValueCreator<K>> liste) {
		super();
		this.liste = liste;
		bereich = new RangeCreator(liste.size()-1);
	}

	@Override
	public K generateValue(Values v) {
		return liste.get(bereich.generateValue(v)).generateValue(v);
	}

	@Override
	public void setDistribution(Distribution distribution) {
		bereich.setDistribution(distribution);
	}



}
