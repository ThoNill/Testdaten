package thomas.nill.testdaten;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;

/**
 * Creates an inserts a value in a {@link Values} map.
 * It uses an other {@link ValueCreator}
 * 
 * @author tnill
 *
 * If the value ist already created this Creator uses always the same value
 * from the map.
 * 
 * 
 */
@Slf4j
public class LookupCreator<K> implements ValueCreator<K>, HasDistribution {
	private String key;
	private ValueCreator<K> creator;

	public LookupCreator(@NonNull String key, @NonNull ValueCreator<K> creator) {
		super();
		this.key = key;
		this.creator = creator;
	}

	@Override
	public K generateValue(@NonNull Values v) {
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
	public void setDistribution(@NonNull Distribution distribution) {
		if (creator instanceof HasDistribution) {
			((HasDistribution) creator).setDistribution(distribution);
		} else {
			throw new TestdataException("Class " + creator.getClass().getSimpleName() + " does not implement "
					+ HasDistribution.class.getSimpleName());
		}
	}



}
