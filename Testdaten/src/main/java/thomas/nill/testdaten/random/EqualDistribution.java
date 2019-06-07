package thomas.nill.testdaten.random;

import java.util.Random;

public class EqualDistribution extends Distribution{
    private Random r;
    
	public EqualDistribution(int max) {
		super(max);
		if (max<0) {
    		throw new IllegalArgumentException("max >= 0 not " + max);
    	}
		r = new Random();
	}

	@Override
	public int randomNumberLowerOrEqualsThenMax() {
		return r.nextInt(max+1);
	}

}
