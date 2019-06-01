package thomas.nill.testdaten;

import java.util.Locale;
import java.util.ResourceBundle;

import thomas.nill.testdaten.basis.TestdatenException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.HasVerteilung;
import thomas.nill.testdaten.random.Verteilung;

public class StringListeCreator implements ValueCreator<String>, HasVerteilung{
    
	private String texte[];
    private NummernBereichCreator bereich;
    
    public StringListeCreator(String text) {
        super();
        this.texte = text.split(" *[\\|] *");
        bereich = new NummernBereichCreator(texte.length-1);
    }
   
	@Override
	public String generateValue(Values v) {
		return texte[bereich.generateValue(v)];
	}

	@Override
	public void setVerteilung(Verteilung verteilung) {
		if (texte.length < verteilung.getGrenze()) {
			throw new TestdatenException("Anzahl der Texte " + (texte.length+1) + " passen nicht zur Verteilung " + verteilung.getGrenze());
		}
		bereich.setVerteilung(verteilung);
	}

}
