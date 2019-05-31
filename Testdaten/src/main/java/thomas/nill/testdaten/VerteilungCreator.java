package thomas.nill.testdaten;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import thomas.nill.testdaten.basis.ConstructorHelper;
import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Verteilung;

public class VerteilungCreator implements ValueCreator<Verteilung> {
	private Verteilung verteilung;


	
	public VerteilungCreator(Class<?> clazz, String[] args) {
		super();
		try {
			Object obj = new ConstructorHelper().searchConstructorAndCreate(clazz, args);
			if (!(obj instanceof Verteilung)) {
				throw new TestdatenException("The Class "+clazz.getSimpleName() + " is not a " + Verteilung.class.getSimpleName());
			}
			verteilung = (Verteilung)obj;
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			throw new TestdatenException("The Class " + clazz + " has not a constructor for " + args.length
					+ " arguments or an argument and the class of the argument did not match");
		}
	}

	@Override
	public Verteilung generateValue(Values v) {
		return verteilung;
	}

}
