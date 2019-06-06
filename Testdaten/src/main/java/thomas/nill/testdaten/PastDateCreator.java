package thomas.nill.testdaten;

import java.time.LocalDate;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;
/**
 * Create a {@link LocalDate}
 * 
 * @author tnill
 *
 * 
 */

public class PastDateCreator implements ValueCreator<LocalDate>, HasDistribution{
	private RangeCreator daysInThePast;
	
    public PastDateCreator(int daysInThePast) {
    	super();
    	if (daysInThePast<1) {
    		throw new IllegalArgumentException("daysInThePast >= 1 not " + daysInThePast);
    	}
    
    	this.daysInThePast = new RangeCreator(daysInThePast-1);
    }

    @Override
    public LocalDate generateValue(@NonNull Values values) {
    	return LocalDate.now().minusDays(1+daysInThePast.generateValue(values));
    }

	@Override
	public void setDistribution(@NonNull Distribution distribution) {
		daysInThePast.setDistribution(distribution);
	}


}
