package thomas.nill.testdaten.basis;

import thomas.nill.testdaten.ResourceCreatorFabric;

/**
 * The ValueCreatorFabrik creates or finds existing {@link ValueCreator} instances.
 * @author tnill
 *
 * A {@link ResourceCreatorFabric} is a implementation of this interface.
 */
public interface ValueCreatorFabrik {
	ValueCreator<?> searchCreator(String name);
}
