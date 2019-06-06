package thomas.nill.testdaten;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

/**
 * Create a long ID
 * 
 * @author tnill
 *
 */
public class IdCreator implements ValueCreator<Long> {
    private long id;
    
    public IdCreator(int start) {
       this.id = start;
    }
    
    @Override
    public Long generateValue(@NonNull Values values) {
        return Long.valueOf(id++);
    }

}
