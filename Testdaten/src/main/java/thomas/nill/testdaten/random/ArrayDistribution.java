package thomas.nill.testdaten.random;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;


/**
 * Probability distribution based on a array of probabilities.
 * @author tnill
 *
 */
@Slf4j
public class ArrayDistribution extends Distribution {
	double[] kummuliert;
	
	public ArrayDistribution(Double[] probDistribution) {
		super(probDistribution.length);
		addTheProbabilities(probDistribution);
	}

	public ArrayDistribution(int max,Function<Integer,Double> distribution) {
		super(max);
		Double[] prob = new Double[max];
		double sum = initArrayWithFunction(distribution, prob);
		normTheFunction(prob, sum);
		addTheProbabilities(prob);
	}

	private double initArrayWithFunction(Function<Integer, Double> distribution, Double[] prob) {
		double sum=0.0;
		for(int i=1;i<=prob.length;i++) {
			double p = distribution.apply(i);
			if (p<0.0) {
				throw new IllegalArgumentException("Function " + distribution.getClass().getSimpleName() + " generate negativ probabilities");
			}
			prob[i-1] = p;
			sum+=p;
		}
		return sum;
	}

	private void normTheFunction(Double[] prob, double sum) {
		if (sum < 1.0) { 
			prob[prob.length-1] = prob[prob.length-1] + (1.0 - sum);
		} else {
			for(int i=0;i<prob.length;i++) {
				prob[i] = prob[i]/sum;
			}
		}
	}

	
	private void addTheProbabilities(Double[] wahrscheinlichkeit) {
		kummuliert = new double[wahrscheinlichkeit.length];
		kummuliert[0] = wahrscheinlichkeit[0];
		for(int i=1;i< wahrscheinlichkeit.length;i++) {
			if (wahrscheinlichkeit[i]<0.0) {
				throw new IllegalArgumentException("Wahrscheinlichkeit sollte >= 0.0 sein");
			}
			kummuliert[i] = kummuliert[i-1] + wahrscheinlichkeit[i]; 
		}
		if (kummuliert[kummuliert.length-1] > 1.0001) {
			throw new IllegalArgumentException("Summe der Wahrscheinlichkeiten sollte <= 1.0 sein ist aber "+kummuliert[kummuliert.length-1] );
		}
	}

	@Override
	public int randomNumberLowerOrEqualsThenMax() {
		return binSeach(0, max, Math.random());
	}
	
	public int binSeach(int start,int end,double w) {
		log.debug("start="+start + " end= " + end);
		if (start == end || start == end-1) {
			double wertInMitte = kummuliert[start];
			if (wertInMitte < w) {
				return end;
			}
			return start;
		}
		int mitte = calculateMiddle(start, end);
		double wertInMitte = kummuliert[mitte];
		log.debug("mitte= " + mitte + " wertInMitte= "+wertInMitte + " w= " + w);
		
		if (wertInMitte < w) {
			return binSeach(mitte,end,w);
		} else {
			return binSeach(start,mitte,w);
		}
	}

	private int calculateMiddle(int start, int end) {
		int mitte = start+end;
		if( mitte % 2 == 1 ) {
			mitte--;
		}
		mitte = mitte /2;
		return mitte;
	}
	
	
}
