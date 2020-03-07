package test;

import static org.junit.Assert.*;
import main.java.bus.Driver;

import org.junit.Test;

public class Driver_Test {

	@Test
	public void test() {
		Driver DriverTest = new Driver(153728,"name",1736904,400);
		assertEquals(153728, DriverTest.getDriverID());
		assertEquals("name", DriverTest.getDriverName());
		assertEquals(1736904, DriverTest.getDriverPhone());
		assertEquals(400, DriverTest.getDriverShift());
	}

}
