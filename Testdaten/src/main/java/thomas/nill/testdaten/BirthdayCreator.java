package thomas.nill.testdaten;

import java.time.LocalDate;

import lombok.NonNull;
import thomas.nill.testdaten.basis.ValueCreator;
import thomas.nill.testdaten.basis.Values;
import thomas.nill.testdaten.random.Distribution;
/**
 * Create a birthday of {@link LocalDateTime} items
 * 
 * @author tnill
 *
 * 
 */
import thomas.nill.testdaten.random.HasDistribution;

/**
 * Create birthday between a starAge and a endAge
 * 
 * @author tnill
 *
 */
public class BirthdayCreator extends MonthAndDay implements ValueCreator<LocalDate>, HasDistribution {
    private RangeCreator yearGenerator;
    private int startAge;
    
    public BirthdayCreator(int startAge,int endAge) {
    	super();
    	if (startAge<0) {
    		throw new IllegalArgumentException("startAge > 0 not " + startAge);
    	}
    	if (endAge<0) {
    		throw new IllegalArgumentException("endtAge > 0 not " + endAge);
    	}
    
    	if (endAge< startAge) {
    		throw new IllegalArgumentException("the endAge " + endAge + " is smaler then the startAge "+startAge);
    	}
    	this.startAge = startAge;
    	yearGenerator = new RangeCreator(endAge-startAge);
    }

    @Override
    public LocalDate generateValue(@NonNull Values values) {
    	int actualYear = LocalDate.now().getYear();
        int year = actualYear - (startAge + yearGenerator.generateValue(values));
        return extendYearToDate(values, year);
    }

	@Override
	public void setDistribution(Distribution distribution) {
		yearGenerator.setDistribution(distribution);
	}

	

}
