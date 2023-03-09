package oosequence;

import java.util.ArrayList;

public class Itinerary {
	private ArrayList<TripComponent> tripComponents;
	private String name;
	
	Itinerary(String iName){
		name = iName;
		tripComponents = new ArrayList<TripComponent>();
	}
	
	public void addTripComponent(TripComponent t) {
		if(tripComponents.size() == 0) {
			tripComponents.add(t);
		}
		else {
			boolean nIns = true;
			for(int i = 0;i<tripComponents.size();i++) {
				if(t.getEndDate().before(tripComponents.get(i).getStartDate()) && (i<1 || t.getStartDate().after(tripComponents.get(i-1).getEndDate()))) {
					//if it arrives before position i and leaves after the previous flight OR position i is the first in the list
					tripComponents.add(i,t);
					nIns = false;
				}
			}
			if(nIns&& t.getStartDate().after(tripComponents.get(tripComponents.size()-1).getEndDate())){
				//if it leaves after the last flight in the list
				tripComponents.add(t);
			}
		}
	}
	
	public long getTotalLayover() {
		long l = 0;
		if(tripComponents.size()<= 1){
			return 0;
		}
		// catch single or empty lists
		
		for(int i = 0;i<tripComponents.size()-1;i++) {
			long tLong = 0;
			tLong = tripComponents.get(i+1).getStartDate().getTime() - tripComponents.get(i).getEndDate().getTime();
			tLong = tLong / 60000;
			l += tLong;
		}
		return l;
	}
	public String toString() {
		String sOut = name;
		for(int i = 0;i<tripComponents.size();i++) {
			sOut = sOut +"\n{}\t{}\t{}".formatted(i,tripComponents.get(i).getStart(),tripComponents.get(i).getEnd());
		}
		return sOut;
	}
	public ArrayList<TripComponent> getTripComponents() {
		return tripComponents;
	}
	
	public String getName() {
		return name;
	}

}
