package thomas.nill.testdaten.random;

public abstract class Distribution {
	protected int grenze = 10;

	public Distribution(int grenze) {
		if (grenze < 0) {
			throw new IllegalArgumentException("Grenze sollte >= 0 sein");
		}
		this.grenze = grenze;
	}

	public abstract int zufälligeZahlBisZurGrenze();

	public int getGrenze() {
		return grenze;
	}

}
