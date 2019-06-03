package thomas.nill.testdaten.basis;

@FunctionalInterface
public interface ValueCreator<K> {
        K generateValue(Values v);
}
