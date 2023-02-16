
package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

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
		System.out.printf("Number of %s between %s and %s is %d", unit,
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
		LocalDate fridayDate = LocalDate.parse("2023-02-17");
		NextFridayPrinting(date, fridayDate);
		date = LocalDate.parse("2023-10-13");
		NextFridayPrinting(date, fridayDate);
		date = LocalDate.parse("2024-09-14");
		NextFridayPrinting(date, fridayDate);
			
	}
	
	private void NextFridayPrinting(LocalDate date, LocalDate fridayDate) {
		do {
			date = date.with(new NextFriday13());
		}
		while (!date.getDayOfWeek().equals(fridayDate.getDayOfWeek()) );
		System.out.println("\n" + "next Friday 13 - " + date);
	}

	@Test
	void displayCurrentDateTimeCanadaTimeZones () {
		//displaying current local date and time for all Canada time zones
		//displaying should contains time zone name
		ZoneId zoneId1 = ZoneId.of("GMT-5");
		ZoneId zoneId2 = ZoneId.of("GMT-6");
		ZoneId zoneId3 = ZoneId.of("GMT-7");
		LocalDateTime now = LocalDateTime.now(zoneId1);
		System.out.println("\n" + "Time for time zone GMT-5 - " + now);
		now = LocalDateTime.now(zoneId2);
		System.out.println("Time for time zone GMT-6 - " + now);
		now = LocalDateTime.now(zoneId3);
		System.out.println("Time for time zone GMT-7 - " + now);
		
	}

}
