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
        int jahr = 1980 + jahrGenerator.zuf�lligeZahlBisZurGrenze();
        int monat = 1 + monatGenerator.zuf�lligeZahlBisZurGrenze();
        int tag = 1 + tagGenerator.zuf�lligeZahlBisZurGrenze();
        
        return String.format("%02d.%02d.%d", tag,monat,jahr);
    }

}
