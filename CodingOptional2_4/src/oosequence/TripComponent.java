package oosequence;
import java.util.Date;

public class TripComponent{
	private Date start;
	private Date end;
	
	TripComponent(Date dep,Date arr){
		//if((dep == null || arr == null) || dep != null && arr != null && dep.before(arr)) {
			setStart(dep);
			setEnd(arr);
		//}
	}
	
	TripComponent(TripComponent f){
		end = f.end;
		start = f.start;
	}
	
	TripComponent(){
		start = new Date();
		end = new Date();
		start = null;
		end = null;
		//end.setTime(start.getTime()+ 3600000); 
		// I don't know why this line was here
	}
	
	public long lengthInSeconds() {
		long rVal = 0;
		if(end != null && start != null && start.before(end)) {
			rVal = end.getTime() - start.getTime();
			rVal = rVal/1000;
			// arrival - departure is in ms, mod 60seconds * 1000 milliseconds to get minute value
			//System.out.println(end.getTime()+ "end and "+ start.getTime());
		}
		return rVal;
	}
	public String getStart() {
		if(start!= null) {
			return start.toString();
		}
		else {
			return "";
		}
	}
	public Date getStartDate() {
		return start;
	}
	
	public void setStart(Date dIn) {
		if(dIn != null) {
			if(end != null && dIn.before(end)) {
				start = (Date) dIn.clone();
				//start = new Date(dIn.getTime());
				//this was my first idea for copying it over which worked perfectly fine apart from two tests,
				//then I realised the test dates were in MST and my method set it to UTC
				
			}
			else if(end == null) {
				start = (Date) dIn.clone();
			}
		}
		
	}
	
	public String getEnd() {
		if(end== null) {
			return "";	
		}
		else {
			return end.toString();
		}
		
	}
	public Date getEndDate() {
		return end;
	}
	
	public void setEnd(Date dIn) {
		if(dIn != null) {
			if(start != null && dIn.after(start)) {
				end = (Date) dIn.clone();
			}
			else if(start == null) {
				end = (Date) dIn.clone();
			}
		}

	}
	
	public String getDuration() {
		return "";
	}
	
	public String toString() {
		return("Start: "+getStart()+"\tEnd: "+getEnd()+"\tLength: "+getDuration());
	}
	
	public boolean isBefore(TripComponent t) {
		if(end.before(t.getStartDate())) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean overlapsWith(TripComponent t) {
//		if((end != null && t.getStart() != "" && end.before(t.getStartDate())) || (start != null && t.getEnd() != "" && t.getEndDate().before(start))) {
//			return false;
//		}
		if(start!=null && end != null &&(t.getStart() != "" && start.before(t.getStartDate())&& end.after(t.getStartDate()))) {
			return true;
		}
		else if(start!=null && end != null &&(t.getEnd() != "" && start.before(t.getEndDate())&& end.after(t.getEndDate()))) {
			return true;
		}
		else if(t.getStart() != "" && t.getEnd() != "" &&(start != null && t.getStartDate().before(start)&& t.getEndDate().after(start))) {
			return true;
		}
		else if(t.getStart() != "" && t.getEnd() != "" &&(end!= null && t.getStartDate().before(end)&& t.getEndDate().after(end))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected boolean overlapAllowed() {
		return false;
	}
	
	public boolean conflictsWith(TripComponent t) {
		if(!overlapsWith(t)) {
			return false;
		}
		else {
			if(overlapAllowed()||t.overlapAllowed()) {
				return false;
			}
			else {
				return true;
			}
			
		}
		
	}

}
