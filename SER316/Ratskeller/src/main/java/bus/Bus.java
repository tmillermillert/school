package main.java.bus;

public class Bus {
	int bus_id;
	int bus_capacity; 
	int bus_status;
	
	public Bus(int bus_id, int bus_capacity, int bus_status) {
		this.bus_id = bus_id;
		this.bus_capacity = bus_capacity;
		this.bus_status = bus_status;
	}
	
	public int getBusID() {
		return bus_id;
	}
	
	public int getBusCap() {
		return bus_capacity;
	}
	
	public int getBusStat() {
		return bus_status;
	}
	
	public void setBusID(int bus_id) {
		this.bus_id=bus_id;
	}
	
	public void setBusCap(int bus_capacity) {
		this.bus_capacity = bus_capacity;
	}
	
	public void setBusStat(int bus_status) {
		this.bus_status =bus_status;
	}
	
	public boolean booked(int bus_id)
	{
		if(this.bus_id==bus_id)
		{
			return true;
		}
		return false;
	}
}
