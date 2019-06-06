package thomas.nill.testdaten;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
/**
 * Generate numbers with a maximal length.
 * @author tnill
 *
 */
public class NumberCreator implements ValueCreator<String> {
    int length = 10;
    private RangeCreator bereich;
    
    public NumberCreator(int length) {
        super();    
        this.length = length;
    	if (length<0) {
    		throw new IllegalArgumentException("length > 0 not " + length);
    	}
    
        this.bereich = new RangeCreator(9);
    }

    
    @Override
    public String generateValue(@NonNull Values values) {
        StringBuilder builder = new StringBuilder(length);
        for(int i=0;i<length;i++) {
            builder.append(bereich.generateValue(values));
        }
        return builder.toString();
    }

}
