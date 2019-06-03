package thomas.nill.testdaten;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;

@Slf4j
public class LookupCreator<K> implements ValueCreator<K>, HasDistribution {
	private String key;
	private ValueCreator<K> creator;

	public LookupCreator(String key, ValueCreator<K> creator) {
		super();
		this.key = key;
		this.creator = creator;
	}

	@Override
	public K generateValue(Values v) {
		log.debug("Suche nach name= <" + key + "> in " + v);
		Object o = v.get(key);
		if (o != null) {
			log.debug("Gefunden liefere <" + o.toString() + ">");
			return (K) o;
		} else {
			K k = creator.generateValue(v);
			log.debug("Erzeuge <" + k.toString() + ">");
			v.put(key, k);
			return k;
		}
	}

	@Override
	public void setVerteilung(Distribution distribution) {
		if (creator instanceof HasDistribution) {
			((HasDistribution) creator).setVerteilung(distribution);
		} else {
			throw new TestdataException("Class " + creator.getClass().getSimpleName() + " does not implement "
					+ HasDistribution.class.getSimpleName());
		}
	}

}
