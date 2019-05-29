package thomas.nill.testdaten.basis;

public interface ValueCreatorFabrik {
	ValueCreator<?> searchCreator(String name);
}
