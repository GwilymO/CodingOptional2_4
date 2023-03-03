package oosequence;

public class Flight extends TripComponent{
	private String departureAirport;
	private String arrivalAirport;
	
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
		l = l /60;
		return (l + " minutes");
		// I really hope this is the right string format
	}
	

}
