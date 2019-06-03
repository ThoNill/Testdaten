package thomas.nill.testdaten;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

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
 * @param <K>
 */

public class DistributionFunctionCreator implements ValueCreator<Distribution> {
	private Distribution distribution;


	
	public DistributionFunctionCreator(Class<?> clazz, int max,String[] args) {
		super();
		try {
			Object obj = new ConstructorHelper().searchConstructorAndCreate(clazz, args);
			if (!(obj instanceof Function<?,?>)) {
				throw new TestdataException("The Class "+clazz.getSimpleName() + " is not a Function<Integer,Double> ");
			}
			Function<Integer,Double> fun = (Function<Integer,Double>)obj;
			distribution = new ArrayDistribution(max,fun);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			throw new TestdataException("The Class " + clazz + " has not a constructor for " + args.length
					+ " arguments or an argument and the class of the argument did not match");
		}
	}

	@Override
	public Distribution generateValue(Values v) {
		return distribution;
	}

}
