package main.java.bus;

public class BusStop {
	int bus_stop_id;
	int bus_stop_long; //longitude
	int bus_stop_lat; //latitude
	
	public BusStop(int bus_stop_id, int bus_stop_long, int bus_stop_lat) {
		this.bus_stop_id = bus_stop_id;
		this.bus_stop_long = bus_stop_long;
		this.bus_stop_lat = bus_stop_lat;
	}
	
	public int getBusStopID() {
		return bus_stop_id;
	}
	
	public int getBusStopLong() {
		return bus_stop_long;
	}
	
	public int getBusStopLat() {
		return bus_stop_lat;
	}
	
}
