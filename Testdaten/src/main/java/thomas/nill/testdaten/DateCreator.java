package thomas.nill.testdaten;

import java.util.Date;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
/**
 * Create a {@link Date}
 * 
 * @author tnill
 *
 * @param <K>
 */

public class DateCreator implements ValueCreator<String>{
    RangeCreator jahrGenerator = new RangeCreator(50);
    RangeCreator monatGenerator = new RangeCreator(11);
    RangeCreator tagGenerator = new RangeCreator(27);
    
    public DateCreator() {
       
    }

    @Override
    public String generateValue(Values values) {
        int jahr = 1980 + jahrGenerator.generateValue(values);
        int monat = 1 + monatGenerator.generateValue(values);
        int tag = 1 + tagGenerator.generateValue(values);
        
        return String.format("%02d.%02d.%d", tag,monat,jahr);
    }

}
