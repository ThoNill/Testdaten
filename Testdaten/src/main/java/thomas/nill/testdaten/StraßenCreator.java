package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.ValueCreatorFabrik;

public class StraﬂenCreator extends LookupCreator<String> {
	public StraﬂenCreator(ValueCreatorFabrik fabrik) {
       super("streetandnumber",
    		   new FormatCreator("%s %2d",  
    				   fabrik.searchCreator("street")
    				   ,new NummernBereichCreator(50)));
    }

}
