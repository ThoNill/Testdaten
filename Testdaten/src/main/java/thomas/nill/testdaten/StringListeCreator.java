package thomas.nill.testdaten;

import java.util.Locale;
import java.util.ResourceBundle;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;

public class StringListeCreator implements ValueCreator<String>{
    
	private String texte[];
    private NummernBereichCreator bereich;
    
    public StringListeCreator(String text) {
        super();
        this.texte = text.split(" *[\\|] *");
        bereich = new NummernBereichCreator(texte.length-1);
    }
   
	@Override
	public String generateValue(Values v) {
		return texte[bereich.zufälligeZahlBisZurGrenze()];
	}

}
