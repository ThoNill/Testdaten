package thomas.nill.testdaten;

import java.lang.reflect.InvocationTargetException;
import java.util.function.IntToDoubleFunction;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ConstructorHelper;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.ArrayDistribution;
import thomas.nill.testdaten.random.Distribution;


/**
 * A creator that creates a function that describes a {@link Distribution}
 * 
 * @author tnill
 *
 * 
 */

public class DistributionFunctionCreator implements ValueCreator<Distribution> {
	private Distribution distribution;


	
	public DistributionFunctionCreator(@NonNull Class<?> clazz, int max,@NonNull String[] args) {
		super();
		if (max<1) {
    		throw new IllegalArgumentException("max > 0 not " + max);
    	}
		try {
			Object obj = new ConstructorHelper().searchConstructorAndCreate(clazz, args);
			if (!(obj instanceof IntToDoubleFunction)) {
				throw new TestdataException("The Class "+clazz.getSimpleName() + " is not a Function<Integer,Double> ");
			}
			IntToDoubleFunction fun = (IntToDoubleFunction)obj;
			distribution = new ArrayDistribution(max,fun);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new TestdataException("The Class " + clazz + " has not a constructor for " + args.length
					+ " arguments or an argument and the class of the argument did not match");
		}
	}

	@Override
	public Distribution generateValue(@NonNull Values v) {
		return distribution;
	}


}
