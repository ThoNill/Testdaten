package thomas.nill.testdaten.random;

public class EqualDistribution extends Distribution{

	public EqualDistribution(int grenze) {
		super(grenze);
	}

	@Override
	public int zuf�lligeZahlBisZurGrenze() {
		int n = (int) (Math.random() * (grenze + 1));
		return (n > grenze) ? grenze : n;
	}

}
