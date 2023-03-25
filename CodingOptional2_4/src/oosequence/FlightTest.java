package oosequence;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class FlightTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
		
	// Testing constructors
	@Test
	public void test_Constructor_validAirports(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "YYC", "YVR");
		assertEquals("Created flight starting in YYC and ending in YVR, testing departure", "YYC", c.getDepartureAirport());
		assertEquals("Created flight starting in YYC and ending in YVR, testing arrive", "YVR", c.getArrivalAirport());
	}

	@Test
	public void test_Constructor_InvalidDeparture(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "YY", "YVR");
		assertEquals("Created flight starting in YY and ending in YVR, testing departure", "", c.getDepartureAirport());
		assertEquals("Created flight starting in YY and ending in YVR, testing arrive", "YVR", c.getArrivalAirport());

		c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "AAAA", "YVR");
		assertEquals("Created flight starting in AAAA and ending in YVR, testing departure", "", c.getDepartureAirport());
		assertEquals("Created flight starting in AAAA and ending in YVR, testing arrive", "YVR", c.getArrivalAirport());
	
	}

	@Test
	public void test_Constructor_InvalidArrival(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "CAN", "BB");
		assertEquals("Created flight starting in CAN and ending in BB, testing departure", "CAN", c.getDepartureAirport());
		assertEquals("Created flight starting in CAN and ending in BB, testing arrive", "", c.getArrivalAirport());

		c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "CAN", "ABCD");
		assertEquals("Created flight starting in CAN and ending in ABCD, testing departure", "CAN", c.getDepartureAirport());
		assertEquals("Created flight starting in CAN and ending in ABCD, testing arrive", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_Constructor_nullArrivalAndDeparture(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), null, null);
		assertEquals("", c.getDepartureAirport());
		assertEquals("", c.getArrivalAirport());
	}
	
	@Test 
	public void testCopyConstructor1()
	{
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "LEX", "LCA");
		Flight c1 = new Flight(c);
		assertEquals("testing start date", "LEX Fri Dec 28 10:20:00 MST 2018", c1.getStart());
		assertEquals("testing end date", "LCA Fri Dec 28 10:21:00 MST 2018", c1.getEnd());
		assertEquals("testing departure airport", "LEX", c1.getDepartureAirport());
		assertEquals("testing arrival airport", "LCA", c1.getArrivalAirport());
	}

	@Test 
	public void testCopyConstructor2()
	{
		 
		Flight c = new Flight(getDate(2018,11,31,23,35), getDate(2019,0,1,10,21), "CAN", "AMS");
		Flight c1 = new Flight(c);
		assertEquals("testing start date", "CAN Mon Dec 31 23:35:00 MST 2018", c1.getStart());
		assertEquals("testing end date", "AMS Tue Jan 01 10:21:00 MST 2019", c1.getEnd());
		assertEquals("testing departure airport", "CAN", c1.getDepartureAirport());
		assertEquals("testing arrival airport", "AMS", c1.getArrivalAirport());
	}

	// test setters and getters
	
	public void test_getter_setter_departureAirport_validAirport(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "YYC", "YVR");
		c.setDepartureAirport("ABR");
		assertEquals("Changed departure airport from YYC to ABR", "ABR", c.getDepartureAirport());
	}

	public void test_getter_setter_arrivalAirport_validAirport(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "YYC", "YVR");
		c.setArrivalAirport("BAC");
		assertEquals("Changed arrival airport from YVR to BAC", "BAC", c.getDepartureAirport());
	}

	@Test
	public void test_getter_setter_departureAirport_Invalid(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "YYY", "YVR");
		c.setDepartureAirport("YY");
		assertEquals("Changed departure airport to YY from YYY", "", c.getDepartureAirport());

		c.setDepartureAirport("YYY");
		c.setDepartureAirport("WXYZ");
		assertEquals("Changed departure airport from YYY to WXYZ", "", c.getDepartureAirport());
	}
	
	
	@Test
	public void test_getter_setter_arrivalAirport_Invalid(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "CAN", "IAB");
		c.setArrivalAirport("BB");
		assertEquals("Changed arrival airport from IAB to BB", "", c.getArrivalAirport());

		c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "CAN", "IAB");
		c.setArrivalAirport("BABA");
		assertEquals("Changed arrival airport from IAB to BABA", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getter_setter_departureAirport_null(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "ABC", "DEF");
		c.setDepartureAirport(null);
		assertEquals("Changed departure airport from ABC to null", "", c.getDepartureAirport());
	}
	
	@Test
	public void test_getter_setter_arrivalAirport_null(){
		 

		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21), "ABC", "DEF");
		c.setArrivalAirport(null);
		assertEquals("Changed arrival airport from DEF to null", "", c.getArrivalAirport());
	}
	
	@Test
	public void test_getDuration_startOneHourAndTenMinutesBeforeEnd() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,11,30), "ABC", "DEF");
		String expectedDuration = "1 hour and 10 minutes";
		assertEquals("Flight is one hour and 10 minutes long", expectedDuration, c.getDuration());		
	}

	@Test
	public void test_getDuration_startFiveHoursBeforeEndWithOvernight() {
		 
		Flight c = new Flight(getDate(2018,11,28,22,20), getDate(2018,11,29,3,20), "ABC", "DEF");
		String expectedDuration = "5 hours";
		assertEquals("Flight is five hours long", expectedDuration, c.getDuration());		
	}

	@Test
	public void test_getDuration_start45MinutesBeforeEnd() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,11,05), "ABC", "DEF");
		String expectedDuration = "45 minutes";
		assertEquals("Flight is 45 minutes long", expectedDuration, c.getDuration());		
	}

	@Test
	public void test_getDuration_endNull() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), null, "ABC", "DEF");
		String expectedDuration = "0 minutes";
		assertEquals("Flight is unknown (null end) long", expectedDuration, c.getDuration());		
	}
	
	
	// test toString (ensuring toString invokes overridden methods.)
	@Test
	public void test_toString_endNull() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), null, "ABC", "DEF");
		String expectedString = "Start: ABC Fri Dec 28 10:20:00 MST 2018\tEnd: DEF \tLength: 0 minutes";
		assertEquals("Expecting format: 'Start: <departure airport> <date as string><tab>End: <arrival airport> <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}
	
	@Test
	public void test_toString_startOneHourTenMinutesBeforeEnd() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,11,30), "YYC", "YVR");
		String expectedString = "Start: YYC Fri Dec 28 10:20:00 MST 2018\tEnd: YVR Fri Dec 28 11:30:00 MST 2018\tLength: 1 hour and 10 minutes";
		assertEquals("Expecting format: 'Start: <departure airport> <date as string><tab>End: <arrival airport> <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}	
	
	@Test
	public void test_conflictsWith_OtherAllowsOverlap() {
		 
		Flight c = new Flight(getDate(2018,11,28,10,20), getDate(2018,11,28,11,30), "YYC", "YVR");
		TripComponent other = new TripComponent(getDate(2018,11,28,10,20), getDate(2018,11,28,11,30)){
			public String getDuration() {return null;}
			protected boolean overlapAllowed() {return true;}
		};
		assertFalse("Assuming no conflict with overlapping TripComponent that allows overlap.", c.conflictsWith(other));
		assertFalse("Assuming no conflict with overlapping TripComponent that allows overlap.", other.conflictsWith(c));
	}
}