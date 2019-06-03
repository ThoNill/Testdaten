package thomas.nill.testdaten.random;

public class EqualDistribution extends Distribution{

	public EqualDistribution(int grenze) {
		super(grenze);
	}

	@Override
	public int randomNumberLowerOrEqualsThenMax() {
		int n = (int) (Math.random() * (max + 1));
		return (n > max) ? max : n;
	}

}
