package test;
import main.java.bus.Route;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;


public class RouteTest {

    Route routeUnderTest;
    Route ctor;
    Route empty;
    Route one, two, three;
    Route four, five, six;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        set_routeManualInput();
        set_routeConstructor();
        set_routes();
    }

    @After
    public void tearDown() throws Exception {
    }



   
    @Test
    public void test_routeManualInput() throws Exception {
        HashMap<Integer, Route> ans = new HashMap<Integer, Route>();
        ans.put(routeUnderTest.getRoute_id(), routeUnderTest);
        ans.put(empty.getRoute_id(), empty);
        System.out.println("Test Route ID: " + ans.get(2).getRoute_id());
        assertTrue(ans.get(2).getRoute_id() == 2);
        assertTrue(ans.get(2).getDirection() == "NW");
        assertTrue(ans.get(2).getRoute_name() == "Test Route");
        System.out.println("Test Route date: " + ans.get(2).getRoute_date());
//        assertTrue(ans.get(3).getRoute_date().equals("11/12/19 03:30"));
        assertTrue(ans.get(2).getSegment_start_loc() == 111.254f);
        assertTrue(ans.get(2).getSegment_end_loc() == 112.251f);
        assertTrue(ans.get(2).getDirection() == "NW");
        assertTrue(ans.get(2).getSegment_length() == 15.50);
        assertTrue(ans.get(2).getRouteList().get(2).getRoute_name() == "Test Route");
        assertTrue(ans.get(1).getRouteList().get(1).getRoute_name() == "Empty");
    }

    @Test
    public void test_routeConstructor(){
        HashMap<Integer, Route> ans = new HashMap<Integer, Route>();
        ans.put(ctor.getRoute_id(), ctor);
        System.out.println("Test ctor ID: " + ctor.getRoute_id());
        int id = ctor.getRoute_id();
        assertTrue(ans.get(id).getRoute_id() == 1);
        assertTrue(ans.get(id).getSegment_start_loc() == 121.15f);
        assertTrue(ans.get(id).getSegment_end_loc() == 111.12f);
        assertTrue(ans.get(id).getRoute_name() == "Test_Ctor");

    }


    @Test
    public void test_Routes(){
        HashMap<Integer, Route> ans = new HashMap<Integer, Route>();
        ans.put(four.getRoute_id(), four);
        ans.put(five.getRoute_id(), five);
        ans.put(six.getRoute_id(), six);
        assertTrue(ans.get(4).getRoute_id() == 4);
        assertTrue(ans.get(5).getRoute_id() == 5);
        assertTrue(ans.get(6).getRoute_id() == 6);
    }

  
    @Test
    public void testEmpties() throws Exception {
        Route empty = new Route();
        empty.setRoute_id(23);
        HashMap<Integer, Route> emptyMap= new HashMap<>();
        emptyMap.put(empty.getRoute_id(), empty);
        exception.expect(Exception.class);
        exception.expectMessage("The date of the route was not set.");
        emptyMap.get(23).getRoute_date();
    }

    /*set up methods*/
    public void set_routeManualInput() throws IOException {

        empty = new Route();
        empty.setRoute_id(0);
        empty.setRoute_name("Empty");
        empty.setRouteList(empty);

        routeUnderTest = new Route();
        routeUnderTest.setRoute_id(2);
        routeUnderTest.setRoute_name("Test Route");
        routeUnderTest.setRoute_date(2019, 11, 12, 3, 30);
        routeUnderTest.setSegment_start_loc(111.254f);
        routeUnderTest.setSegment_end_loc(112.251f);
        routeUnderTest.setDirection("NW");
        routeUnderTest.setSegment_length(15.5d);
        routeUnderTest.setRouteList(routeUnderTest);
    }

    public void set_routeConstructor(){
        ctor = new Route(1, "Test_Ctor", 121.15f, 111.12f);

    }

    public void set_routes(){

        four = new Route();
        four.setRoute_id(4);
        five = new Route();
        five.setRoute_id(5);
        six = new Route();
        six.setRoute_id(6);
    }

}
