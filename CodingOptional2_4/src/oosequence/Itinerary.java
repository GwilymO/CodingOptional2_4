package oosequence;

import java.util.ArrayList;

public class Itinerary {
	private ArrayList<Flight> flights;
	private String name;
	
	Itinerary(String iName){
		name = iName;
		flights = new ArrayList<Flight>();
	}
	
	public void addFlight(Flight f) {
		if(flights.size() == 0) {
			flights.add(new Flight(f));
		}
		else {
			boolean nIns = true;
			for(int i = 0;i<flights.size();i++) {
				if(f.getArrival().before(flights.get(i).getDeparture()) && (i<1 || f.getDeparture().after(flights.get(i-1).getArrival()))) {
					//if it arrives before position i and leaves after the previous flight OR position i is the first in the list
					flights.add(i,new Flight(f));
					nIns = false;
				}
			}
			if(nIns&& f.getDeparture().after(flights.get(flights.size()-1).getArrival())){
				//if it leaves after the last flight in the list
				flights.add(new Flight(f));
			}
		}
	}
	
	public long getTotalLayover() {
		long l = 0;
		if(flights.size()<= 1){
			return 0;
		}
		// catch single or empty lists
		
		for(int i = 0;i<flights.size()-1;i++) {
			long tLong = 0;
			tLong = flights.get(i+1).getDeparture().getTime() - flights.get(i).getArrival().getTime();
			tLong = tLong / 60000;
			l += tLong;
		}
		return l;
	}
	
	public ArrayList<Flight> getFlightList() {
		return flights;
	}
	
	public String getName() {
		return name;
	}

}