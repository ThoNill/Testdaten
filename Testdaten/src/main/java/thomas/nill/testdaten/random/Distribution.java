package thomas.nill.testdaten.random;

/**
 * Probability distribution
 * @author tnill
 *
 */
public abstract class Distribution {
	protected int max = 10;

	public Distribution(int max) {
		if (max < 0) {
			throw new IllegalArgumentException("Grenze sollte >= 0 sein");
		}
		this.max = max;
	}

	public abstract int randomNumberLowerOrEqualsThenMax();

	public int getMax() {
		return max;
	}

}
