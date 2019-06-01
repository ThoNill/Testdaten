package thomas.nill.testdaten;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.HasVerteilung;
import thomas.nill.testdaten.random.Verteilung;

@Slf4j
public class LookupCreator<K> implements ValueCreator<K>, HasVerteilung {
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
	public void setVerteilung(Verteilung verteilung) {
		if (creator instanceof HasVerteilung) {
			((HasVerteilung) creator).setVerteilung(verteilung);
		} else {
			throw new TestdatenException("Class " + creator.getClass().getSimpleName() + " does not implement "
					+ HasVerteilung.class.getSimpleName());
		}
	}

}
