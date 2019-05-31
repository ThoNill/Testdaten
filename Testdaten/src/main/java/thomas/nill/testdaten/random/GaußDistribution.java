package thomas.nill.testdaten.random;

import java.util.function.Function;

public class GauﬂDistribution implements Function<Integer,Double> {
	private double my;
	private double sigmaSquare;
	private double norm;
	
	public GauﬂDistribution(double my, double sigma) {
		super();
		this.my = my;
		this.sigmaSquare = 2 * sigma*sigma;
		this.norm = 1 / Math.sqrt(Math.PI * sigmaSquare);
	}

	@Override
	public Double apply(Integer t) {
		double x = t.doubleValue();
		double x0 = - (x-my) * (x-my) / sigmaSquare; 
		return norm * Math.exp(x0);
	}
	
}


