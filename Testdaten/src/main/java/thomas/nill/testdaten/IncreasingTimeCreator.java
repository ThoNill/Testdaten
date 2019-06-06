package thomas.nill.testdaten;

import java.time.LocalDateTime;

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
	public IncreasingTimeCreator(int count,int maxStep,String unitName) {
    	super(count,maxStep-1,unitName);
    	time = LocalDateTime.now().minus(count * maxStep,unit);
    }

	@Override
	public LocalDateTime generateValue(Values values) {
		return replaceNewValue(time.plus(1+timeStep.generateValue(values), unit));
	}

}