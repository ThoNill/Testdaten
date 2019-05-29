package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class DatumCreator implements ValueCreator<String>{
    NummernBereichCreator jahrGenerator = new NummernBereichCreator(50);
    NummernBereichCreator monatGenerator = new NummernBereichCreator(11);
    NummernBereichCreator tagGenerator = new NummernBereichCreator(27);
    
    public DatumCreator() {
       
    }

    @Override
    public String generateValue(Values values) {
        int jahr = 1980 + jahrGenerator.zufälligeZahlBisZurGrenze();
        int monat = 1 + monatGenerator.zufälligeZahlBisZurGrenze();
        int tag = 1 + tagGenerator.zufälligeZahlBisZurGrenze();
        
        return String.format("%02d.%02d.%d", tag,monat,jahr);
    }

}
