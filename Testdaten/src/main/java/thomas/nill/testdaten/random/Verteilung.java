package thomas.nill.testdaten.random;

public abstract class Verteilung {
	protected int grenze = 10;

	public Verteilung(int grenze) {
		if (grenze <= 0) {
			throw new IllegalArgumentException("Grenze sollte > 0 sein");
		}
		this.grenze = grenze;
	}

	public abstract int zufälligeZahlBisZurGrenze();

}
