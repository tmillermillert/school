package main.java.bus;

public class Relationships {
	int driver_route_time; //time that a driver starts a route, formatted HHMMSS, no other characters
	int driver_route_date; //date that a driver starts a route, formatted MMDDYYYY, no other characters
	int driver_bus_time;
	int driver_bus_date;
	int bus_route_time;
	int bus_route_date;
	int path_segment_sequence;
	int is_start_bus_stop; // 1 = yes, 0 = no
	
	public Relationships(int driver_route_time, int driver_route_date, int driver_bus_time, int driver_bus_date, int bus_route_time, int bus_route_date, int path_segment_sequence, int is_start_bus_stop) {
		this.driver_route_time = driver_route_time;
		this.driver_route_date = driver_route_date;
		this.driver_bus_time = driver_bus_time;
		this.driver_bus_date = driver_bus_date;
		this.bus_route_time = bus_route_time;
		this.bus_route_date = bus_route_date;
		this.path_segment_sequence = path_segment_sequence;
		this.is_start_bus_stop = is_start_bus_stop;
	}
	
	public int getDriverRouteTime() {
		return driver_route_time;
	}
	
	public int getDriverRouteDate() {
		return driver_route_date;
	}
	
	public int getDriverBusTime() {
		return driver_bus_time;
	}
	
	public int getDriverBusDate() {
		return driver_bus_date;
	}
	
	public int getBusRouteTime() {
		return bus_route_time;
	}
	public int getBusRouteDate(){
		return bus_route_date;
	}


	public void setDriverRouteTime(int driver_route_time) {
		this.driver_route_time=driver_route_time;
	}
	
	public void setDriverRouteDate(int driver_route_date) {
		this.driver_route_date=driver_route_date;
	}
	
	public void setDriverBusTime(int driver_bus_time) {
		this.driver_bus_time= driver_bus_time;
	}
	
	public void setDriverBusDate(int driver_bus_date) {
		this.driver_bus_date= driver_bus_date;
	}
	
	public void setBusRouteTime(int bus_route_time) 
	{
		this.bus_route_time= bus_route_time;
	}
	
	public void setBusRouteDate(int bus_route_data) {
		this.bus_route_date=bus_route_date;
	}
	

	
	public int getPathSegmentSequence() {
		return path_segment_sequence;
	}
	
	public int getIsStartBusStop() {
		return is_start_bus_stop;
	}

}
