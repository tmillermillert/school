package test;

import static org.junit.Assert.*;

import org.junit.Test;
import main.java.bus.BusStop;

public class Bus_Stop_Test {

	@Test
	public void test() {
		BusStop BusStopTest = new BusStop(1,2,3);
		assertEquals(1, BusStopTest.getBusStopID());
		assertEquals(2, BusStopTest.getBusStopLong());
		assertEquals(3, BusStopTest.getBusStopLat());
		
	}

}
