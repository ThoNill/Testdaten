package thomas.nill.testdaten;

import java.util.ArrayList;
import java.util.List;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a list of beans, with the help of a {@link BeanCreator}
 * 
 * @author tnill
 *
 * 
 */
public class BeanListCreator<K>  implements ValueCreator<List<K>>{
	private BeanCreator<K> creator;
	private RangeCreator nummern;
	
	public BeanListCreator(Class<?> clazz,int count) {
		super();
		creator = new BeanCreator<>(clazz);
		nummern= new RangeCreator(count);
	}
	
	@Override
	public List<K> generateValue(Values v) {
		List<K> list = new ArrayList<>();
		int count = nummern.generateValue(v);
		for(int i=0;i<count;i++) {
			list.add(creator.generateValue(new Values()));
		}
		return list;
	}

}
