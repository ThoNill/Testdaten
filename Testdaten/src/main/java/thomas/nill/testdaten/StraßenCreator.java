package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreatorFabrik;

public class Stra�enCreator extends LookupCreator<String> {
	public Stra�enCreator(ValueCreatorFabrik fabrik) {
       super("streetandnumber",
    		   new FormatCreator("%s %2d",  
    				   fabrik.searchCreator("street")
    				   ,new NummernBereichCreator(50)));
    }

}
