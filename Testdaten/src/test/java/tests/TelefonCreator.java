package tests;

import thomas.nill.testdaten.FormatCreator;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.NumberCreator;

public class TelefonCreator extends LookupCreator<String>{
   
    public TelefonCreator() {
    	super("phone",new FormatCreator("%s-%s", new NumberCreator(4),new NumberCreator(7)));
    }
    

}
