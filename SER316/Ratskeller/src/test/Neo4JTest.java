package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Result;

import main.java.bus.BusSchedulerNeo4j;

public class Neo4JTest {

	@Before public void initialize() {
		BusSchedulerNeo4j.start_neo4jDB();
	}
	
	
	@Test
	public void test() {
		Result expect = BusSchedulerNeo4j.get_buses();
		String expectS = expect.resultAsString();
		assertEquals(expectS, BusSchedulerNeo4j.get_buses().resultAsString());
	}

}
