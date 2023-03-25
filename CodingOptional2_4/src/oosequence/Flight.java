package oosequence;
import java.util.Date;

public class Flight extends TripComponent{
	private String departureAirport;
	private String arrivalAirport;
	
	public Flight(){
		departureAirport = new String("");
		arrivalAirport = new String("");
	}
	
	public Flight(Date dStart, Date dEnd,String depPort, String arrPort) {
		setStart(dStart);
		setEnd(dEnd);
		if(depPort!= null && depPort.length() == 3) {
			departureAirport = depPort;
		}
		else {
			departureAirport = "";
		}
		
		if(arrPort!= null && arrPort.length() == 3) {
			arrivalAirport = arrPort;// this is where all the pirates go on holiday
		}
		else {
			arrivalAirport = "";
		}
	}
	
	public Flight(Flight f) {
		setStart(f.getStartDate());
		setEnd(f.getEndDate());
		departureAirport = f.getDepartureAirport();
		arrivalAirport = f.getArrivalAirport();
	}
	
	public String getDepartureAirport(){
		return departureAirport;
	}
	
	public void setDepartureAirport(String s){
		String dStr = "";
		if(s!= null && s.length()== 3) {
			dStr = s;
		}
		departureAirport = dStr;
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public void setArrivalAirport(String s) {
		String aStr = "";
		if(s!= null && s.length()== 3) {
			aStr = s;
		}
		arrivalAirport = aStr;
	}
	
	public String getDuration() {
		long l = lengthInSeconds();
		//System.out.println(l+ "seconds");
		if(l==0) {
			return("0 minutes");
		}
		l = l /60;
		//time in minutes
		long h = l /60;
		long m = l%60;
		String hString = "";
		String mString = "";
		
		if(h==1) {
			hString = h + " hour";
		}
		else if(h > 1) {
			hString = h + " hours";
		}
		
		if(m>=1) {
			mString = m + " minutes";
		}
		
		if(hString != "" && mString != "") {
			mString = " and "+ mString;
		}
		return (hString + mString);
		// I really hope this is the right string format
	}
	
	public String getStart() {
		return departureAirport +" "+ super.getStart();
	}
	public String getEnd() {
		return arrivalAirport +" "+  super.getEnd();
	}
	
	protected boolean overlapAllowed() {
		return false;
	}

}