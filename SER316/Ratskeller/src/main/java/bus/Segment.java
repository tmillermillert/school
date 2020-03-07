package main.java.bus;

public class Segment {
	int segment_id;
	int segment_length; //distance
	int segment_duration; //time
	int segment_name;
	int segment_start_loc;
	int segment_end_loc;
	
	public Segment(int segment_id, int segment_length, int segment_duration) {
		this.segment_id = segment_id;
		this.segment_length = segment_length;
		this.segment_duration = segment_duration;
	}
	
	public int getSegmentID() {
		return segment_id;
	}
	
	public int getSegmentLength() {
		return segment_length;
	}
	
	public int getSegmentDur() {
		return segment_duration;
	}
	
	public void setRouteID(int route_id) {
		this.segment_id= route_id;
	}
	
	public void setSegmentName(int segment_name) {
		this.segment_name= segment_name;
	}
	
	public void setSegmentStart(int segment_start_loc) {
		this.segment_start_loc= segment_start_loc;
	}
	
	public void setSegmentEnd(int segment_end_loc) {
		this.segment_end_loc= segment_end_loc;
	}
	
	public void setSegmentLength(int segment_length) {
		this.segment_length= segment_length;
	}
}
