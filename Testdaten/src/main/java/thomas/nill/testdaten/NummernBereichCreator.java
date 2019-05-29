package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class NummernBereichCreator implements ValueCreator<Integer> {
    int grenze = 10;
    
    public NummernBereichCreator(int grenze) {
        if (grenze<=0) {
            throw new IllegalArgumentException("Grenze sollte > 0 sein");
        }
        this.grenze = grenze;
    }
    
    protected int zufälligeZahlBisZurGrenze() {
        int n = (int)(Math.random() * (grenze+1));
        return (n > grenze) ? grenze : n;
    }

    @Override
    public Integer generateValue(Values values) {
        return Integer.valueOf(zufälligeZahlBisZurGrenze());
     }

}
