package oosequence;
import java.util.Date;

public class Flight {
	private Date departure;
	private Date arrival;
	
	Flight(Date dep,Date arr){
		if((dep == null || arr == null) || dep != null && arr != null && dep.before(arr)) {
			setDeparture(dep);
			setArrival(arr);
		}
	}
	
	Flight(Flight f){
		arrival = f.arrival;
		departure = f.departure;
	}
	
	public long length() {
		long rVal = 0;
		if(arrival != null && departure != null && departure.before(arrival)) {
			rVal = arrival.getTime() - departure.getTime();
			rVal = rVal/60000;
			// arrival - departure is in ms, mod 60seconds * 1000 milliseconds to get minute value
		}
		return rVal;
	}
	public Date getDeparture() {
		Date d = null;
		if(departure != null) {
			d = (Date) departure.clone();
		}

		return d;
	}
	
	public void setDeparture(Date dIn) {
		if(dIn != null) {
			if(arrival != null && dIn.before(arrival)) {
				departure = new Date(dIn.getTime());
			}
			else if(arrival == null) {
				departure = new Date(dIn.getTime());
			}
		}
		
	}
	
	public Date getArrival() {
		Date d = null;
		if(arrival != null) {
			d = (Date) arrival.clone();
		}
		return d;
	}
	
	public void setArrival(Date dIn) {
		if(dIn != null) {
			if(departure != null && dIn.after(departure)) {
				arrival = new Date(dIn.getTime());
			}
			else if(departure == null) {
				arrival = new Date(dIn.getTime());
			}
		}

	}

}