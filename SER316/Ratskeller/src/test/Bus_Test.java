package test;

import static org.junit.Assert.*;


import org.junit.Test;
import main.java.bus.Bus;

public class Bus_Test {
	

	@Test
	public void test() {
		Bus BusTest = new Bus(1567,218,1);
		assertEquals(1567, BusTest.getBusID());
		assertEquals(218, BusTest.getBusCap());
		assertEquals(1, BusTest.getBusStat());
	}

}
