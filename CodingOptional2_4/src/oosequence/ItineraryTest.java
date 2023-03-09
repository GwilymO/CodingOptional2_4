package oosequence;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ItineraryTest {
	private Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, hour, minute, 0);
		return cal.getTime();
	}


	@Test
	public void test_ConstructorAndGetter() {
		Itinerary c = new Itinerary("Test Constructor and Getter");
		assertEquals("Testing constructor and getter", "Test Constructor and Getter", c.getName());
	}

	@Test
	public void test_addTripComponent_addingOneFlight() {
		Itinerary c = new Itinerary("Test");
		Flight m = new Flight(getDate(2019,1,1,10,30),getDate(2019,1,1,18,1),"YYC", "YVR");
		c.addTripComponent(m);
		ArrayList<TripComponent> list = c.getTripComponents();
		Flight m2 = null;

		if (list.size() > 0){
			m2 = (Flight)list.get(0);
		}


		assertEquals("Itinerary only has one flight - testing size.", 1, list.size());
		assertEquals("Itinerary only has one flight - testing start.", "YYC " + getDate(2019,1,1,10,30), m2.getStart());
		assertEquals("Itinerary only has one flight - testing end.", "YVR " + getDate(2019,1,1,18,1), m2.getEnd());
	}

	@Test
	public void test_addTripComponent_addingOneTripComponent() {
		Itinerary c = new Itinerary("Test");
		TripComponent h = new TripComponent(getDate(2019,1,1,10,30),getDate(2019,1,1,18,1));
		c.addTripComponent(h);
		ArrayList<TripComponent> list = c.getTripComponents();
		TripComponent actual = null;

		if (list.size() > 0){
			actual = (TripComponent)list.get(0);
		}


		assertEquals("Itinerary only has one flight - testing size.", 1, list.size());
		assertEquals("Itinerary only has one flight - testing start.", getDate(2019,1,1,10,30).toString(), actual.getStart());
		assertEquals("Itinerary only has one flight - testing end.", getDate(2019,1,1,18,1).toString(), actual.getEnd());
	}

	@Test
	public void test_addTripComponent_addingManyFlightsInOrder() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45), "YYC", "YVR");
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21), "YYC", "YVR");
		Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21), "YYC", "YVR");
		c.addTripComponent(m1);
		c.addTripComponent(m2);
		c.addTripComponent(m3);
		c.addTripComponent(m4);
		c.addTripComponent(m5);
		c.addTripComponent(m6);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
		assertEquals("flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("flight 2 test - testing start", "YYC " + getDate(2019,1,1,11,30), list.get(1).getStart());
		assertEquals("flight 3 test - testing start", "YYC " + getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("flight 5 test - testing start", "YYC " + getDate(2019,1,2,4,40), list.get(4).getStart());
		assertEquals("flight 6 test - testing start", "YYC " + getDate(2019,1,2,9,40), list.get(5).getStart());
	}

	@Test
	public void test_addTripComponent_addingVariedInOrder() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		TripComponent m2 = new TripComponent(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		TripComponent m5 = new TripComponent(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
		TripComponent m6 = new TripComponent(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
		c.addTripComponent(m1);
		c.addTripComponent(m2);
		c.addTripComponent(m3);
		c.addTripComponent(m4);
		c.addTripComponent(m5);
		c.addTripComponent(m6);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 6 trip components", 6, list.size());			
		assertEquals("flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("TripComponent 2 test - testing start", getDate(2019,1,1,11,30).toString(), list.get(1).getStart());
		assertEquals("flight 3 test - testing start", "YYC " + getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("flight 5 test - testing start", getDate(2019,1,2,4,40).toString(), list.get(4).getStart());
		assertEquals("flight 6 test - testing start", getDate(2019,1,2,9,40).toString(), list.get(5).getStart());
	}

	@Test
	public void test_addTripComponent_addingOverlappingFlight() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45), "YYC", "YVR");
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21), "YYC", "YVR");
		Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21), "YYC", "YVR");
		c.addTripComponent(m1);
		c.addTripComponent(m2);
		c.addTripComponent(m3);
		c.addTripComponent(m4);
		c.addTripComponent(m5);
		c.addTripComponent(m6);

		Flight overlapping = new Flight(getDate(2019,1,1,15,30), getDate(2019,1,1,20,00), "YYC", "YVR");
		c.addTripComponent(overlapping);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 7 flights, with one overlapping flight", 6, list.size());			
		assertEquals("flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("flight 2 test - testing start", "YYC " + getDate(2019,1,1,11,30), list.get(1).getStart());
		assertEquals("flight 3 test - testing start","YYC " +  getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("flight 5 test - testing start", "YYC " + getDate(2019,1,2,4,40), list.get(4).getStart());
		assertEquals("flight 6 test - testing start", "YYC " + getDate(2019,1,2,9,40), list.get(5).getStart());
	}

	@Test
	public void test_addTripComponent_addingOverlappingTripComponent() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45), "YYC", "YVR");
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21), "YYC", "YVR");
		Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21), "YYC", "YVR");
		c.addTripComponent(m1);
		c.addTripComponent(m2);
		c.addTripComponent(m3);
		c.addTripComponent(m4);
		c.addTripComponent(m5);
		c.addTripComponent(m6);

		TripComponent overlapping = new TripComponent(getDate(2019,1,1,15,30), getDate(2019,1,1,20,00));
		c.addTripComponent(overlapping);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 6 flights, with one overlapping TripComponent", 6, list.size());			
		assertEquals("flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("flight 2 test - testing start", "YYC " + getDate(2019,1,1,11,30), list.get(1).getStart());
		assertEquals("flight 3 test - testing start","YYC " +  getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("flight 5 test - testing start", "YYC " + getDate(2019,1,2,4,40), list.get(4).getStart());
		assertEquals("flight 6 test - testing start", "YYC " + getDate(2019,1,2,9,40), list.get(5).getStart());
	}

	@Test
	public void test_addTripComponent_addingReverseOrder() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45),"YYC", "YVR");
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59),  "YYC", "YVR");
		Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21),  "YYC", "YVR");
		Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21),  "YYC", "YVR");
		c.addTripComponent(m6);
		c.addTripComponent(m5);
		c.addTripComponent(m4);
		c.addTripComponent(m3);
		c.addTripComponent(m2);
		c.addTripComponent(m1);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
		assertEquals("flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("flight 2 test - testing start", "YYC " + getDate(2019,1,1,11,30), list.get(1).getStart());
		assertEquals("flight 3 test - testing start", "YYC " + getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("flight 5 test - testing start", "YYC " + getDate(2019,1,2,4,40), list.get(4).getStart());
		assertEquals("flight 6 test - testing start", "YYC " + getDate(2019,1,2,9,40), list.get(5).getStart());
	}

	@Test
	public void test_addTripComponent_addingRandomOrder() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		Flight m2 = new Flight(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45), "YYC", "YVR");
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		Flight m5 = new Flight(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21), "YYC", "YVR");
		Flight m6 = new Flight(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21), "YYC", "YVR");
		c.addTripComponent(m4);
		c.addTripComponent(m6);
		c.addTripComponent(m1);
		c.addTripComponent(m5);
		c.addTripComponent(m2);
		c.addTripComponent(m3);

		ArrayList<TripComponent> list = c.getTripComponents();

		assertEquals("Expected list of size 6 after adding 6 flights", 6, list.size());			
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 1 test - testing start", "YYC " + getDate(2019,1,1,10,0), list.get(0).getStart());
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 2 test - testing start", "YYC " + getDate(2019,1,1,11,30), list.get(1).getStart());
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 3 test - testing start", "YYC " + getDate(2019,1,1,14,50), list.get(2).getStart());
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 4 test - testing start", "YYC " + getDate(2019,1,1,23,30), list.get(3).getStart());
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 5 test - testing start", "YYC " + getDate(2019,1,2,4,40), list.get(4).getStart());
		assertEquals("Insertion order: 4,6,1,5,2,3, flight 6 test - testing start", "YYC " + getDate(2019,1,2,9,40), list.get(5).getStart());
	}
	
	@Test
	public void test_toString() {
		Itinerary c = new Itinerary("Test");
		Flight m1 = new Flight(getDate(2019,1,1,10,0),getDate(2019,1,1,11,0), "YYC", "YVR");
		TripComponent m2 = new TripComponent(getDate(2019,1,1,11,30),getDate(2019,1,1,12,45));
		Flight m3 = new Flight(getDate(2019,1,1,14,50),getDate(2019,1,1,19,35), "YYC", "YVR");
		Flight m4 = new Flight(getDate(2019,1,1,23,30),getDate(2019,1,2,2,59), "YYC", "YVR");
		TripComponent m5 = new TripComponent(getDate(2019,1,2,4,40),getDate(2019,1,2,6,21));
		TripComponent m6 = new TripComponent(getDate(2019,1,2,9,40),getDate(2019,1,2,13,21));
		c.addTripComponent(m1);
		c.addTripComponent(m2);
		c.addTripComponent(m3);
		c.addTripComponent(m4);
		c.addTripComponent(m5);
		c.addTripComponent(m6);

		String expectedValue = "Test\n0\tYYC Fri Feb 01 10:00:00 MST 2019\tYVR Fri Feb 01 11:00:00 MST 2019\n" +
				"1\tFri Feb 01 11:30:00 MST 2019\tFri Feb 01 12:45:00 MST 2019\n" +
				"2\tYYC Fri Feb 01 14:50:00 MST 2019\tYVR Fri Feb 01 19:35:00 MST 2019\n" +
				"3\tYYC Fri Feb 01 23:30:00 MST 2019\tYVR Sat Feb 02 02:59:00 MST 2019\n" +
				"4\tSat Feb 02 04:40:00 MST 2019\tSat Feb 02 06:21:00 MST 2019\n" +
				"5\tSat Feb 02 09:40:00 MST 2019\tSat Feb 02 13:21:00 MST 2019\n";
		String actualValue = c.toString();
		assertEquals("Testing toString with itinerary that contains Flight and TripComponent objects", expectedValue, actualValue);
	}

}