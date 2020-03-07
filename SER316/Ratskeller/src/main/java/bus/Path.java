package main.java.bus;

public class Path {
	int path_id;
	int path_duration; //length of time
	int path_length; //time
	
	public Path(int path_id, int path_duration, int path_length) {
		this.path_id = path_id;
		this.path_duration = path_duration;
		this.path_length = path_length;
	}
	
	public int getPathID() {
		return path_id;
	}
	
	public int getPathDur() {
		return path_duration;
	}

	public int getPathLength() {
		return path_length;
	}
}
