package thomas.nill.testdaten;

import java.lang.reflect.InvocationTargetException;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ConstructorHelper;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;

/**
 * A creator that creates a {@link Distribution}
 * 
 * @author tnill
 *
 * 
 */

public class DistributionCreator implements ValueCreator<Distribution> {
	private Distribution distribution;
	
	public DistributionCreator(@NonNull Class<?> clazz,@NonNull  String[] args) {
		super();
		try {
			Object obj = new ConstructorHelper().searchConstructorAndCreate(clazz, args);
			if (!(obj instanceof Distribution)) {
				throw new TestdataException("The Class "+clazz.getSimpleName() + " is not a " + Distribution.class.getSimpleName());
			}
			distribution = (Distribution)obj;
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			throw new TestdataException("The Class " + clazz + " has not a constructor for " + args.length
					+ " arguments or an argument and the class of the argument did not match");
		}
	}

	@Override
	public Distribution generateValue(@NonNull Values v) {
		return distribution;
	}


}
