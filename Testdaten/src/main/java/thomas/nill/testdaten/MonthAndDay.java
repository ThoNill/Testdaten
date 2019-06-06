package thomas.nill.testdaten;

import java.time.LocalDate;
import java.time.YearMonth;

import thomas.nill.testdaten.basis.Values;

public class MonthAndDay {

	private RangeCreator monthGenerator = new RangeCreator(11);

	public MonthAndDay() {
		super();
	}

	protected LocalDate extendYearToDate(Values values, int year) {
		int month = 1 + monthGenerator.generateValue(values);
	    int maxDay = YearMonth.of(year,month).lengthOfMonth();
	    int dayOfMonth = 1 + new RangeCreator(maxDay-1).generateValue(values);
	    
	    return LocalDate.of(year, month, dayOfMonth);
	}

}