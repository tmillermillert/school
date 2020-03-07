package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Powers;

public class PowersTest {

    @Test
    public void test() {
        Powers power = new Powers(10, "Ice");
        assertTrue(power.get_attack_name().compareTo("Ice") == 0);
        assertTrue(power.get_attack_damage() == 10);
    }

}
