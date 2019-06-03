package thomas.nill.testdaten.basis;

import thomas.nill.testdaten.LookupCreator;

/**
 * The central FunctionalInterface for the creation of test values.
 * @author tnill
 *
 * ValueCreator are created with the help of a 
 * {@link ValueCreatorFabrik}. 
 * The ValueCreators are stored in a map {@link Creators}.
 * 
 * The {@link LookupCreator} insert created Values in a {@link Values} map.
 * 
 * @param <K>
 */
@FunctionalInterface
public interface ValueCreator<K> {
        K generateValue(Values v);
}
