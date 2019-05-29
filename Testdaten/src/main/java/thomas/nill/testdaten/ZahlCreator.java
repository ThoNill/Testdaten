package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class ZahlCreator implements ValueCreator<String> {
    int l�nge = 10;
    private NummernBereichCreator bereich;
    
    public ZahlCreator(int l�nge) {
        super();    
        this.l�nge = l�nge;
        this.bereich = new NummernBereichCreator(9);
    }

    
    @Override
    public String generateValue(Values values) {
        StringBuilder builder = new StringBuilder(l�nge);
        for(int i=0;i<l�nge;i++) {
            builder.append(bereich.zuf�lligeZahlBisZurGrenze());
        }
        return builder.toString();
    }
}
