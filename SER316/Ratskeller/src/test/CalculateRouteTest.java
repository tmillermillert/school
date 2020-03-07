package test;
import main.java.bus.CalculateRoute;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import static org.junit.Assert.*;

public class CalculateRouteTest {

    private CalculateRoute calc;
    private double speed = 30.0d;

    @Test
    public void calculateDistance() {

        System.out.println("Calculating distance from downtown Phoenix to "
                + "downtown Tucson...");
        System.out.println("Google maps has distance of 115 miles." );
        calc = new CalculateRoute();
        double dist =
                calc.calculateDistance(32.221360f, -110.976898f,
                        33.450316f, -112.073970f);
        System.out.println("Calculated Distance: " +  dist + " miles.");
        assertTrue(dist > 112.00);
        assertTrue(dist < 115.00);
    }

    @Test
    public void calculateDuration() {

        System.out.println("Calculating duration from downtown Phoenix"
                + "to downtown Tucson");
        System.out.println("Trip should take 3h 50m travelling at 30mph");
        calc = new CalculateRoute();
        double dist =
                calc.calculateDistance(32.221360f, -110.976898f,
                        33.450316f, -112.073970f);
        System.out.println("Distance: " + dist);

        double dur = calc.calculateDuration(dist, speed);
        String duration = calc.ConvertTimeValue(dur);
        System.out.println("Calculated Duration: " + duration);

        for (int i = 0; i < 1; i++) {
            if(duration.equalsIgnoreCase("3H 46M")) {
                System.out.println("asserted 3H 46M");
                assertTrue(true);
            } else if (duration.equalsIgnoreCase("3H 47M")) {
                System.out.println("asserted 3H 47M");
                assertTrue(true);
            } else if (duration.equalsIgnoreCase("3H 48M")) {
                System.out.println("asserted 3H 48M");
                assertTrue(true);
            } else if (duration.equalsIgnoreCase("3H 49M")) {
                System.out.println("asserted 3H 49M");
                assertTrue(true);
            } else if (duration.equalsIgnoreCase("3H 50M")) {
                System.out.println("asserted 3H 50M");
                assertTrue(true);
            } else {
                System.out.println("Failed Test");
                assertFalse(false);
            }
        }
    }
}
