package tests;

import thomas.nill.testdaten.DateCreator;
import thomas.nill.testdaten.FormatCreator;
import thomas.nill.testdaten.RangeCreator;

public class ZeitCreator extends FormatCreator{
    public ZeitCreator() {
       super("%s %02d:%02d",new DateCreator(),new RangeCreator(23),new RangeCreator(59));
    }

    
}
