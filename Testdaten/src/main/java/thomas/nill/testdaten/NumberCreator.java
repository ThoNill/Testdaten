package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class NumberCreator implements ValueCreator<String> {
    int länge = 10;
    private RangeCreator bereich;
    
    public NumberCreator(int länge) {
        super();    
        this.länge = länge;
        this.bereich = new RangeCreator(9);
    }

    
    @Override
    public String generateValue(Values values) {
        StringBuilder builder = new StringBuilder(länge);
        for(int i=0;i<länge;i++) {
            builder.append(bereich.generateValue(values));
        }
        return builder.toString();
    }
}
