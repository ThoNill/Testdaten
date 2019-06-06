package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.EqualDistribution;
import thomas.nill.testdaten.random.HasDistribution;

/**
 * Generate number for 0 to the max value of the {@link Distribution}
 * 
 * @author tnill
 *
 */
public class RangeCreator implements ValueCreator<Integer>, HasDistribution {
    private Distribution distribution;
    
	public RangeCreator(Distribution distribution) {
		super();
		this.distribution = distribution;
	}



	public RangeCreator(int grenze) {
       this(new EqualDistribution(grenze));
    }
    
 

    @Override
    public Integer generateValue(Values values) {
        return Integer.valueOf(distribution.randomNumberLowerOrEqualsThenMax());
     }

    @Override
	public void setDistribution(Distribution distribution) {
    	this.distribution = distribution;
    }



}
