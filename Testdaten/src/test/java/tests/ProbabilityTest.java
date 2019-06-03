package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import thomas.nill.testdaten.random.ArrayDistribution;

@Slf4j(topic = "test")
public class ProbabilityTest {

	public ProbabilityTest() {
	}

	
	@Test
	public void distribution() {
		Double[] prob = generateRandomArray(10);
		ArrayDistribution dist = new ArrayDistribution(prob);
		
		int countExperiments = 10000;
		long[] count = countGeneratedValues(prob.length, dist, countExperiments);
		Double[] generatedProb = normalize(count,countExperiments);
		double diff = squareDifference(prob, generatedProb);
		assertEquals(0.0,diff,0.01);
	}

	
	private long[] countGeneratedValues(int c, ArrayDistribution dist, int countExperiments) {
		long[] count = new long[c];
		for(int i=0;i< countExperiments;i++) {
			count[dist.zufälligeZahlBisZurGrenze()]++;
		}
		return count;
	}



	private Double[] generateRandomArray(int size) {
		Double[] prob = new Double[size];
		for(int i=0;i<prob.length;i++) {
			prob[i] = Math.random();
		}
		normalize(prob);
		return prob;
	}

	private void normalize(Double[] prob) {
		double dsum = sumUp(prob);
		for(int i=0;i< prob.length;i++) {
			prob[i] = prob[i].doubleValue()/dsum;
		}
	}

	private double sumUp(Double[] prob) {
		double dsum=0;
		for(int i=0;i< prob.length;i++) {
			dsum += prob[i].doubleValue();
		}
		return dsum;
	}
	

	private Double[] normalize(long[] count,int countExperiments) {
		Double[] generatedProb = new Double[count.length];
		for(int i=0;i< generatedProb.length;i++) {
			generatedProb[i] = Double.valueOf((double)(count[i])/((double)countExperiments));
		}
		return generatedProb;
	}

	private double squareDifference(Double[] prob, Double[] generatedProb) {
		double diff=0.0d;
		for(int i=0;i< generatedProb.length;i++) {
			double d = generatedProb[i].doubleValue()- prob[i].doubleValue();
			diff += (d*d);
		}
		return diff;
	}

}
