package thomas.nill.testdaten;

import java.util.ArrayList;
import java.util.List;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class BeanListCreator<K>  implements ValueCreator<List<K>>{
	private BeanCreator<K> creator;
	private NummernBereichCreator nummern;
	
	public BeanListCreator(Class<?> clazz,int count) {
		super();
		creator = new BeanCreator<>(clazz);
		nummern= new NummernBereichCreator(count);
	}
	
	@Override
	public List<K> generateValue(Values v) {
		List<K> list = new ArrayList<>();
		int count = nummern.generateValue(v);
		for(int i=0;i<count;i++) {
			list.add(creator.generateValue(v));
		}
		return list;
	}

}
