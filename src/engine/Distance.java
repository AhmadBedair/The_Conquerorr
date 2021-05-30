package engine;

public class Distance {
	
	private String from;
	private String to;
	private int distance;
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public Distance(String from , String to , int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	

}
