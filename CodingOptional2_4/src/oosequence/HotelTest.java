package oosequence;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class HotelTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
		
	// Testing constructors
	@Test
	public void test_Constructor_validDates(){
		 

		Hotel c = new Hotel(getDate(2019,02,28,0,0), getDate(2019,03,04,0,0), "Hotel California");
		assertEquals("Created hotel starting March 28 to April 4 -testing start", "Thu Mar 28 00:00:00 MDT 2019", c.getStart());
		assertEquals("Created hotel starting March 28 to April 4 -testing end", "Thu Apr 04 00:00:00 MDT 2019", c.getEnd());
		assertEquals("Created hotel starting March 28 to April 4 -testing name", "Hotel California", c.getName());
	}
	@Test 
	public void testCopyConstructor()
	{
		 
		Hotel c = new Hotel(getDate(2019,02,28,0,0), getDate(2019,03,04,0,0), "Marriott");
		Hotel c1 = new Hotel(c);
		assertEquals("Copied hotel starting March 28 to April 4 -testing start", "Thu Mar 28 00:00:00 MDT 2019", c1.getStart());
		assertEquals("Copied hotel starting March 28 to April 4 -testing end", "Thu Apr 04 00:00:00 MDT 2019", c1.getEnd());
		assertEquals("Copied hotel starting March 28 to April 4 -testing name", "Marriott", c1.getName());
	}

	@Test
	public void test_getDuration_roundUp() {
		 
		Hotel c = new Hotel(getDate(2018,11,24,17,20), getDate(2018,11,29,5,30), "Test");
		String expectedDuration = "5 days";
		assertEquals("Hotel stay is 4.5 days long", expectedDuration, c.getDuration());		
	}

	@Test
	public void test_getDuration_roundDown() {
		 
		Hotel c = new Hotel(getDate(2018,11,24,17,20), getDate(2018,11,29,5,19), "Test");
		String expectedDuration = "4 days";
		assertEquals("Hotel stay is just under 4.5 days long", expectedDuration, c.getDuration());		
	}

	@Test 
	public void test_getDuration_LessThan24Hours() {
		 
		Hotel c = new Hotel(getDate(2018,11,28,10,20), getDate(2018,11,29,9,20), "Test");
		String expectedDuration = "1 day";
		assertEquals("Hotel stay is 23 hours", expectedDuration, c.getDuration());		
	}

	@Test
	public void test_getDuration_endNull() {
		 
		Hotel c = new Hotel(getDate(2018,11,28,10,20), null, "test");
		String expectedDuration = "1 day";
		assertEquals("Hotel is unknown (null end) long", expectedDuration, c.getDuration());		
	}
	
	
	// test toString (ensuring toString invokes overridden methods.)
	@Test
	public void test_toString1() {
		Hotel c = new Hotel(getDate(2018,11,24,17,20), getDate(2018,11,29,5,19), "Test Name");
		String expectedDuration = "4 days";

		String expectedString = "Hotel Test Name\tStart: Mon Dec 24 17:20:00 MST 2018\tEnd: Sat Dec 29 05:19:00 MST 2018\tLength: 4 days";
		assertEquals("Expecting format: 'Hotel: <Hotel Name><tab>Start: <date as string><tab>End: <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}
	
	@Test
	public void test_toString2() {
		Hotel c = new Hotel(getDate(2019,02,28,0,0), getDate(2019,03,04,0,0), "Hotel California");
		String expectedDuration = "7 days";

		String expectedString = "Hotel Hotel California\tStart: Thu Mar 28 00:00:00 MDT 2019\tEnd: Thu Apr 04 00:00:00 MDT 2019\tLength: 7 days";
		assertEquals("Expecting format: 'Hotel: <Hotel Name><tab>Start: <date as string><tab>End: <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}
	
	@Test
	public void test_conflictsWith_OtherDoesNotAllowOverlap() {
		 
		Hotel c = new Hotel(getDate(2019,02,28,0,0), getDate(2019,03,04,0,0), "Hotel California");
		TripComponent other = new TripComponent(getDate(2019,02,29,10,20), getDate(2019,02,30,11,30)){
			public String getDuration() {return null;}
			protected boolean overlapAllowed() {return false;}
		};
		assertFalse("Assuming no conflict with overlapping TripComponent that does not allow overlap.", c.conflictsWith(other));
		assertFalse("Assuming no conflict with overlapping TripComponent that does not allow overlap.", other.conflictsWith(c));
	}
}