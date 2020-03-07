package main.java.bus;

public class Driver {
	int driver_id;
	String driver_name;
	int driver_phone_number; //formatted 8881548274 (no spaces or dashes)
	int driver_shift; // Shift ID number
	
	public Driver(int driver_id, String driver_name, int driver_phone_number, int driver_shift) {
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.driver_phone_number = driver_phone_number;
		this.driver_shift = driver_shift;
	}
	
	public int getDriverID() {
		return driver_id;
	}
	
	public String getDriverName() {
		return driver_name;
	}
	
	public int getDriverPhone() {
		return driver_phone_number;
	}
	
	public int getDriverShift() {
		return driver_shift;
	}
	
	public void setDriverID(int driver_id) {
		this.driver_id=driver_id;
	}
	
	public void setDriverName(String driver_name) {
		this.driver_name= driver_name;
	}
	
	public void setDriverPhone(int driver_phone_number) {
		this.driver_phone_number=driver_phone_number;
	}
	
	public void setDriverShift(int driver_shift) {
		this.driver_shift=driver_shift;
	}
}
