package thomas.nill.testdaten;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.NonNull;
import thomas.nill.testdaten.random.Distribution;
import thomas.nill.testdaten.random.HasDistribution;

public class TimeSerie implements HasDistribution{

	protected RangeCreator timeStep;
	protected LocalDateTime time;
	protected ChronoUnit unit;

	public TimeSerie(int count,int maxStep,@NonNull String unitName) {
    	super();
    	this.unit = ChronoUnit.valueOf(unitName);
    	this.timeStep = new RangeCreator(maxStep);
    }

	
	protected LocalDateTime replaceNewValue(@NonNull LocalDateTime newValue) {
		time = newValue; 
    	return time;
	}
	
	


	@Override
	public void setDistribution(@NonNull Distribution distribution) {
		timeStep.setDistribution(distribution);		
	}

}