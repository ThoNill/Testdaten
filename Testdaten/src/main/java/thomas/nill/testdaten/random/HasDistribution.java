package thomas.nill.testdaten.random;

import thomas.nill.testdaten.basis.ValueCreator;

/**
 * Marker interface, for {@link ValueCreator} that use a {@link Distribution}
 * @author tnill
 *
 */
public interface HasDistribution {
	void setVerteilung(Distribution distribution);
}
