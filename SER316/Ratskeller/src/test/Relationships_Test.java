package test;

import static org.junit.Assert.*;
import main.java.bus.Relationships;

import org.junit.Test;

public class Relationships_Test {

	@Test
	public void test() {
		Relationships RelationshipsTest = new Relationships(1230,41919,200,51620,900,51720,5,1);
		assertEquals(1230, RelationshipsTest.getDriverRouteTime());
		assertEquals(41919, RelationshipsTest.getDriverRouteDate());
		assertEquals(200, RelationshipsTest.getDriverBusTime());
		assertEquals(51620, RelationshipsTest.getDriverBusDate());
		assertEquals(900, RelationshipsTest.getBusRouteTime());
		assertEquals(51720, RelationshipsTest.getBusRouteDate());
		assertEquals(5, RelationshipsTest.getPathSegmentSequence());
		assertEquals(1, RelationshipsTest.getIsStartBusStop());
	}

}
