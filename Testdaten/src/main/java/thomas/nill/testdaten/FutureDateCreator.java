package thomas.nill.testdaten;

import java.time.LocalDate;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;
/**
 * Create a {@link LocalDate} in the future
 * 
 * @author tnill
 *
 * 
 */

public class FutureDateCreator implements ValueCreator<LocalDate>, HasDistribution{
	private RangeCreator daysInTheFuture;
	
    public FutureDateCreator(int daysInTheFuture) {
    	super();
    	if (daysInTheFuture<1) {
    		throw new IllegalArgumentException("daysInTheFuture > 0 not " + daysInTheFuture);
    	}
    	this.daysInTheFuture = new RangeCreator(daysInTheFuture-1);
    }

    @Override
    public LocalDate generateValue(@NonNull Values values) {
    	return LocalDate.now().plusDays(1+daysInTheFuture.generateValue(values));
    }

	@Override
	public void setDistribution(@NonNull Distribution distribution) {
		daysInTheFuture.setDistribution(distribution);
	}


}
