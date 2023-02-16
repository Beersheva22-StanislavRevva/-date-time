package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
	Temporal res = null;
	if (temporal.get(ChronoField.DAY_OF_MONTH) > 13) {
		res = temporal.with(ChronoField.DAY_OF_MONTH, 13).plus(1, ChronoUnit.MONTHS);
	}
	if (temporal.get(ChronoField.DAY_OF_MONTH) == 13) {
		res = temporal.plus(1, ChronoUnit.MONTHS);
	}
	if (temporal.get(ChronoField.DAY_OF_MONTH) < 13) {
		res =  temporal.with(ChronoField.DAY_OF_MONTH, 13);
	}
		
	return res;
			
	}

}
