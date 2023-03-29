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
		
		int tSize = tripComponents.size();
		
		if(tSize == 0) {
			tripComponents.add(t);
		}
		else {
			if(t.isAfter(tripComponents.get(tSize-1))) {
				tripComponents.add(t);
			}
			else if(t.isBefore(tripComponents.get(0))) {
				tripComponents.add(0,t);
			}
			else {
				for(TripComponent tList : tripComponents) {
					if(t.getStartDate().before(tList.getStartDate())) {
						if(t.isBefore(tList)) {
							tripComponents.add(tripComponents.indexOf(tList),t);
						}
						else if(t.overlapsWith(tList) && (t.overlapAllowed() || tList.overlapAllowed())) {
							tripComponents.add(tripComponents.indexOf(tList),t);
						}
						
						break;
					}
					else if(t.overlapsWith(tList)&& (t.overlapAllowed() || tList.overlapAllowed())) {
						tripComponents.add(tripComponents.indexOf(tList)+1,t);
						break;
					}
					else if(t.overlapsWith(tList)&& t.conflictsWith(tList)) {
						//System.out.println("TripComponents overlap, not allowed");
						break;
					}

				}
			}
		}
		//System.out.println(tripComponents.toString());
		
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
		String sOut = name+ "\n";
		for(int i = 0;i<tripComponents.size();i++) {
			//sOut = sOut +"\n%d\t%s\t%s".formatted(i,tripComponents.get(i).getStart(),tripComponents.get(i).getEnd());
			//This is a perfectly valid way to get the correct output but the tests require a \n at the end of the final entry
			// so I had to redo the formatting and add a \n to "name" to get the same result
			sOut = sOut +"%d\t%s\t%s\n".formatted(i,tripComponents.get(i).getStart(),tripComponents.get(i).getEnd());
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
