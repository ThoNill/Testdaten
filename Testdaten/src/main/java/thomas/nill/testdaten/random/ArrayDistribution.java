package thomas.nill.testdaten.random;

import java.util.function.Function;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


/**
 * Probability distribution based on a array of probabilities.
 * @author tnill
 *
 */
@Slf4j
public class ArrayDistribution extends Distribution {
	private double[] kummulated;
	
	public ArrayDistribution(@NonNull Double[] probDistribution) {
		super(probDistribution.length);
		addTheProbabilities(probDistribution);
	}

	public ArrayDistribution(int max,@NonNull Function<Integer,Double> distribution) {
		super(max);
		Double[] prob = new Double[max];
		double sum = initArrayWithFunction(distribution, prob);
		normTheFunction(prob, sum);
		addTheProbabilities(prob);
	}

	@Override
	public int randomNumberLowerOrEqualsThenMax() {
		return binSeach(0, max, Math.random());
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

	
	private void addTheProbabilities(Double[] prob) {
		kummulated = new double[prob.length];
		kummulated[0] = prob[0];
		for(int i=1;i< prob.length;i++) {
			if (prob[i]<0.0) {
				throw new IllegalArgumentException("Wahrscheinlichkeit sollte >= 0.0 sein");
			}
			kummulated[i] = kummulated[i-1] + prob[i]; 
		}
		if (kummulated[kummulated.length-1] > 1.0001) {
			throw new IllegalArgumentException("Summe der Wahrscheinlichkeiten sollte <= 1.0 sein ist aber "+kummulated[kummulated.length-1] );
		}
	}

	
	private int binSeach(int start,int end,double w) {
		assert start >=0 && end>=0;
		log.debug("start="+start + " end= " + end);
		if (start == end || start == end-1) {
			double wertInMitte = kummulated[start];
			if (wertInMitte < w) {
				return end;
			}
			return start;
		}
		int mitte = calculateMiddle(start, end);
		double wertInMitte = kummulated[mitte];
		log.debug("mitte= " + mitte + " wertInMitte= "+wertInMitte + " w= " + w);
		
		if (wertInMitte < w) {
			return binSeach(mitte,end,w);
		} else {
			return binSeach(start,mitte,w);
		}
	}

	private int calculateMiddle(int start, int end) {
		assert start >=0 && end>=0;
		int mitte = start+end;
		if( mitte >= 0 && mitte % 2 == 1 ) {
			mitte--;
		}
		mitte = mitte /2;
		return mitte;
	}
	
	
}
