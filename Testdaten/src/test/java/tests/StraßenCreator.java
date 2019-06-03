package tests;

import thomas.nill.testdaten.FormatCreator;
import thomas.nill.testdaten.LookupCreator;
import thomas.nill.testdaten.RangeCreator;
import thomas.nill.testdaten.basis.ValueCreatorFabrik;

public class Stra�enCreator extends LookupCreator<String> {
	public Stra�enCreator(ValueCreatorFabrik fabrik) {
       super("streetandnumber",
    		   new FormatCreator("%s %2d",  
    				   fabrik.searchCreator("street")
    				   ,new RangeCreator(50)));
    }

}
