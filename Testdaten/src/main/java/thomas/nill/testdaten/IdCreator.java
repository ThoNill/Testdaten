package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class IdCreator implements ValueCreator<Long> {
    long id;
    
    public IdCreator(int start) {
       this.id = start;
    }
    
    @Override
    public Long generateValue(Values values) {
        return Long.valueOf(id++);
     }

}
