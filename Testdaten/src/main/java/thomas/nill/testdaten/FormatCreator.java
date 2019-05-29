package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class FormatCreator implements ValueCreator<String> {
    private ValueCreator<?>[] creators;
    private String format;
    
    public FormatCreator(String format,ValueCreator<?> ...creators) {
       this.creators = creators;
    }

    @Override
    public String generateValue(Values values) {
    	Object[] objects = new Object[creators.length];
    	for(int i=0;i< creators.length;i++) {
    		objects[i] = creators[i].generateValue(values);
    	}
        return String.format(format,objects);
    }

}
