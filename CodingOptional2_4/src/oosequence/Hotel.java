package oosequence;
import java.util.Date;

public class Hotel extends TripComponent{
	String name;
	
	
	Hotel(Date startTime,Date endTime, String aName){
		setStart(startTime);
		setEnd(endTime);
		name = aName;
	}
	
	Hotel(Hotel toCopy){
		setStart(toCopy.getStartDate());
		setEnd(toCopy.getEndDate());
		name = toCopy.getName();
	}
	
	public String getName() {
		String s = "";
		if(name != null) {
			s = name;
		}
		return s;
	}
	
	public String getDuration() {
		long l = lengthInSeconds();
		//System.out.println(l+ "seconds");
		if(l==0) {
			return("1 day");
		}
		l = l /3600;
		long d = l / 24;
		if(l%24 >= 12) {
			d+=1;
		}
		if(d>= 2) {
			return d+ " days";
		}
		else {
			return "1 day";
		}
		
	}
	
	public String toString() {
		return("Hotel "+name+"\tStart: "+getStart()+"\tEnd: "+getEnd()+"\tLength: "+getDuration());
		//Hotel Hotel California\tStart: Thu Mar 28 00:00:00 MDT 2019\tEnd: Thu Apr 04 00:00:00 MDT 2019\tLength: 7 days"
	}
	
	protected boolean overlapAllowed() {
		return true;
	}
}
