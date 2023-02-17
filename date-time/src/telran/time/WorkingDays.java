package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class WorkingDays implements TemporalAdjuster {
private DayOfWeek[] dayOffs;
private int amount;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		int i = 0;
		int [] array = new int [dayOffs.length];
		convertToInt (dayOffs, array);
		if (dayOffs.length < 7) {
			while (i < amount) {
				if (!contains(temporal, array)) {
					i++;
				}
				temporal = temporal.plus(1, ChronoUnit.DAYS);
			}
		}
		return temporal;
	}
	private int[] convertToInt(DayOfWeek[] dayOffs, int[] array) {
		int i = 0;
		for (DayOfWeek d : dayOffs) {
			array[i] = d.getValue();
			i++;
		}
		return array;
		
	}
	private boolean contains(Temporal temporal, int[] array) {
		boolean res = false;
		for (int i = 0; i < array.length; i++) {
			if (temporal.get(ChronoField.DAY_OF_WEEK) == array[i]) {
				res = true;
			}
		}
		return res;
	}
	public WorkingDays(DayOfWeek[] dayOffs, int amount) {
		this.dayOffs = dayOffs;
		this.amount = amount;
	}

}