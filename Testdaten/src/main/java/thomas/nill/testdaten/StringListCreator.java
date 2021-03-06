package thomas.nill.testdaten;

import lombok.NonNull;
import thomas.nill.testdaten.basis.TestdataException;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;
/**
 * Selects a String from am Array of Strings separated with a |
 * 
 * @author tnill
 *
 */
public class StringListCreator implements ValueCreator<String>, HasDistribution{
    
	private String[] texte;
    private RangeCreator bereich;
    
    public StringListCreator(@NonNull String text) {
        super();
        this.texte = text.split(" *[\\|] *");
        bereich = new RangeCreator(texte.length-1);
    }
   
	@Override
	public String generateValue(@NonNull Values v) {
		return texte[bereich.generateValue(v)];
	}

	@Override
	public void setDistribution(@NonNull Distribution distribution) {
		if (texte.length < distribution.getMax()) {
			throw new TestdataException("Anzahl der Texte " + (texte.length+1) + " passen nicht zur Distribution " + distribution.getMax());
		}
		bereich.setDistribution(distribution);
	}


}
