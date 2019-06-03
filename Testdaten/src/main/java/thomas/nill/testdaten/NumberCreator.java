package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class NumberCreator implements ValueCreator<String> {
    int l�nge = 10;
    private RangeCreator bereich;
    
    public NumberCreator(int l�nge) {
        super();    
        this.l�nge = l�nge;
        this.bereich = new RangeCreator(9);
    }

    
    @Override
    public String generateValue(Values values) {
        StringBuilder builder = new StringBuilder(l�nge);
        for(int i=0;i<l�nge;i++) {
            builder.append(bereich.generateValue(values));
        }
        return builder.toString();
    }
}
