package thomas.nill.testdaten;

import java.time.LocalDate;

import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;
/**
 * Create a {@link LocalDate} with a year between sratYear and endYear
 * 
 * @author tnill
 *
 * 
 */

public class DateCreator extends MonthAndDay implements ValueCreator<LocalDate>, HasDistribution{
    private RangeCreator yearGenerator;
    private int startYear;
    
    public DateCreator(int startYear,int endYear) {
    	if (endYear< startYear) {
    		throw new IllegalArgumentException("the endYear " + endYear + " is smaler then the startYear "+startYear);
    	}
    	this.startYear=startYear;
    	this.yearGenerator = new RangeCreator(endYear-startYear);
    }

    @Override
    public LocalDate generateValue(Values values) {
        int year = startYear + yearGenerator.generateValue(values);
        return extendYearToDate(values, year);
    }

	@Override
	public void setDistribution(Distribution distribution) {
		yearGenerator.setDistribution(distribution);
	}

}
