package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class ZahlCreator implements ValueCreator<String> {
    int länge = 10;
    private NummernBereichCreator bereich;
    
    public ZahlCreator(int länge) {
        super();    
        this.länge = länge;
        this.bereich = new NummernBereichCreator(9);
    }

    
    @Override
    public String generateValue(Values values) {
        StringBuilder builder = new StringBuilder(länge);
        for(int i=0;i<länge;i++) {
            builder.append(bereich.zufälligeZahlBisZurGrenze());
        }
        return builder.toString();
    }
}
