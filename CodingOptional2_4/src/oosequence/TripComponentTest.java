package oosequence;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;

import org.junit.Test;

public class TripComponentTest{
	class TPMock extends TripComponent {
		public TPMock(Date startTime, Date endTime) {
			super(startTime, endTime);
		}
		public TPMock(TripComponent tp) {
			super(tp);
		}
		String next = "";
		boolean allowed = false;
		@Override
		public String getDuration() {
			return next;
		}
		@Override
		protected boolean overlapAllowed() {
			return allowed;
		}
	}

	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}
	
	// Testing constructors

	@Test
	public void testConstructor_startBeforeend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("start one minute before end, testing start", expectedstart.toString(), c.getStart());
		assertEquals("start one minute before end, testing end", expectedend.toString(), c.getEnd());
	}

	@Test
	public void testConstructor_startSameAsend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,27,10,20), getDate(2018,11,27,10,20));
		Date expectedstart = getDate(2018,11,27,10,20);
		Date expectedend = null;
		assertEquals("testing start", expectedstart.toString(), c.getStart());
		assertEquals("testing end (which is null, expecting empty string", "", c.getEnd());
	}

	@Test
	public void testConstructor_startAfterend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,21), getDate(2018,11,28,10,20));
		Date expectedstart = getDate(2018,11,28,10,21);
		Date expectedend = null;
		assertEquals("start one minute after end, testing start", expectedstart.toString(), c.getStart());
		assertEquals("start one minute after end, testing end", "", c.getEnd());
	}

	@Test
	public void testConstructor_nullstartAndend(){
		 
		TripComponent c = new TPMock(null,null);
		Date expectedstart = null;
		Date expectedend = null;
		assertEquals("testing start", "", c.getStart());
		assertEquals("testing end", "", c.getEnd());
	}

	@Test
	public void testConstructor_startEncapsulation(){
		 
		Date argumentstart = getDate(2019,11,28,10,25);
		TripComponent c = new TPMock(argumentstart,null);

		// Change argument
		argumentstart.setTime(getDate(2020,10,20,14,0).getTime());

		// Verify that TripComponent start date is unchanged.
		Date expectedstart = getDate(2019,11,28,10,25);

		assertEquals("Changed argument after creation from 2019:11:20:10:25 to 2020:10:20:14:00, expect TripComponent start to remain unchanged.", expectedstart.toString(), c.getStart());
	}

	@Test
	public void testCopyConstructor(){
		 
		TripComponent c = new TPMock(getDate(2018,10,28,10,20), getDate(2018,10,29,1,5));
		TripComponent copy = new TPMock(c);
		Date expectedstart = getDate(2018,10,28,10,20);
		Date expectedend = getDate(2018,10,29,1,5);
		assertEquals("Testing start", expectedstart.toString(), copy.getStart());
		assertEquals("Testing end", expectedend.toString(), copy.getEnd());
	}

	@Test
	public void testCopyConstructor_nullstartAndend(){
		 
		TripComponent p = new TPMock(null,null);
		TripComponent p2 = new TPMock(p);
		assertEquals("testing start", "", p2.getStart());
		assertEquals("testing end", "", p2.getEnd());
	}

	// Testing setter and getters

	@Test
	public void test_setter_and_getter_start_NewstartBeforeend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to 8am from 10:20am, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed start to 8am from 10:20am, testing end", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_fromNullstart(){
		 
		TripComponent c = new TPMock(null, getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start from null, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed start from null, testing end", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_toLaterThanend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setStart(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to later than end, expected unchanged start, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed start to later than end, testing end", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_Nullend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), null);
		c.setStart(getDate(2018,11,28,8,0));
		Date expectedstart = getDate(2018,11,28,8,0);
		assertEquals("Changed start to 8am from 10:20am, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed start to 8am from 10:20am, testing end", "", c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_start_setterEncapsulation(){
		 
		Date dep = getDate(2019,11,28,10,20);
		TripComponent c = new TPMock(dep, getDate(2019,11,28,11,21));

		dep.setTime(getDate(2018,11,28,8,0).getTime());

		Date expectedstart = getDate(2019,11,28,10,20);
		assertEquals("Changed start time provided as argument to setstart to 2018 from 2019, testing that start in TripComponent unchanged", expectedstart.toString(), c.getStart());
	}

	@Test
	public void test_setter_and_getter_end_NewendAfterstart(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setEnd(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,29,8,0);
		assertEquals("Changed end to 8am on the 29th from 10:21am on the 28th, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed end to 8am on the 29th from 10:21am on the 28th", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_fromNullend(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,21), null);
		c.setEnd(getDate(2018,11,29,8,0));
		Date expectedstart = getDate(2018,11,28,10,21);
		Date expectedend = getDate(2018,11,29,8,0);
		assertEquals("Changed end from null, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed end from null, testing end", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_toEarlierThanstart(){
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		c.setEnd(getDate(2018,11,27,8,0));
		Date expectedstart = getDate(2018,11,28,10,20);
		Date expectedend = getDate(2018,11,28,10,21);
		assertEquals("Changed start to later than end, testing start", expectedstart.toString(), c.getStart());
		assertEquals("Changed start to later than end, expected unchanged end, testing end", expectedend.toString(), c.getEnd());			
	}

	@Test
	public void test_setter_and_getter_end_Nullstart(){
		 
		TripComponent c = new TPMock(null, getDate(2018,11,28,10,20));
		c.setEnd(getDate(2018,11,28,8,0));
		Date expectedend = getDate(2018,11,28,8,0);
		assertEquals("Changed end to 8am from 10:20am, testing end", expectedend.toString(), c.getEnd());
		assertEquals("Changed end to 8am from 10:20am, testing start", "", c.getStart());			
	}

	@Test
	public void test_length_startOneMinuteBeforeend() {
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,21));
		long expectedLength = 1;
		assertEquals("TripComponent is one minute long", 60,c.lengthInSeconds());

	}

	@Test
	public void test_length_startOneHourBeforeend() {
		 
		TripComponent c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		long expectedLength = 3600;
		assertEquals("TripComponent is one hour long", expectedLength,c.lengthInSeconds());

	}

	@Test
	public void test_length_startNull() {
		 
		TripComponent c = new TPMock(null, getDate(2018,11,28,11,20));
		long expectedLength = 0;
		assertEquals("Null start", expectedLength,c.lengthInSeconds());

	}

	@Test
	public void test_length_endNull() {
		 
		TripComponent c = new TPMock(getDate(2018,11,28,11,20), null);
		long expectedLength = 0;
		assertEquals("Null end", expectedLength,c.lengthInSeconds());

	}
	
	@Test
	public void test_overlapsWith_ZigZag() {
		 
		TripComponent zig = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		TripComponent zag = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		assertTrue("Trip from 10:20 to 11:20 expected to overlap with trip from 11:00 to 12:00", zig.overlapsWith(zag));

		zig = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,30,9,20));
		zag = new TPMock(getDate(2018,11,29,9,30), getDate(2018,11,31,12,00));
		assertTrue("Trip from Dec 29 9:30 to Dec 31 12:00 expected to overlap with trip from Dec 28 10:20 to Dec 30 9:20", zag.overlapsWith(zig));
	}
	
	@Test
	public void test_overlapsWith_TripContainedInOtherTrip() {
		 
		TripComponent t1 = new TPMock(getDate(2018,11,28,11,20), getDate(2018,11,28,11,30));
		TripComponent t2 = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		assertTrue("Trip from 11:20 to 11:30 expected to overlap with trip from 11:00 to 12:00", t1.overlapsWith(t2));

		t1 = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,30,9,20));
		t2 = new TPMock(getDate(2018,11,29,9,30), getDate(2018,11,29,12,00));
		assertTrue("Trip from Dec 29 9:30 to Dec 29 12:00 expected to overlap with trip from Dec 28 10:20 to Dec 30 9:20", t1.overlapsWith(t2));
	}
	
	@Test
	public void test_overlapsWith_TripBeforeOtherTrip() {
		 
		TripComponent t1 = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,59));
		TripComponent t2 = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		assertFalse("Trip from 10:20 to 10:59 expected to have no overlap with trip from 11:00 to 12:00", t1.overlapsWith(t2));

		t1 = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		t2 = new TPMock(getDate(2018,11,29,9,30), getDate(2018,11,29,12,00));
		assertFalse("Trip from Dec 29 9:30 to Dec 28 11:20 expected to have no overlap with trip from Dec 29 10:20 to Dec 30 9:20", t1.overlapsWith(t2));
	}
	
	@Test
	public void test_overlapsWith_OneOfTripComponentsContainsNull() {
		 
		TripComponent t1 = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,10,59));
		TripComponent t2 = new TPMock(getDate(2018,11,28,11,00), null);
		assertFalse("Other trip component end is null, expecting no overlap", t1.overlapsWith(t2));

		t1 = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		t2 = new TPMock(null, getDate(2018,11,29,12,00));
		assertFalse("Other trip component start is null, expecting no overlap", t1.overlapsWith(t2));
		
		t1 = new TPMock(null, getDate(2018,11,28,10,59));
		t2 = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		assertFalse("trip component start is null, expecting no overlap", t1.overlapsWith(t2));

		t1 = new TPMock(getDate(2018,11,28,10,20), null);
		t2 = new TPMock(getDate(2018,11,29,9,30), getDate(2018,11,29,12,00));
		assertFalse("trip component end is null, expecting no overlap", t1.overlapsWith(t2));
	}

	@Test
	public void test_conflictsWith_OverlapAllowed() {
		 
		TPMock zig = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		zig.allowed = true;
		TPMock zag = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		zag.allowed = false;
		assertFalse("Trip from 10:20 to 11:20 with overlap allowed expected not to conflict with trip from 11:00 to 12:00", zig.conflictsWith(zag));

		zig.allowed = false;
		zag.allowed = true;
		assertFalse("Trip from 10:20 to 11:20 expected not to conflict with trip from 11:00 to 12:00 with overlap allowed", zig.conflictsWith(zag));

		zig.allowed = true;
		zag.allowed = true;
		assertFalse("Trip from 10:20 to 11:20 expected not to conflict with trip from 11:00 to 12:00 since both allow overlap allowed", zig.conflictsWith(zag));
	}
	

	@Test
	public void test_conflictsWith_NoOverlapAllowed() {
		 
		TPMock zig = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		zig.allowed = false;
		TPMock zag = new TPMock(getDate(2018,11,28,11,00), getDate(2018,11,28,12,00));
		zag.allowed = false;
		assertTrue("Trip from 10:20 to 11:20 expected to conflict with trip from 11:00 to 12:00, neither allows overlap", zig.conflictsWith(zag));
	}
	
	@Test
	public void test_conflictsWith_NoOverlapAndNoOverlapAllowed() {
		 
		TPMock zig = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		zig.allowed = false;
		TPMock zag = new TPMock(getDate(2018,11,29,11,00), getDate(2018,11,29,12,00));
		zag.allowed = false;
		assertFalse("Trip from 10:20 to 11:20 on December 28 not expected to conflict with trip from 11:00 to 12:00 on Dec 29, neither allows overlap", zig.conflictsWith(zag));
	}
	
	@Test
	public void test_toString_endNull() {
		 
		TPMock c = new TPMock(getDate(2018,11,28,10,20), null);
		c.next = "10 seconds";
		String expectedString = "Start: Fri Dec 28 10:20:00 MST 2018\tEnd: \tLength: 10 seconds";
		assertEquals("hard coded duration to return 10 seconds, Expecting format: 'Start: <date as string><tab>End: <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}
	
	@Test
	public void test_toString_startOneHourBeforeEnd() {
		 
		TPMock c = new TPMock(getDate(2018,11,28,10,20), getDate(2018,11,28,11,20));
		c.next = "1 hour";
		String expectedString = "Start: Fri Dec 28 10:20:00 MST 2018\tEnd: Fri Dec 28 11:20:00 MST 2018\tLength: 1 hour";
		assertEquals("Expecting format: 'Start: <date as string><tab>End: <date as string><tab>Length: <duration>'", expectedString, c.toString());		
	}	
}