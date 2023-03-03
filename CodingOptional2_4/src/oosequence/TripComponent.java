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
		end.setTime(start.getTime()+ 3600000); 
	}
	
	public long lengthInSeconds() {
		long rVal = 0;
		if(end != null && start != null && start.before(end)) {
			rVal = end.getTime() - start.getTime();
			rVal = rVal/1000;
			// arrival - departure is in ms, mod 60seconds * 1000 milliseconds to get minute value
		}
		return rVal;
	}
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date dIn) {
		if(dIn != null) {
			if(end != null && dIn.before(end)) {
				start = dIn;
			}
			else if(end == null) {
				start = dIn;
			}
		}
		
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date dIn) {
		if(dIn != null) {
			if(start != null && dIn.after(start)) {
				end = dIn;
			}
			else if(start == null) {
				end = dIn;
			}
		}

	}

}
