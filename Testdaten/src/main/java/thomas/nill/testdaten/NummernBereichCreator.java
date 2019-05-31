package thomas.nill.testdaten;

import thomas.nill.testdaten.basis.HasVerteilung;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Gleichverteilung;
import thomas.nill.testdaten.random.Verteilung;

public class NummernBereichCreator implements ValueCreator<Integer>, HasVerteilung {
    private Verteilung verteilung;
    



	public NummernBereichCreator(Verteilung verteilung) {
		super();
		this.verteilung = verteilung;
	}



	public NummernBereichCreator(int grenze) {
       this(new Gleichverteilung(grenze));
    }
    
 

    @Override
    public Integer generateValue(Values values) {
        return Integer.valueOf(verteilung.zufälligeZahlBisZurGrenze());
     }

    public void setVerteilung(Verteilung verteilung) {
    	this.verteilung = verteilung;
    }


}
