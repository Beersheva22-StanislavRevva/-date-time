
package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d");
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d \n \n", unit,
				birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
		
	}
	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}
	
	@Test
	void NextFriday13Test() {
		LocalDate date = LocalDate.parse("2023-10-12");
		int dayOfWeek = 5;
		NextFridayPrinting(date, dayOfWeek );
		date = LocalDate.parse("2023-10-13");
		NextFridayPrinting(date, dayOfWeek);
		date = LocalDate.parse("2024-09-14");
		NextFridayPrinting(date, dayOfWeek);
			
	}
	
	private void NextFridayPrinting(LocalDate date, int dayOfWeek) {
		do {
			date = date.with(new NextFriday13());
		}
		while (date.get(ChronoField.DAY_OF_WEEK) != dayOfWeek);
		System.out.println("next Friday 13 - " + date);
	}

	@Test
	void displayCurrentDateTimeCanadaTimeZones () {
		//displaying current local date and time for all Canada time zones
		//displaying should contains time zone name
		List<String> list = ZoneId.getAvailableZoneIds().stream().filter(n -> n.startsWith("Canada")).sorted().toList();
		for (String s : list) {
			ZoneId zoneId = ZoneId.of(s);
			System.out.printf("%s		%s \n", zoneId, LocalDateTime.now(zoneId));
		}
		System.out.println();
	}
	
	@Test
	void WorkingDaysTest() {
		LocalDate date = LocalDate.parse("2023-02-17");
		System.out.println("Starting date  " + date);
		date = date.with(new WorkingDays(new DayOfWeek[]{DayOfWeek.SATURDAY}, 10));
		System.out.println("Date + 10 working days (1 dayoff) " + date);
		date = date.with(new WorkingDays(new DayOfWeek[]{DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY}, 10));
		System.out.println("Date + 10 working days (7 dayoffs) " + date);
		System.out.println();
	}

}
