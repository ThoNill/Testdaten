package thomas.nill.testdaten.random;

import java.util.function.IntToDoubleFunction;
/**
 * Normal or Gauﬂ Distribution
 * 
 * @author tnill
 *
 */
public class GaussDistribution implements IntToDoubleFunction {
	private double my;
	private double sigmaSquare;
	private double norm;
	
	public GaussDistribution(double my, double sigma) {
		super();
		this.my = my;
		this.sigmaSquare = 2 * sigma*sigma;
		this.norm = 1 / Math.sqrt(Math.PI * sigmaSquare);
	}

	@Override
	public double applyAsDouble(int t){
		double x0 = - (t-my) * (t-my) / sigmaSquare; 
		return norm * Math.exp(x0);
	}


	
}


