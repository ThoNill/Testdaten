package thomas.nill.testdaten.random;

public class Gleichverteilung extends Verteilung{

	public Gleichverteilung(int grenze) {
		super(grenze);
	}

	public int zuf�lligeZahlBisZurGrenze() {
		int n = (int) (Math.random() * (grenze + 1));
		return (n > grenze) ? grenze : n;
	}

}
