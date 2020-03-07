package test;

import static org.junit.Assert.*;
import main.java.bus.Path;

import org.junit.Test;

public class Path_Test {

	@Test
	public void test() {
		Path PathTest = new Path(62,500,4020);
		assertEquals(62, PathTest.getPathID());
		assertEquals(500, PathTest.getPathDur());
		assertEquals(4020, PathTest.getPathLength());
	}

}
