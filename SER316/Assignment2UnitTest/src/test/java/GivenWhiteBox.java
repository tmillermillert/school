import main.java.*;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import org.junit.Test;

import main.java.Course;


public class GivenWhiteBox {
    Course oneStudent;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void noCurvingTwoStudents() {
        // One Student
        oneStudent = new Course("SER316");
        oneStudent.set_points("Hanna", 85);
        oneStudent.set_points("Tanja", 100);
        Map<String, Integer> ans = oneStudent.countOccurencesLetterGrades(false);
        assertTrue(ans.get("A") == 1);
        assertTrue(ans.get("B") == 1);
        assertTrue(ans.get("C") == 0);
        assertTrue(ans.get("D") == 0);
        assertTrue(ans.get("F") == 0);
    }
}
