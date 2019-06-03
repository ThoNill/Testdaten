package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.EqualDistribution;
import thomas.nill.testdaten.random.HasDistribution;

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
        return Integer.valueOf(distribution.zufälligeZahlBisZurGrenze());
     }

    @Override
	public void setVerteilung(Distribution distribution) {
    	this.distribution = distribution;
    }


}
