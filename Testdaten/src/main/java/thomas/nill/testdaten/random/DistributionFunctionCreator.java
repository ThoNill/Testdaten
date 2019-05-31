package thomas.nill.testdaten.random;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;

import thomas.nill.testdaten.ConstantCreator;
import thomas.nill.testdaten.basis.ConstructorHelper;
import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class DistributionFunctionCreator implements ValueCreator<Verteilung> {
	private Verteilung distribution;


	
	public DistributionFunctionCreator(Class<?> clazz, int max,String[] args) {
		super();
		try {
			Object obj = new ConstructorHelper().searchConstructorAndCreate(clazz, args);
			if (!(obj instanceof Function<?,?>)) {
				throw new TestdatenException("The Class "+clazz.getSimpleName() + " is not a Function<Integer,Double> ");
			}
			Function<Integer,Double> fun = (Function<Integer,Double>)obj;
			distribution = new ArrayVerteilung(max,fun);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			throw new TestdatenException("The Class " + clazz + " has not a constructor for " + args.length
					+ " arguments or an argument and the class of the argument did not match");
		}
	}

	@Override
	public Verteilung generateValue(Values v) {
		return distribution;
	}

}
