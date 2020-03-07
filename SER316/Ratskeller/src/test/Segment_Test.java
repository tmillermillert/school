package test;

import static org.junit.Assert.*;
import main.java.bus.Segment;

import org.junit.Test;

public class Segment_Test {

	@Test
	public void test() {
		Segment SegmentTest = new Segment(167, 39, 5);
		assertEquals(167, SegmentTest.getSegmentID());
		assertEquals(39, SegmentTest.getSegmentLength());
		assertEquals(5, SegmentTest.getSegmentDur());
	}

}
