package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class TelefonCreator extends LookupCreator<String>{
   
    public TelefonCreator() {
    	super("phone",new FormatCreator("%s-%s", new ZahlCreator(4),new ZahlCreator(7)));
    }
    

}
