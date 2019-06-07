package thomas.nill.testdaten;

import java.time.LocalDateTime;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a decreasing sequence of {@link LocalDateTime} items
 * 
 * @author tnill
 *
 * 
 */

public class DecreasingTimeCreator extends TimeSerie implements ValueCreator<LocalDateTime> {

	public DecreasingTimeCreator(int count, int maxStep, String unitName) {
		super(maxStep-1, unitName);
		if (count<0) {
    		throw new IllegalArgumentException("count > 0 not " + count);
    	}
    
		time = LocalDateTime.now();
	}

	@Override
	public LocalDateTime generateValue(@NonNull Values values) {
		return replaceNewValue(time.minus(1L+timeStep.generateValue(values), unit));
	}

	
}
