package thomas.nill.testdaten;

import java.time.LocalDateTime;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
/**
 * Create a increasing sequence of {@link LocalDateTime} items
 * 
 * @author tnill
 *
 * 
 */

public class IncreasingTimeCreator extends TimeSerie implements ValueCreator<LocalDateTime>{
	public IncreasingTimeCreator(int count,int maxStep,@NonNull String unitName) {
    	super(maxStep-1,unitName);
    	if (count<0) {
    		throw new IllegalArgumentException("count > 0 not " + count);
    	}
    	time = LocalDateTime.now().minus((long)(count * maxStep),unit);
    }

	@Override
	public LocalDateTime generateValue(@NonNull Values values) {
		return replaceNewValue(time.plus(1L+timeStep.generateValue(values), unit));
	}

}
