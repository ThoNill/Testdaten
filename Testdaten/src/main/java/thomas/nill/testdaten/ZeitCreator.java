package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.Values;

public class ZeitCreator extends FormatCreator{
    public ZeitCreator() {
       super("%s %02d:%02d",new DatumCreator(),new NummernBereichCreator(23),new NummernBereichCreator(59));
    }

    
}
